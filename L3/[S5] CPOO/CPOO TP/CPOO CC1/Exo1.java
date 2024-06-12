import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

//1.
public class Exo1{
    //1.1
    static <T> void fait2(List<T> l, BiConsumer<T, T> action, T init){
        if(l.size()==0) return;
        action.accept(init, l.get(0));
        for(int i=0; i<l.size()-1; i++)
            action.accept(l.get(i), l.get(i+1));
    }
    
    //1.2
    public static void main(String[] args){
        List<Integer> l=Arrays.asList(new Integer[]{2, 3, 4});
        fait2(l, new BiConsumer<Integer, Integer>(){
            @Override
            public void accept(Integer t, Integer u){
                System.out.println(t+u);
            }
        }, 1);
        /*On affiche la somme des couples de la liste (en prenant en compte, 
            bien sûr, init).
        */
    }
}