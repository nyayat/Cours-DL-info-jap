//1.
public class Exo1{
    //1.2
    static int calcule(int t){
        Compteur c=new Compteur();
        
        Thread[] threads=new Thread[t];
        for(int i=0; i<t; i++){  //id=i, qui sera donc invariant
            int j=i;  //pour effective final
            
            //manière 1
            //threads[i]=new Thread(() -> c.incremente(j));
            
            //manière 2
            threads[i]=new Thread(new Runnable(){
                @Override
                public void run(){
                    c.incremente(j);
                }
            });
        }
        
        for(int i=0; i<t; i++){
            threads[i].start();
            try{
                //on lie les threads pour qu'ils s'attendent entre eux
                threads[i].join();
            }
            catch(InterruptedException e){}
        }
        
        return c.getValeur();
    }
    
    public static void main(String[] args){
        System.out.println(calcule(32));
        System.out.println(calcule(64));
    }
}