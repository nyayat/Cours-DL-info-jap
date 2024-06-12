//2.
public interface Sequencable{
    public int longueur();
    public Comparable get(int i);
    public void echange(int i, int j);
    
    //2.1
    default void affiche(){
        for(int i=0; i<longueur(); i++){
            System.out.print(get(i)+" ");
        }
        System.out.println();
    }
    
    //2.2
    default void triBulle(){
        boolean change;
        do{
            change=false;
            for(int i=0; i<longueur()-1; i++){
                if(get(i).estPlusGrand(get(i+1))){
                    echange(i, i+1);
                    change=true;
                }
            }
        }
        while(change);
    }
}