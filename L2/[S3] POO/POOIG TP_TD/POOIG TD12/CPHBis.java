import java.util.LinkedList;

//2.
public class CPHBis {
    LinkedList<Pair> list=new LinkedList<Pair>();
    
    class Pair<T>{
        private T before;
        private T after;
        
        Pair(T x, T y){
            before=x;
            after=y;
        }
    }
         
    <T> void infoChangeBis(T x, T y){
        list.add(new Pair(x, y));
    }
    
    public String toString(){
        String res="";
        for(Pair p : list) res+=p.before+" -> "+p.after+"\n";
        return res;
    }
}