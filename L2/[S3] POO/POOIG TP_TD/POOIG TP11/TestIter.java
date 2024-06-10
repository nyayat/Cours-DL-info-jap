import java.util.Iterator;

public class TestIter<E> implements Iterator<E> {
    //1.
    private E[]tableau;
    private int index;
    
    TestIter(E[]t){
        tableau=t;
        index=0;
    }
    
    public boolean hasNext(){
        return (index+1<tableau.length && tableau[index+1]!=null);
    }
    
    public E next(){
        if(this.hasNext()){
            index++;
            return tableau[index];
        }
        return null;
    }
}