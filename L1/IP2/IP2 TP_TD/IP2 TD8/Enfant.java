public class Enfant {
    //1.1
    String nom;
    int nbTob;
    
    //1.2
    Enfant suivant;
    Enfant precedent;
    
    //1.1
    Enfant(String nom, int nb){
        this.nom=nom;
        this.nbTob=nb;
        this.precedent=this;
        this.suivant=this;
    }
    
    //1.2
    Enfant(Enfant e){
        this.precedent=this;
        this.suivant=this;
    }
    
    //2.3
    Enfant(String nom, int nb, Enfant s, Enfant p){
        this.nom=nom;
        this.nbTob=nb;
        this.precedent=p;
        this.suivant=s;
    }
    
    //2.6
    int unTour(){
        return ++this.nbTob;
    }
}