//2.
public class MyOptional<E>{
    private E e=null;
    
    //2.1
    boolean isPresent(){
        return e!=null;
    }
    
    E getValeur(){
        return e;
    }
    
    void setValeur(E valeur){
        e=valeur;
    }
    
    //2.2
    static void testMyOptional(int n){
        class ThreadPers extends Thread{
            MyOptional<Integer> entree, sortie;
            
            ThreadPers(MyOptional<Integer> e, MyOptional<Integer> s){
                entree=e;
                sortie=s;
            }
            
            public void run(){
                if(entree==null){
                    sortie.setValeur(2);
                    return;
                }
                
                if(sortie==null){
                    System.out.println(entree.getValeur());
                    return;
                }
                
                while(!entree.isPresent()){
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){}
                }
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){}
                sortie.setValeur(entree.getValeur()*2);
            }
        }
        
        MyOptional<Integer> tmpE=null;
        MyOptional<Integer> tmpS=new MyOptional<Integer>();
        ThreadPers[] threads=new ThreadPers[n];
        for(int i=0; i<n-1; i++){
            threads[i]=new ThreadPers(tmpE, tmpS);
            tmpE=tmpS;
            tmpS=new MyOptional<Integer>();
        }
        threads[n-1]=new ThreadPers(tmpE, null);
        
        for(int i=0; i<n; i++){
            threads[i].start();
            try{
                threads[i].join();
            }
            catch(InterruptedException e){}
        }
    }
    
    //2.3
    public static void main(String[] args){
        testMyOptional(4); //2^3=8
    }
}