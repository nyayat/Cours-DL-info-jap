//3.
public class Datas {
    private final static int SIZE=16;
    private Integer[]monTableau=new Integer[SIZE];
    
    public Datas(){
        for(int i=0; i<SIZE; i++){
            monTableau[i]=Integer.valueOf(i);
        }
    }
    
    private interface DatasIterator extends java.util.Iterator<Integer>{}
    
    //3.1
    private class EvenIterator implements DatasIterator{
        int i=0;
        
        public boolean hasNext(){
            return(i<SIZE);
        }
        
        public Integer next(){
            Integer res=monTableau[i];
            i+=2;
            return res;
        }
    }
    
    public void printEven(){
        print(new EvenIterator());
    }
    
    //3.2
    public void printOdd(){
        class OddIterator implements DatasIterator{
            int i=1;
            
            public boolean hasNext(){
                return(i<SIZE);
            }

            public Integer next(){
                Integer res=monTableau[i];
                i+=2;
                return res;
            }
        }
        print(new OddIterator());
    }
    
    //3.3
    public void printBackwards(){
        DatasIterator it=new DatasIterator(){
            int i=SIZE-1;

            public boolean hasNext(){
                return(i>=0);
            }

            public Integer next(){
                Integer res=monTableau[i];
                i--;
                return res;
            }
        };
        print(it);
    }
    
    private void print(DatasIterator i){
        while(i.hasNext()) System.out.print(i.next()+" ");
        System.out.println();
    }
    
    public static void main(String[]s){
        Datas d=new Datas();
        d.printEven();
        d.printOdd();
        d.printBackwards();
    }
}