import java.util.List;
import java.util.function.*;

public class Test{
    public static void main(String[] args){
        MaListe<Integer> list=new MaListe<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        
        //1.3
        //list.pourChacun(i->System.out.println(i));
        
        //1.4
        //list.pourChacun(System.out::println);
        
        //1.5
        /*list.pourChacun(new Consumer(){
            int n=0;
            
            @Override
            public void accept(Object t){
                System.out.println((++n)+" : "+t);
            }
        });*/
        
        //1.6
        /*Consumer c=new Consumer<Integer>(){
            int n=0;
            
            @Override
            public void accept(Integer t){
                System.out.println((++n)+" : "+t);
            }
        };
        list.pourChacun(c);*/
        
        //1.7
        MaListe<Personne> list2=new MaListe<Personne>();
        list2.add(new Personne("personne1"));
        list2.add(new Personne("personne2"));
        list2.add(new Personne("personne3"));
        
        //1.8
        /*list2.pourChacun(new Consumer<Personne>(){
            int n=0;
            
            @Override
            public void accept(Personne t){
                System.out.println((++n)+" : "+t);
            }
        });*/
        
        //1.9
        /*Consumer<List> myC=new Consumer<List>(){
            int n=0;
            
            public void accept(List t){
                for(Object o : t) System.out.println((++n)+" : "+o);
            }
        };
        
        myC.accept(list2);*/
        
        
        //2.4
        //produit
        /*System.out.println(list.fold(1, new BiFunction<Integer, Integer, Integer>(){
            @Override
            public Integer apply(Integer t, Integer u){
                return t*u;
            }
        }));
        
        //maximum de la liste
        System.out.println(list.fold(0, new BiFunction<Integer, Integer, Integer>(){
            @Override
            public Integer apply(Integer t, Integer u){
                return t>u?t:u;
            }
        }));*/
        
        
        //3.2
        /*EntierTransformable i=new EntierTransformable(0);
        i.transform(new UnaryOperator<Integer>(){
            @Override
            public Integer apply(Integer t){
                return t*2;
            }
        });
        i.transform(new UnaryOperator<Integer>(){
            @Override
            public Integer apply(Integer t){
                return t+15;
            }
        });
        System.out.println(i.getElment());*/
        
        //3.3
        EntierTransformable x=new EntierTransformable(2);
        /*x.transform(new Additionneur(3));
        System.out.println(x.getElment());*/
        
        
        //5.2
        UnaryOperator<UnaryOperator<Integer>> trans=new UnaryOperator<UnaryOperator<Integer>>(){
            @Override
            public UnaryOperator<Integer> apply(UnaryOperator<Integer> t){
                return e->t.apply(t.apply(t.apply(t.apply(
                    t.apply(t.apply(t.apply(t.apply(t.apply(t.apply(e))))))))));
            }
        };
 
        //5.3
        OperateurUnaireTransformable<Integer> op=new OperateurUnaireTransformable<Integer>(
            new UnaryOperator<Integer>(){
                @Override
                public Integer apply(Integer t){
                    return t*2;
                }
            }
        );
        
        op.transform(trans);
        x.transform(op.getElment());
        System.out.println(x.getElment());
    }
}