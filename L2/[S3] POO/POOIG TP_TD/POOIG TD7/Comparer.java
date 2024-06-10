//4.
public class Comparer {
    public static void main(String[]args){
        int[]a={10, 30, 0, -2, 100, -9};
        afficher(a);
        
        //4.2.4
        trier(a, new Comparateur(){
            public boolean plusGrand(int x, int y){ return x>y;}
        });
        afficher(a);
        
        //4.2.b
        trier(a, new Comparateur(){
            public boolean plusGrand(int x, int y){ return x<y;}
        });
        afficher(a);
        
        //4.2.c        
        trier(a, new Comparateur(){
            public boolean plusGrand(int x, int y){ return Math.abs(x)>Math.abs(y);}
        });
        afficher(a);
    }
    
    static void afficher(int[]x){
        for(int i=0; i<x.length; i++){
            System.out.println(x[i]+" ");
        }
        System.out.println();
    }
    
    //4.1
    static void trier(int[]x, Comparateur c){
        boolean change=false;
        do{
            change=false;
            for(int i=0; i<x.length-1 ; i++){
                if(c.plusGrand(x[i], x[i+1])){
                    int temp=x[i];
                    x[i]=x[i+1];
                    x[i+1]=temp;
                    change=true;
                }
            }
        }while(change);
    }
}