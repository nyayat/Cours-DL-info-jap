//1.1
public class D {
    public void localMethod(int i){
        i++;
        int j=i;
        
        class Locale{
            //int k=i;  //nécessaire d'être final (pour pas modifier i)
            
            /*public void increase1(){
                //return ++i;  //renvoie int alors que void dans la signature
            }*/
            
            /*public int increase2(){
                //return ++j;  //pas possible de mod var de classe englobante
            }*/
            
            /*public int increase3(){
                return ++k;  // plus de k
            }*/
        }
        
        Locale l=new Locale();
        //System.out.println(l.increase3());  //plus de increase3()
    }

    public static void main(String[]args){
        A a=new A();
        A.StaticA.astat=4;
        //A.StaticA.nstat=3;  //nstat non static alors que dans classe static
        
        A.StaticA sa1=new A.StaticA();
        //A.StaticA sa2=A.new StaticA();  //on ne déclare pas comme ça
        sa1.nstat=3;
        //A.InstaceA nsa1=new A.InstaceA();  //on ne déclare pas comme ça
        A.InstaceA nsa2=a.new InstaceA();
        //A.InstaceA nsa3=new a.InstaceA();  //on ne déclare pas comme ça
        //nsa2.i=3;  //private
        nsa2.method(6); //6 4 2
        //A.B b=a.new B(5);  //private
        D d=new D();
        int i=2;
        i++;
        d.localMethod(i);
    }
}