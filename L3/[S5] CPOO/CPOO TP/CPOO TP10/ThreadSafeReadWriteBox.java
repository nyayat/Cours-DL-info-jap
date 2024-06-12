//4.
public class ThreadSafeReadWriteBox{
    private String string;
    private ReadWriteLock lock;
    
    //4.2
    ThreadSafeReadWriteBox(String s){
        string=s;
        lock=new ReadWriteLock();
    }
    
    static void attend(int x){
        try{
            Thread.sleep(x);
        }
        catch(InterruptedException e){
            System.out.println("InterruptedException");
        }
    }
    
    static void attendAlea(int x){
        attend((int)(x*Math.random()));
    }
    
    void setString(String s){
        try{
            lock.acquireWriterPrivilege();
            attend(1000);
            string=s;
        }
        catch(InterruptedException e){
            System.out.println("InterruptedException");
        }
        lock.dropWriterPrivilege();
    }
    
    String getString(){
        try{
            lock.acquireReaderPrivilege();
            attendAlea(2000);
            return string;
        }
        catch(InterruptedException e){
            System.out.println("InterruptedException");
            return "";
        }
        finally{
            lock.dropReaderPrivilege();
        }
    }
}