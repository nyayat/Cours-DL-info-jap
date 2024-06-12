import java.util.List;

public class Main{
    public static void main(String[] args){
        //1.1
        //Concernant l'utilisation, il n'y a aucune différence.
        //Mais <T> est un type utilisable dans la fonction, pas <?>.
        
        //1.2
        //Non, elles s'utilisent de la même manière.
        
        //2.1
        /*ClarkKent ck=new ClarkKent();
        System.out.println(ck);
        ck.changeEtat(true);
        System.out.println(ck);*/
        
        //2.2
        Personnage.ClarkKent ck=(new Personnage()).new ClarkKent();
        System.out.println(ck);
        ck.changeEtat(true);
        System.out.println(ck);
        
    }
    
    //1.3
    static <T> void dupliquePremierB(List<T> l){
        if(!l.isEmpty()) l.add(l.get(0));
    }
    
    //1.4
    static void dupliquePremierA(List<?> l){
        dupliquePremierB(l);
    }
}