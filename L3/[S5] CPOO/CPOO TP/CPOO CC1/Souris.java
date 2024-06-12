import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

//2.
public class Souris{
    //2.1
    boolean isFemale;
    int robustesse; //entre 1 et 3
    
    Souris(boolean isF, int r){
        if(r<1 || r>3) throw new IllegalArgumentException();
        isFemale=isF;
        robustesse=r;
    }
    
    public String toString(){
        return (isFemale?"Femelle":"Mâle")+" de robustesse "+robustesse;
    }
    
    //2.2
    public static List<Souris> cree(Supplier<Souris> sup, int longueur){
        List<Souris> res=new LinkedList<Souris>();
        for(int i=0; i<longueur; i++) res.add(sup.get());
        return res;
    }
    
    //2.3
    public static void elimination(Predicate<Souris> pre, List<Souris> l){
        int n=(int)(Math.random()*l.size());
        if(pre.test(l.get(n))) l.remove(n);
    }
    
    
    public static void main(String[] args){
        //2.2
        List<Souris> l=cree(new Supplier<Souris>(){
            @Override
            public Souris get(){
                return new Souris((Math.random()>0.5), (int)(Math.random()*3)+1);
            }
        }, 30);
        l.forEach(System.out::println);
        
        //2.3
        Predicate<Souris> p=new Predicate<Souris>(){
            int erasedN=0;
            
            @Override
            public boolean test(Souris t){
                if(t.isFemale && Math.random()*100<30/t.robustesse
                || !t.isFemale && Math.random()*100<40/t.robustesse){
                    System.out.println(++erasedN+" souris éliminée(s).");
                    return true;
                }
                return false;
            }
        };
        for(int i=0; i<30; i++) elimination(p, l);
        l.forEach(System.out::println);
    }
}