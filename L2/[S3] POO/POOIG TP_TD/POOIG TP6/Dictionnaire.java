//10.
public class Dictionnaire implements Triable{
    String[]t;
    
    Dictionnaire(String[]t){
        this.t=t;
    }
    
    @Override
    public void echange(int i, int j){
        String temp=t[i];
        t[i]=t[j];
        t[j]=temp;
    }
    
    @Override
    public boolean plusGrand(int i, int j){  //par défaut, false si t[i]==t[j]
        return (t[i].compareToIgnoreCase(t[j])>0);
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