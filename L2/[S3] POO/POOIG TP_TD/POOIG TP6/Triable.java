public interface Triable {
    void echange(int i, int j);  //échange t[i] et t[j] dans le tableau t
    
    boolean plusGrand(int i, int j);  //true if t[i]>t[j]
    
    int taille();  //nombre d'élément à trier
    
    //7.
    static void triBulles(Triable t){
        boolean change=false;
        do{
            change=false;
            for(int i=0 ;i<t.taille()-1; i++){
                if(t.plusGrand(i, i+1)){
                    t.echange(i, i+1);
                    change=true;
                }
            }
        }
        while(change);
    }
}