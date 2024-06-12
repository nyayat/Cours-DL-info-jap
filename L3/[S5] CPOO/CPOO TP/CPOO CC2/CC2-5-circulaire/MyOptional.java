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
    static class ThreadPers extends Thread{
        MyOptional<Integer> entree, sortie;
        final int id;

        ThreadPers(MyOptional<Integer> e, MyOptional<Integer> s, int id){
            entree=e;
            sortie=s;
            this.id=id;
        }

        public void run(){
            if(id!=0){
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
                return;
            }
            
            sortie.setValeur(2);
            while(!entree.isPresent()){
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){}
            }
            System.out.println(entree.getValeur());
        }
    }
    
    static void testMyOptional(int n){
        ThreadPers[] threads=new ThreadPers[n];
        MyOptional<Integer> tmpE=new MyOptional<Integer>();
        MyOptional<Integer> tmpS=new MyOptional<Integer>();
        for(int i=0; i<n-1; i++){
            threads[i]=new ThreadPers(tmpE, tmpS, i);
            tmpE=tmpS;
            tmpS=new MyOptional<Integer>();
        }
        threads[n-1]=new ThreadPers(tmpE, threads[0].entree, n-1);
        
        for(int i=0; i<n; i++) threads[i].start();//pas de join sinon blocage
    }
    
    //2.3
    public static void main(String[] args){
        testMyOptional(4);//2^4=16
    }
}