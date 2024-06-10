import java.util.Vector;

public class Test{
    static <C> void affiche(Vector<C> vector){
        for(C v : vector) System.out.print(v.toString());
    }
    
    static void afficheBis(Vector<?> vector){
        for(Object v : vector) System.out.print(v.toString());
    }
    
    static <K, T extends AUneClef> int compteElement(K clef, Vector<T> v){
        int res=0;
        for(T temp : v){
            if(temp.getClef()==clef) res++;
        }
        return res;
    }
    
    static <K extends Number, T extends AUneClef<K>> double sommeClefs(Vector<T> v){
        double res=0;
        for(T t : v) res+=(double)t.getClef();
        return res;
    }
    
    static <U, T extends U> Vector<U> convertit(Vector<T> t){
        Vector<U> res=new Vector<U>();
        for(T old : t) res.add(old);
        return res;
    }
    
    static <K,V> void ajoute(K clef, V val, Vector<? super Paire<K,V>> tab){
        tab.add(new Paire<K,V>(clef, val));
    }
    
    public static void main(String[]ar){
        Vector<Paire<Integer, String>> v1=new Vector();
        ajoute(1, "un", v1);
    }
}