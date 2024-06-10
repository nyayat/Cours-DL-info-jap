import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;

public class Formateur {
    //4.
    Scanner sc;
    LinkedList<BoiteComposite> liste;
    
    Formateur(String fichier){
        sc=null;
        try{
            sc=new Scanner(new File(fichier));
        }
        catch(Exception e){
            System.out.println("Erreur lors de l'ouverture du fichier.");
            e.printStackTrace();
            System.exit(1);
        }
        liste=new LinkedList<BoiteComposite>();
    }
    
    private BoiteComposite readParagraphe(){
        BoiteComposite res=new BoiteComposite();
        String temp=sc.next();
        Scanner s=new Scanner(temp);
        while(s.hasNext()){
            res.addBoite(new BoiteMot(s.next()));
            res.addBoite(new BoiteEspace());
        }
        res.deleteLastSpace();
        return res;
    }
    
    void read(){
        sc.useDelimiter("\n\\s*\n");  //entre deux retours à la ligne
        while(sc.hasNext()){
            BoiteComposite temp=readParagraphe();
            if(!(temp.isEmpty())) liste.add(temp);
                                        //on n'ajoute pas les paragraphes vides
        }
    }
    
    private void printParagraphe(BoiteComposite b){
        for(Boite mot : b.composite){
            System.out.print(mot);
        }
        System.out.println();
    }
    
    void print(){
        for(BoiteComposite b : this.liste){
            this.printParagraphe(b);
            if(!(b.equals(this.liste.getLast()))) System.out.println();
                                                            //retour à la ligne
        }
    }
}