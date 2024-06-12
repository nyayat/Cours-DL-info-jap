//2.
public class MotComparable implements Comparable{
    //2.3
    private String content;
    
    MotComparable(String s){
        content=s;
    }
    
    public String toString(){
        return content;
    }
    
    public Object value(){
        return content;
    }
    
    public boolean estPlusGrand(Comparable i){
        if(!(i.value() instanceof String)) throw new IllegalArgumentException();
        return content.compareTo((String)i.value())>0;
    }
}