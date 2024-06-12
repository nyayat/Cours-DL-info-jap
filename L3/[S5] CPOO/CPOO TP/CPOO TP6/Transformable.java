import java.util.function.UnaryOperator;

//3.
public interface Transformable<T>{
    T getElment();
    void transform(UnaryOperator<T> trans);
}
