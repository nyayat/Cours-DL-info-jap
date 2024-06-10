import java.util.*;

public class ChangePseudoHistory implements Observer{
    LinkedList<Pair> list=new LinkedList<Pair>();
    
    class Pair{
        private String before;
        private String after;
        
        Pair(String x, String y){
            before=x;
            after=y;
        }
    }
         
    void infoChange(String x, String y){
        list.add(new Pair(x, y));
    }
    
    public String toString(){
        String res="";
        for(Pair p : list) res+=p.before+" -> "+p.after+"\n";
        return res;
    }
    
    //3.
    public void update(Observable o, Object arg){
        if(!(o instanceof Pseudo)) throw new IllegalArgumentException();
        if(!(arg instanceof String)) throw new IllegalArgumentException();
        list.add(new Pair((String)arg, ((Pseudo)o).getContent()));
    }
}