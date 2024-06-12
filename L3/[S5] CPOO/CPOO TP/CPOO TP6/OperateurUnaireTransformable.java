import java.util.function.UnaryOperator;

//5.
public class OperateurUnaireTransformable<T> implements Transformable<UnaryOperator<T>>{
    private UnaryOperator op;
    
    OperateurUnaireTransformable(UnaryOperator o){
        op=o;
    }
    
    @Override
    public UnaryOperator<T> getElment(){
        return op;
    }

    @Override
    public void transform(UnaryOperator<UnaryOperator<T>> trans){
        op=trans.apply(op);
    }
}