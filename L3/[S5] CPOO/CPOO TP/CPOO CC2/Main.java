import java.util.LinkedList;

public class Main{
    public static void main(String[] args) throws InterruptedException{
        //1.
        /*Thread t1=new Thread(new Messagerie("nom1", 0));
        Thread t2=new Thread(new Messagerie("nom2", 1));
        t1.start();
        t2.start();*/
        
        //2.4
        Bureau b=new Bureau(30);
        LinkedList<Thread> threads=new LinkedList<Thread>();
        for(Electeur e : b.electeurs) threads.add(new Thread(e));
        for(Thread t : threads) t.start();
    }
}