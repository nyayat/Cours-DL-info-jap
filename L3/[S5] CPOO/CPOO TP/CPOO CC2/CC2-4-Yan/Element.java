//2.
public class Element implements Runnable{
    String nom;
    int num;
    static Chambre c=new Chambre();
    
    Element(String s, int n){
        nom=s;
        num=n;
    }
    
    @Override
    public void run(){
        c.toctoc(this);
    }
    
    //2.3
    public static class Chambre{
        int nbElementsHere=0;
        int nbChanged=0;
        Element[] presents=new Element[2];
        
        int toctoc(Element x){
            if(nbElementsHere>1){
                System.out.println(x.nom+" "+x.num+" constate que la chambre est pleine.");
                try{
                    wait();
                }
                catch(InterruptedException e){}
            }
            if(presents[0]==null){
                presents[0]=x;
                nbElementsHere++;
                waitfull();
                return 0;
            }
            presents[1]=x;
            nbElementsHere++;
            return 1;
        }
        
        void waitfull(){
            while(nbElementsHere<2){
                try{
                    wait();
                }
                catch(InterruptedException e){}
            }
        }
        
        boolean reaction1(){
            return presents[0].nom.equals("H") && presents[1].nom.equals("H");
        }
        
        boolean reaction2(){
            return presents[0].nom.equals("O") && presents[1].nom.equals("O");
        }
        
        /*String newName(int nbArrived){
            
        }*/
    }
}