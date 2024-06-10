import java.util.LinkedList;
import java.util.ArrayList;

public class Mediatheque {
    //3.
    private LinkedList<Media> mediatheque;
    
    //3.1
    Mediatheque(){
        mediatheque=new LinkedList<Media>();
    }
    
    //3.2
    void ajouter(Media doc){
        if(this.mediatheque.isEmpty()) this.mediatheque.addFirst(doc);
        else{
            int i=0;
            while(i<this.mediatheque.size() 
                 && this.mediatheque.get(i).plusPetit(doc)) i++;
            this.mediatheque.add(i, doc);
        }
    }
    
    //3.3
    void affiche(){
        if(this.mediatheque.isEmpty()) 
            System.out.println("Il n'y a rien dans la médiathèque.");
        else{
            int i=0;
            while(i<this.mediatheque.size()){
                System.out.println(this.mediatheque.get(i));
                i++;
            }
        }
    }
    
    //3.4
    void tousLesDictionnaires(){
        int nb=0;
        for(int i=0; i<this.mediatheque.size(); i++){
            if(this.mediatheque.get(i) instanceof Dictionnaire){
                System.out.println(this.mediatheque.get(i));
                nb++;
            }
        }
        if(nb==0) System.out.println("Il n'y a pas de dictionnaire.");
    }
    
    //5.3
    ArrayList<Media> recherche(Predicat p){
        ArrayList<Media> res=new ArrayList<Media>();
        int i=0;
        while(i<this.mediatheque.size()){
            Media temp=this.mediatheque.get(i);
            if(p.estVrai(temp)) res.add(temp);
            i++;
        }
        return res;
    }
    
    public static void main(String[]a){
        //3.3
        Livre l0=new Livre("La déchéance d'un homme", "Dazai Osamu", 196);
        Livre l1=new Livre("Cours, Melos", "Dazai Osamu", 264);
        Livre l2=new Livre("Je suis un chat", "Natsume Sōseki", 438);
        Mediatheque m=new Mediatheque();
        //m.affiche();  //vide
        
        m.ajouter(l2);        
        m.ajouter(l1);
        m.ajouter(l0);
        //m.affiche();  //affiche 0 puis 1 puis 2
        
        //3.4
        Dictionnaire d3=new Dictionnaire("Larousse", "français", 2);
        Dictionnaire d4=new Dictionnaire("Robert", "français", 5);
        //m.tousLesDictionnaires();  //vide
        
        m.ajouter(d4);
        m.ajouter(d3);
        //m.tousLesDictionnaires();  //affiche 3 puis 4
        
        //4.2
        Livre l5=new Livre("Rashōmon", "Akutagawa Ryūnosuke", 39);
        m.ajouter(l5);
        DictionnaireBilingue d6=new DictionnaireBilingue("Linguee", "français", "anglais", 1);
        m.ajouter(d6);
        //m.affiche();  // 3, 4, 6, 0, 1, 2, 5  --> c'est bon
        
        //4.4
        //m.affiche();  // 3, 4, 6, 0, 1, 2, 5  --> c'est bon si on n'enlève pas //4.1
        
        /*Si on enlève la méthode Livre.plusPetit(Media doc) (//4.1), les livres
        ne seront plus tous à droite, donc "plus grands" que les autres médias.*/
        
        //4.5
        //Méthode dynamique.
        
        //4.6
        //m.affiche();  // 3, 4, 6, 0, 1, 2, 5  --> c'est bon
        
        //5.6
        Predicat p1=new TitreCommenceParS();
        Predicat p2=new EstUnLivre();
        Et p3=new Et(p1, p2);
        //System.out.println(p3.estVrai(l0));  //affiche false
        //System.out.println(p3.estVrai(d3));  //affiche false
        
        Dictionnaire d7=new Dictionnaire("Scribens", "français", 4);
        //System.out.println(p3.estVrai(d7));    //affiche false
        
        Manga m8=new Manga("Shaman King", "Takei Hiroyuki", 190,
                            "Taikei Hiroyuki", "Gesell Sébastien", 1);
        //System.out.println(p3.estVrai(m8));  //affiche true
        
        Livre l9=new Livre("Sword Art Online 9", "Kawahara Reki", 510);
        //System.out.println(p3.estVrai(l9));  //affiche true
        
        //5.3
        ArrayList<Media> tab=m.recherche(p2);
        /*for(int i=0; i<tab.size(); i++){
            System.out.println(tab.get(i).getNumero());
        }*/
        //affiche 0, 1, 2, 5
    }
}