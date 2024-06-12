import java.io.*;

public class Personne implements Serializable {
    private String nom;
    private Chien chien;

    public Personne(String _nom,Chien _chien){
        this.nom=_nom;
        this.chien=_chien;
    }

    public String toString(){
        return (nom+", "+chien.toString());
    }

    public Chien getChien(){
        return chien;
    }
}
