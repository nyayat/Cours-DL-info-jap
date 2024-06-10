public class Employe {
    //1.1
    private final String nom;
    private int salaire;
    
    Employe(String n, int s){
        nom=n;
        salaire=s;
    }
    
    String getNom(){
        return this.nom;
    }
    
    int getSalaire(){
        return this.salaire;
    }
    
    void setSalaire(int s){
        this.salaire=s;
    }
    
    void affichage(){
        System.out.println(this.nom+" a un salaire de "+this.salaire+" euros.");
    }
}