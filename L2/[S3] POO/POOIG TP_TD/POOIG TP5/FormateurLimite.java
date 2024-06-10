import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;

public class FormateurLimite {
    //5.
    int longueurMax;
    LinkedList<LinkedList<BoiteComposite>> texte;
    Scanner sc;
    //LinkedList<BoiteComposite> liste;  //plus utile ici
    
    FormateurLimite(String fichier, int longueurMax){
        this.longueurMax=longueurMax;
        sc=null;
        try{
            sc=new Scanner(new File(fichier));
        }
        catch(Exception e){
            System.out.println("Erreur lors de l'ouverture du fichier.");
            e.printStackTrace();
            System.exit(1);
        }
        //liste=new LinkedList<BoiteComposite>();  //plus utile
        texte=new LinkedList<LinkedList<BoiteComposite>>();
    }
    
    private LinkedList<BoiteComposite> readParagraphe(){
        LinkedList<BoiteComposite> res=new LinkedList<BoiteComposite>();
        String temp=sc.next();
        Scanner s=new Scanner(temp);
        int delimiteur=0;
        BoiteComposite ligne=new BoiteComposite();
        while(s.hasNext()){
            BoiteMot suivant=new BoiteMot(s.next());
            if(delimiteur+suivant.length()<=longueurMax){
            //on ne compte pas encore l'espace dans la taille car on peut l'enlever
                ligne.addBoite(suivant);
                ligne.addBoite(new BoiteEspace());
                delimiteur+=suivant.length()+1;  //+1 pour l'espace
            }
            else{  //dépasse la ligne
                ligne.deleteLastSpace();
                //on efface l'espace en trop de la BoiteComposite, avant de reset
                res.add(ligne);
                
                //reset :
                delimiteur=suivant.length()+1;  //+1 pour l'espace
                ligne=new BoiteComposite();
                ligne.addBoite(suivant);
                ligne.addBoite(new BoiteEspace());
            }
        }
        ligne.deleteLastSpace();
                      //on a forcément un espace dans la dernière BoiteComposite
        if(!ligne.isEmpty()) res.add(ligne);  //on n'ajoute pas la ligne si vide
        return res;
    }
    
    /*On ne passe pas à une nouvelle boîte si la courante est vide,
        car on ne dépasse pas longueurMax.*/
    
    void read(){
        sc.useDelimiter("\n\\s*\n");  //entre deux retours à la ligne
        while(sc.hasNext()){
            LinkedList<BoiteComposite> temp=readParagraphe();
            texte.add(temp);
        }
    }
    
    private void printLigne(BoiteComposite b){  //affiche une ligne
        for(Boite mot : b.composite){  //un espace=un "mot" ici aussi
            System.out.print(mot);
        }
        System.out.println();  //dans tous les cas on retourne à la ligne
    }
    
    private void printParagraphe(LinkedList<BoiteComposite> b){  //affiche un paragraphe
        for(BoiteComposite bC : b){
            printLigne(bC);
        }
    }
    
    void print(){
        for(LinkedList<BoiteComposite> b : this.texte){
            this.printParagraphe(b);
            if(!(b.equals(this.texte.getLast()))) System.out.println();
            //retour à la ligne
        }
    }
    
    //10.
    private void printLigneJustifie(BoiteComposite b){
        System.out.println(b.toString(longueurMax));
                                    //dans tous les cas on retourne à la ligne
    }
    
    private void printParagrapheJustifie(LinkedList<BoiteComposite> b){
        for(BoiteComposite bC : b){
            printLigneJustifie(bC);
        }
    }
    
    void printJustifie(){
        for(LinkedList<BoiteComposite> b : this.texte){
            this.printParagrapheJustifie(b);
            if(!(b.equals(this.texte.getLast()))) System.out.println();
            //retour à la ligne
        }
    }
    
    //11.
    private void printParagrapheJustifieBis(LinkedList<BoiteComposite> b){
        for(BoiteComposite bC : b){
            if(bC==b.getLast()) printLigne(bC);
            else printLigneJustifie(bC);
        }
    }
    
    void printJustifieBis(){
        for(LinkedList<BoiteComposite> b : this.texte){
            this.printParagrapheJustifieBis(b);
            if(!(b.equals(this.texte.getLast()))) System.out.println();
            //retour à la ligne
        }
    }
}