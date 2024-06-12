//1.
public class Enseignant extends Personne{
    private String matiere;
    
    Enseignant(String nom, String matiere){
        super(nom);
        this.matiere=matiere;
    }
    
    void presenteToi(){
        System.out.println("Je suis "+nom+", enseignant de "+matiere);
    }
    
    void enseigne(){
        System.out.println(matiere+" is beautiful");
    }
}