//1.
public class GenLib{
    //1.1
    public static Generateur aleaInt(int m){
        return new Generateur(){
            public int suivant(){
                return (int)(Math.random()*m);
            }
        };
    }
    
    public static Generateur suiteAri(int r){
        class GenerateurArithmetique implements Generateur{
            int next=0;
            
            public int suivant(){
                int current=next;
                next+=r;
                return current;
            }
        }
        
        return new GenerateurArithmetique();
    }
    
    public static Generateur suiteGeo(int r){
        class GenerateurGeometrique implements Generateur{
            int next=1;
            
            public int suivant(){
                int current=next;
                next*=r;
                return current;
            }
        }
        
        return new GenerateurGeometrique();
    }
    
    static class GenerateurFibo implements Generateur{
        int u0=0, u1=1;

        public int suivant(){
            int next=u0+u1;
            u0=u1;
            u1=next;
            return u0;
        }
    }
    
    public static Generateur fibo(){
        return new GenerateurFibo();
    }
}