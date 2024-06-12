import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

public class Main{
    //1.
    static void helloFromThreads(int n){
        for(int i=0; i<n; i++) new Thread(new HelloRunnable()).start();
    }
    
    //2.
    public static boolean isPrime(long n){
        if(n==0) return false;
        for(long i=2; i<(long)Math.sqrt(n); i++){
            if(n%i==0) return false;
        }
        return true;
    }
    
    public static boolean isPrime(long n, int t){
        Interval interval=new Interval(2, (long)Math.sqrt(n));
        LinkedList<IsPrimeRunnable> listR=new LinkedList<IsPrimeRunnable>();
        LinkedList<Thread> listT=new LinkedList<Thread>();
        for(Interval i : interval.diviser(t)) listR.add(new IsPrimeRunnable(n, i));
        Thread tmp;
        for(IsPrimeRunnable runnable : listR){
            tmp=new Thread(runnable);
            listT.addFirst(tmp);
            runnable.addThread(tmp);
        }
        for(IsPrimeRunnable runnable : listR)
            for(Thread thread : listT)
                if(!runnable.threads.contains(thread)) runnable.addThread(thread);
                else runnable.threads.remove(thread);
        for(Thread thread : listT) thread.start();
        for(Thread thread : listT){
            try{
                thread.join();
            }
            catch(InterruptedException e){}
        }
        for(IsPrimeRunnable runnable : listR)
            if(runnable.isNotPrime) return false;
        return true;
    }
    
    public static void main(String[] args){
        //1.
        //helloFromThreads(6);
        
        //2.
        /*long[] toTest=new long[]{2, 9999999929L, 1262182800679059439L, 8223335555577777779L, 9181531581341931811L};
        for(int j=0; j<toTest.length; j++){
            Instant start=Instant.now();
            System.out.println(isPrime(toTest[j]));
            Instant end=Instant.now();
            Duration timeElapsed=Duration.between(start, end);
            System.out.println("entier : "+toTest[j]+" --> "+timeElapsed);
            
            /*for(int i=1; i<33; i*=2){
                Instant start=Instant.now();
                System.out.println(isPrime(toTest[j], i));
                Instant end=Instant.now();
                Duration timeElapsed=Duration.between(start, end);
                System.out.println("entier : "+toTest[j]+" avec "+i+" threads --> "+timeElapsed);
            }*/
        //}
/*
version          2     9999999929L  1262182800679059439L  8223335555577777779L  9181531581341931811L
              premier    premier        non premier             premier               premier
séquentielle  0.0000      0.000            8.739                20.320                21.630
threads=1     0.0021      0.001            8.158                20.865                22.466
threads=2     0.0008      0.001            4.348                10.661                11.653
threads=4     0.0007      0.011            2.407                5.908                 16.531
threads=8     0.0002      0.000            1.566                4.081                 4.483
threads=16    0.0000      0.000            1.317                3.588                 3.990
threads=32    0.0034      0.000            1.257                3.553                 3.996
*/

        //3.
        /*CompteurTest c=new CompteurTest();
        
        class LocalThread extends Thread{
            public void run(){
                c.decrementerTest();
            }
        }
        
        Runnable r=new Runnable(){
            @Override
            public void run(){
                c.decrementerTest();
            }
        };
        
        LocalThread t1=new LocalThread();
        Thread t2=new Thread(r);
        Thread t3=new Thread(() -> c.incrementerTest());
        Thread t4=new Thread(c::incrementerTest);
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();*/
        
        //4.1
        /*ReadWriteLock lock=new ReadWriteLock();
        lock.acquireReaderPrivilege();
        lock.acquireReaderPrivilege();*/
        
        /*ReadWriteLock lock=new ReadWriteLock();
        lock.acquireReaderPrivilege();
        lock.acquireWriterPrivilege();*/
        
        /*ReadWriteLock lock=new ReadWriteLock();
        lock.acquireWriterPrivilege();
        lock.acquireReaderPrivilege();*/
        
        //4.3
        ThreadSafeReadWriteBox box=new ThreadSafeReadWriteBox("Init");
        new ThreadWrite("A", box).start();
        new ThreadWrite("B", box).start();
        for(int i=0; i<10; i++) new ThreadRead(box).start();
    }
    
    static class ThreadWrite extends Thread{
        String val;
        ThreadSafeReadWriteBox box;
        
        ThreadWrite(String v, ThreadSafeReadWriteBox b){
            val=v;
            box=b;
        }
        
        public void run(){
            box.setString(val);
        }
    }
    
    
    static class ThreadRead extends Thread{
        ThreadSafeReadWriteBox box;
        
        ThreadRead(ThreadSafeReadWriteBox b){
            box=b;
        }
        
        public void run(){
            System.out.println(box.getString());
        }
    }
}
