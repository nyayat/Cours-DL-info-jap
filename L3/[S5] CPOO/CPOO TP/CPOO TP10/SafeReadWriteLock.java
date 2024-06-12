//4.4
public class SafeReadWriteLock{
    private final ReadWriteLock lock=new ReadWriteLock();
    
    void read(Runnable r){
        try{
            lock.acquireReaderPrivilege();
            r.run();
        }
        catch(InterruptedException e){}
        finally{
            lock.dropReaderPrivilege();
        }
    }
    
    void write(Runnable r){
        try{
            lock.acquireWriterPrivilege();
            r.run();
        }
        catch(InterruptedException e){}
        finally{
            lock.dropWriterPrivilege();
        }
    }
}