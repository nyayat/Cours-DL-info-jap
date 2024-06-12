import java.io.*;

public class Chien implements Serializable{
    private String nom;

    public Chien(String _nom){
        this.nom=_nom;
    }

    public String toString(){
        return nom;
    }
}
