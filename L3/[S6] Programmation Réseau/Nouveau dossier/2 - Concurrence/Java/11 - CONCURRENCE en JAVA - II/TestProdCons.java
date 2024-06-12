import java.net.*;
import java.io.*;
import java.lang.*;

public class TestProdCons{
    public static void main(String[] args){
        try{
            VariablePartagee var=new VariablePartagee();
            CodeProducteur prod=new CodeProducteur(var);
            CodeConsommateur cons=new CodeConsommateur(var);
            Thread []t=new Thread[20];
            for(int i=0; i<10; i++){
                t[i]=new Thread(prod);
            }
            for(int i=10; i<20; i++){
                t[i]=new Thread(cons);
            }
            for(int i=0; i<20; i++){
                t[i].start();
            }
            for(int i=0; i<20; i++){
                t[i].join();
            }
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
