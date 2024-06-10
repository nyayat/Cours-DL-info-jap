import java.util.Vector;

public class Ex2 {
    static void affiche(Vector<? super A> vector){
        for(Object v : vector) System.out.print(v.toString());
    }
    
    public static void main(String[]args){
        /*Vector<A> l;
        Vector<B> m=new Vector<B>();
        l=m;*/
        //ne compile pas car Vector<B> n'hérite pas de Vector<A>
        
        Vector<? extends A> l0;
        l0=new Vector<B>();
        //compile car "? extends A" peut être égale à B,
        //donc Vector<? extends A> est hérité par Vector<B>
        
        //? nomMethode(? c)
        
        /*Vector<A> m=new Vector<A>();
        m.add(new A());
        Vector<? extends A> l=m;
        l.add(new A());*/
        //ne compile pas car A n'hérite pas de A, donc impossible de l'ajouter
        
        Vector<A> m=new Vector<A>();
        m.add(new A());
        Vector<? extends A> l1=m;
        A a=l1.get(0);
        //compile car upcast
        
        affiche(new Vector<Object>());  //compile
        affiche(new Vector<A>());  //compile
        //affiche(new Vector<B>());  //ne compile pas
        
        Vector<? super A> l2=new Vector<A>();
        l2.add(new A());
        //compile car A est inclut dans "? super A" (A est un super de A)
        
        /*Vector<? super A> l2=new Vector<A>();
        A a=l2.get(0);*/
        //ne compile pas car le premier élément a pour type déclaratif "? super A"
        //donc pas forcément A (il faut forcer la transformation en A)
    }
}