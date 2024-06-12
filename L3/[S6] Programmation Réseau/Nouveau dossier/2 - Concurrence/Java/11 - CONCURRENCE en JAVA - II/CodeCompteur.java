import java.net.*;
import java.io.*;
import java.lang.*;

public class CodeCompteur implements Runnable{

    private Compteur c;
    
    public CodeCompteur(Compteur _c){
        this.c=_c;
    }
    
    public void run(){
        for(int i=0; i<1000; i++){
            synchronized(c){
                c.setValeur(c.getValeur()+1);
            }
        }
    }
}
