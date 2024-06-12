import java.util.function.BiFunction;
import java.util.function.Function;

//4.
public interface Curryfication<T, U, R> extends Function<T, Function<U, R>>{
    //4.1
    static <T, U, R> Curryfication<T, U, R> curried(BiFunction<T, U, R> f){
        return t -> u -> f.apply(t, u);
    }
    
    //4.2
    default BiFunction<T, U, R> unCurried(){
        return (t, u) -> this.apply(t).apply(u);
    }
}