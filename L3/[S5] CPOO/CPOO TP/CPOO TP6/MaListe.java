import java.util.LinkedList;
import java.util.List;
import java.util.function.*;

//1.
public class MaListe<E> extends LinkedList<E>{
    //1.2
    void pourChacun(Consumer<E> action){
        this.forEach(action);
    }
    
    //2.1
    List<E> filter(Predicate<E> pred){
        List<E> res=new MaListe<E>();
        for(E e : this)
            if(pred.test(e)) res.add(e);
        return res;
    }
    
    //2.2
    <U> List<U> map(Function<E, U> f){
        List<U> res=new MaListe<U>();
        for(E e : this) res.add(f.apply(e));
        return res;
    }
    
    //2.3
    <U> U fold(U z, BiFunction<U, E, U> f){
        U a=z;
        for(E e : this) a=f.apply(a, e);
        return a;
    }
}