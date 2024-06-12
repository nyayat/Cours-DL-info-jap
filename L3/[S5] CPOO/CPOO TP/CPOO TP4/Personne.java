//1.
public class Personne{
    final String nom;
    
    Personne(String nom){
        this.nom=nom;
    }
    
    void presenteToi(){
        System.out.println("Je suis "+nom);
    }
    
    void chante(){
        System.out.println("la-la-la");
    }
}