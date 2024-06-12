import java.util.function.UnaryOperator;

//3.1
public class EntierTransformable implements Transformable<Integer>{
    private Integer state;
    
    EntierTransformable(Integer i){
        state=i;
    }

    @Override
    public Integer getElment(){
        return state;
    }

    @Override
    public void transform(UnaryOperator<Integer> trans){
        state=trans.apply(state);
    }
}