import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class World {

    final int[][] distance;
    int nbVilles, nbBusA, nbBusB, nbBusC;

    private final HashMap<String, Ville> mapNom;
    private final HashMap<Integer, Ville> mapIndex;
    
    List<Bus> bus=new ArrayList<Bus>();


    public World(int[][] distance, int nbVilles, int nbBusA, int nbBusB, int nbBusC, HashMap<String, Ville> mapNom, HashMap<Integer, Ville> mapIndex) {
        this.distance = distance;
        this.nbVilles = nbVilles;
        this.nbBusA = nbBusA;
        this.nbBusB = nbBusB;
        this.nbBusC = nbBusC;
        this.mapNom = mapNom;
        this.mapIndex = mapIndex;
    }

    static World fromFile(String filename) throws FileNotFoundException{
        File file=new File(filename+".in");
        Scanner sc=new Scanner(file);
        
        int nbVilles=sc.nextInt();
        int nbBusA=sc.nextInt(), nbBusB=sc.nextInt(), nbBusC=sc.nextInt();
        int i, j;
        
        HashMap<String, Ville> mapNom=new HashMap<String, Ville>();
        HashMap<Integer, Ville> mapIndex=new HashMap<Integer, Ville>();
        sc.useDelimiter(" |\n|\r"); //parser selon " ", \n et \r (carriage return)
        sc.next(); //pour carriage return
        Ville v;
        for(i=0; i<nbVilles; i++){
            v=new Ville(sc.next(), i, sc.nextInt(), sc.nextInt(), sc.nextInt());
            sc.next(); //pour carriage return
            mapNom.put(v.getNom(), v);
            mapIndex.put(i, v);
        }
        
        int[][] distance=new int[nbVilles][nbVilles]; //faire attention aux 0 = valeurs par defaut
        while(sc.hasNext()){
            i=mapNom.get(sc.next()).getIndex();
            j=mapNom.get(sc.next()).getIndex();
            distance[i][j]=sc.nextInt();
            sc.next(); //pour carriage return
            distance[j][i]=distance[i][j];
        }

        return new World(distance, nbVilles, nbBusA, nbBusB, nbBusC, mapNom, mapIndex);
    }
    
    int lengthMax(){
        return 0;
    }
    
    void createTrajets(int length){
        Bus busToAdd;
        while(nbVilles>0 && (nbBusA+nbBusB+nbBusC)>0){
            busToAdd=searchPath(searchDepart(), length);
            if(busToAdd!=null) bus.add(busToAdd);
            else break;
        }
    }
    
    Bus searchDepart(){
        int creditCurr=0, creditMax=0;
        int[] creditMaxParType=new int[3];//credits bus A, credits bus B, credits bus C
        int[] nbBus= {nbBusA, nbBusB, nbBusC};
        Ville ville=null;
        Bus.BUS_TYPE busType=null;

        for(Ville v:mapNom.values()) {
            if(!v.isVisited()) {
                for(int i=0;i<3;i++) {
                    if(nbBus[i]>0) {
                        creditCurr=v.getCredits(Bus.BUS_TYPE.getType(i));
                        if(creditCurr>creditMaxParType[i]) {
                            creditMaxParType[i]=creditCurr;
                            if(creditCurr>creditMax) {	
                                creditMax=creditCurr;
                                ville=v;
                                busType=Bus.BUS_TYPE.getType(i);
                            }
                        }
                    }
                }
            }
        }
        if(busType==null) return null;//villes restantes ont un credit de 0 pour les bus restants
        switch(busType) {
            case A: nbBusA--; break;
            case B: nbBusB--; break;
            case C: nbBusC--; break;
        }
        ville.visit();
        nbVilles--;
        return new Bus(ville, busType);
    }
    
    Bus searchPath(Bus b, int length){ //length=combien de pas dans le future
        if(b==null) return null;
        Tree tree;
        LinkedList<Ville> path;
        while(true){
            tree=new Tree(b.trajet.get(b.trajet.size()-1), b.credit, length, distance, mapIndex); //un arbre sur length+1 générations
            path=tree.findPath(length); //le chemin optimal sur length villes
            if(path.size()>0)
                for(Ville v : path)
                    b.passerParVille(v, distance[v.index][b.trajet.get(b.trajet.size()-1).index]);
            else return b;
            nbVilles-=path.size();
        }
    }
    
    void toFile(String filename){
        StringBuilder toWrite= new StringBuilder();
        for(Bus b : bus) toWrite.append(b.toString());
        
        try{
            FileWriter descr=new FileWriter(filename+".out");
            descr.write(toWrite.toString());
            descr.close();
        }
        catch(IOException e){}
    }
}
