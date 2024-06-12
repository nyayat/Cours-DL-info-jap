public class Main{
    public static void main(String[] args){
        //1.1
        //Object n'extends pas Base :
        //G<Object, Object>
        //G<Object, Base>
        
        //idem pour les classes parents de Object :
        //G<? super Object, ? super Object>
        //G<? super Object, ? super Base>
        
        //peut marcher (pas tout le temps le cas) :
        //G<? extends Object, ? extends Object> //6
        //G<? extends Object, ? extends Base>   //7
        //G<? super Base, ? super Object>       //8
        //G<? super Base, ? super Derive>       //9
        //G<?, ?>                               //10
        
        //toujours ok :
        //G<Base, Object>                       //1
        //G<Derive, Object>                     //2
        //G<? extends Derive, ? extends Object> //3
        //G<? extends Base, ? extends Object>   //4
        //G<? extends Base, ? extends Derive>   //5
        
        //1.2
        //1 -> 5 -> 7 -> 6/10
        //5 -> 4 -> 6/10
        //2 -> 3 -> 4
        //1 -> 8 -> 9 -> 6/10
    }
}