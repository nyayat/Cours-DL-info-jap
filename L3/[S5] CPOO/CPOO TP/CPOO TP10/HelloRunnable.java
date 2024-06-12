//1.
public class HelloRunnable implements Runnable{
    static int nbID=0;
    private int id;
    static int countM=0;
    
    HelloRunnable(){
        id=++nbID;
    }
    
    public synchronized void run(){
        countM++; //incrémente avant affichage pour limiter problèmes de synchro
        if(countM<4) System.out.println("Thread "+id+" : Hello World !");
    }
}