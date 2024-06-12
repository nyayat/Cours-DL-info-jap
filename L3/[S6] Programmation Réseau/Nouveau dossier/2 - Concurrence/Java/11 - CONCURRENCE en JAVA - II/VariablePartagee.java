import java.net.*;
import java.io.*;
import java.lang.*;

public class VariablePartagee {
    public int val;
    public boolean pretaecrire;

    public VariablePartagee(){
        val=0;
        pretaecrire=true;
    }

    public synchronized int lire(){
        try{
            while(pretaecrire==true){
                wait();
            }
            pretaecrire=true;
            notifyAll();
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return val;
    }

    public synchronized void ecrire(int v){
        try{
            while(pretaecrire==false){
                wait();
            }    
            pretaecrire=false;
            notifyAll();
            val=v;
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
