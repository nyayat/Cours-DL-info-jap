//1.
public class Compteur{
    private int n;
    
    //1.1
    synchronized void incremente(int v){
        n+=v;
    }
    
    synchronized int getValeur(){
        return n;
    }
}