//1.
public class Exo1{
    //1.2
    static int calcule(int n, int t){
        Compteur c=new Compteur();
        Thread[] threads=new Thread[t];
        for(int i=0; i<t; i++){
            if(i%2==0) threads[i]=new Thread(() ->{
                for(int j=0; j<n; j++) c.incremente();
            });
            else threads[i]=new Thread(new Runnable(){
                @Override
                public void run(){
                    for(int j=0; j<n; j++) c.decremente();
                }
            });
        }
        for(int i=0; i<t; i++) threads[i].start();
        for(int i=0; i<t; i++){
            try{
                //on lie les threads pour qu'ils s'attendent entre eux
                threads[i].join();
            }
            catch(InterruptedException e){}
        }
        return c.getValeur();
    }
    
    //1.3
    public static void main(String[] args){
        System.out.println(calcule(1000, 4));
        System.out.println(calcule(1000, 5));
    }
}