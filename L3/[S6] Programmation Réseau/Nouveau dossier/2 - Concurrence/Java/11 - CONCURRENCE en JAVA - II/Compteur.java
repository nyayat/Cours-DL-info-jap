import java.net.*;
import java.io.*;
import java.lang.*;

public class Compteur{

    private int valeur;
    
    public Compteur(){
        valeur=0;
    }
    
    public int getValeur(){
        return valeur;
    }

    public void setValeur(int v){
        valeur=v;
    }
}
