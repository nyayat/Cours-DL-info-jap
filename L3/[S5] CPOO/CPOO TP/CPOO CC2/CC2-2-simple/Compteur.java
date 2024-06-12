//1.
public class Compteur{
    private int n;
    
    //1.1
    synchronized void incremente(){
        n++;
    }
    
    synchronized void decremente(){
        n--;
    }
    
    synchronized int getValeur(){
        return n;
    }
}