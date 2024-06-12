//3.
public class Compteur{
    private int compte=0;
    
    synchronized int getCompte(){
        return compte;
    }
    
    synchronized void incrementer(){
        compte++;
    }
    
    synchronized void decrementer(){
        compte--;
    }
}