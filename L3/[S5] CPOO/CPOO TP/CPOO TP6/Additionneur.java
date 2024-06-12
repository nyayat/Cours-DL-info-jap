import java.util.function.UnaryOperator;

//3.3
public class Additionneur implements UnaryOperator<Integer>{
    final private Integer add;
    
    Additionneur(Integer i){
        add=i;
    }
    
    @Override
    public Integer apply(Integer t){
        return t+add;
    }
}