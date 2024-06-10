public class Pile<T> {
    int indLast;
    Object[] t;
    
    public Pile(int taille){
        if(taille<0) throw new IllegalArgumentException();
        this.t=new Object[taille];
        indLast=-1;
    }
    
    public boolean estVide(){
        return indLast==-1;
    }
    
    public boolean estPleine(){
        return indLast==t.length-1;
    }
    
    public void empile(T e) throws Exception{
        if(this.estPleine()) throw new Exception();
        t[indLast]=e;
        indLast++;
    }
    
    public T getSommet() throws Exception{
        if(this.estVide()) throw new Exception();
        return (T)t[indLast];
    }
    
    public T depile() throws Exception{
        T res=this.getSommet();
        this.t[indLast]=null;
        indLast--;
        return res;
    }
}