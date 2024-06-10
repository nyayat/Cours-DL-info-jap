//1.1
public class A {
    private static int h=5;
    private B x=new B();
    private int i=x.b;
    
    public static class StaticA{
        public int nstat=h;
        public static int astat=h;
        public void increase(){
            h++;
            //i++;  //pas de i static dans A
        }
    }
        
    private class B{
        private int b=2;
        //private static int bstat=3;  //pas final
    }

    public class InstaceA{
        private int i=A.StaticA.astat;
        //private int j=A.StaticA.nstat;  //j non-static alors que nstat l'est
        //public int k=A.x.b;  //x pas dans A mais dans B
        //public static void staticMethod(){}  //pas de static dans les méthodes
        public void method(int i){
            int i1=this.i;
            int i2=A.this.i;
            System.out.println(i+' '+i1+' '+i2);
        }
    }
}
