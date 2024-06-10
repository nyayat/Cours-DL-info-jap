import java.io.*;
import java.util.ArrayList;
import java.lang.*;

public class Arbre {
    //1.
    public class Noeud{  //classe interne
        String nom;
        int taille;
        boolean repertoire;
        ArrayList<Noeud> fils;
        
        //2.
        Noeud(File f) throws FileNotFoundException{
            if(!f.exists()) throw new FileNotFoundException();
            else{
                nom=f.getName();
                taille=(int)f.length();
                repertoire=f.isDirectory();
                if(repertoire){
                    fils=new ArrayList<Noeud>();
                    for(int i=0; i<f.listFiles().length; i++){
                        fils.add(new Noeud(f.listFiles()[i]));
                    }
                }
            }
        }
        
        //3.
        void afficher(int tab){
            System.out.println(this.nom+" ["+this.taille+"]");
            if(this.fils!=null){
                for(Noeud n : this.fils){
                    for(int i=0; i<tab+1; i++) System.out.print("  ");
                    n.afficher(tab+1);
                }
            }
        }
        
        //4.
        void map(StringTransformation t){
            if(!this.repertoire)this.nom=t.transf(this.nom);
            else this.fils.forEach((noeud)-> noeud.map(t));
        }
        
        //5.
        boolean memeExtension(String extension){
            if(this.nom.length()<extension.length()) return false;
            if(this.nom.substring(this.nom.length()-extension.length()).equals(extension))
                return true;
            return false;
        }

        void traverser(String extension){
            if(!repertoire){
                if(this.memeExtension(extension))
                    System.out.println(this.nom+" ["+this.taille+"]");
            }
            else this.fils.forEach((noeud)->noeud.traverser(extension));
        }
        
        void supprimer(String extension) throws UnableToDeleteFileException{
            int j=0;
            while(j<this.fils.size()){  //parcours du dossier
                Noeud n=this.fils.get(j);
                if(!n.repertoire){  //fichier
                    if(n.memeExtension(extension)){
                        boolean deleted=this.fils.remove(n);
                        if(!deleted) throw new UnableToDeleteFileException();
                    }
                    else j++;
                }
                else{  //dossier
                    n.supprimer(extension);
                    j++;
                }
            }
        }
    }  //fin de la classe interne
    
    Noeud racine;
    
    //2.
    Arbre(String chemin) throws FileNotFoundException{
        File f=new File(chemin);
        racine=new Noeud(f);  //soulève déjà l'erreur si f n'existe pas
    }
    
    //3.
    void afficher(){
        if(this.racine!=null) this.racine.afficher(0);
        else System.out.println("Vide.");
    }
    
    //4.
    void map(StringTransformation t){
        if(this.racine!=null) this.racine.map(t);
    }
    
    //5.
    void traverser(String extension){
        if(this.racine!=null) this.racine.traverser(extension);
        else System.out.println("Vide depuis le début.");
    }
    
    void supprimer(String extension) throws UnableToDeleteFileException{
        if(this.racine==null) return;
        if(this.racine.repertoire){  //racine est un dossier
            int i=0;
            while(i<this.racine.fils.size()){  //parcours du dossier
                Noeud n=this.racine.fils.get(i);
                if(!n.repertoire){  //fichier
                    if(n.memeExtension(extension)){
                        boolean deleted=this.racine.fils.remove(n);
                        if(!deleted) throw new UnableToDeleteFileException();
                    }
                    else i++;
                }
                else{  //dossier
                    n.supprimer(extension);
                    i++;  
                }
            }
        }
        else if(this.racine.memeExtension(extension)) this.racine=null;
                                                    //racine est un fichier
    }
}