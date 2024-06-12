import java.util.LinkedList;
import java.util.List;

//2.
public class IsPrimeRunnable implements Runnable{
    long number;
    Interval interval;
    boolean isNotPrime=false;
    List<Thread> threads=new LinkedList<Thread>();
    
    IsPrimeRunnable(long n, Interval i){
        number=n;
        interval=i;
    }
    
    void addThread(Thread thread){
        threads.add(thread);
    }
    
    @Override
    public void run(){
        for(long i=interval.debut; i<interval.fin; i++){
            if(number%i==0){
                isNotPrime=true;
                for(Thread t : threads) t.interrupt();
                return;
            }
        }
    }
}