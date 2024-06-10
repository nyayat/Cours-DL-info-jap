//8.
public class TabEntiersTriable implements Triable{
    int[]t;
    
    TabEntiersTriable(int[]t){
        this.t=t;
    }
    
    @Override
    public void echange(int i, int j){
        int temp=t[i];
        t[i]=t[j];
        t[j]=temp;
    }
    
    @Override
    public boolean plusGrand(int i, int j){
        return t[i]>t[j];
    }
    
    @Override
    public int taille(){
        return this.t.length;
    }
    
    public String toString(){
        String res="{";
        for(int i=0; i<this.taille()-1; i++){
            res+=this.t[i]+", ";
        }
        res+=this.t[this.taille()-1]+"}";
        return res;
    }
}