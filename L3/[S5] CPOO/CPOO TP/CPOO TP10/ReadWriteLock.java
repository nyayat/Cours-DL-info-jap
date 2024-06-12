//4.
public class ReadWriteLock{
    private boolean authorizedWriter=false;
    private int nbReader=0;
    
    //4.1
    synchronized void dropReaderPrivilege(){
        if(nbReader>0) nbReader--;
        notify();
    }
    
    synchronized void dropWriterPrivilege(){
        authorizedWriter=false;
        notifyAll();
    }
    
    synchronized void acquireReaderPrivilege() throws InterruptedException{
        while(authorizedWriter) wait(1);
        nbReader++;
    }
    
    synchronized void acquireWriterPrivilege() throws InterruptedException{
        while(nbReader>0 || authorizedWriter) wait(1);
        authorizedWriter=true;
    }
}