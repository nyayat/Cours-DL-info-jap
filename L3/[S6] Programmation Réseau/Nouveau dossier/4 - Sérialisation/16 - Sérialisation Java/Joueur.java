public class Joueur{

    public String nom;
    public int age;

    public Joueur(){
        nom="";
        age=0;
    }

    public Joueur(String _nom, int _age){
        this.nom=_nom;
        this.age=_age;
    }

    public void setNom(String _nom){
        this.nom=_nom;
    }

    public String getNom(){
        return this.nom;
    }

    public void setAge(int  _age){
        this.age=_age;
    }

    public int getAge(){
        return this.age;
    }

    public String toString(){
        return(nom+": "+age);
    }
    
}
