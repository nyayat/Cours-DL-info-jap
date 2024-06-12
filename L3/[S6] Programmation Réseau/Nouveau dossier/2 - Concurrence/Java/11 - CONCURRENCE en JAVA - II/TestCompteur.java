import java.lang.*;
import java.io.*;

public class TestCompteur {
    public static void main(String[] args){
        try{
            Compteur c=new Compteur();
            CodeCompteur code=new CodeCompteur(c);
            Thread []t=new Thread[20];
            for(int i=0; i<20; i++){
                t[i]=new Thread(code);
            }
            for(int i=0; i<20; i++){
                t[i].start();
            }
            for(int i=0; i<20; i++){
                t[i].join();
            }
            System.out.println("Tout est fini");
            System.out.println("Le compteur vaut "+c.getValeur());
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
