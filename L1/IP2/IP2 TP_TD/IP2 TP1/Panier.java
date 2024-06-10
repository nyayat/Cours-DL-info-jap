package tp1;

public class Panier {
    
    //9.
    public Fruit[] fruits;  //tableau de fruits de type Fruit
    
    public Panier(Fruit[]f){  //panier=tableau f
        int l=f.length;
        fruits=new Fruit[l];
        for(int i=0; i<l; i++){
            fruits[i]=f[i];
        }
    }
    
    public Panier(){  //panier vide
        fruits=new Fruit[0];
    }
    
    public Panier(Fruit f, Panier p){  //panier=panier p + fruit f
        int l=p.fruits.length;
        fruits=new Fruit[l+1];
        for(int i=0; i<l; i++){
            fruits[i]=p.fruits[i];
        }
        fruits[l]=f;
    }
    
    //10.
    public static void afficher(Panier p){
        int l=p.fruits.length;
        for(int i=0; i<l; i++){
            Fruit.afficher(p.fruits[i]);
        }
    }
        
    //11.
    public static Panier hybridePanier(Fruit f, Panier p){
        Panier res=new Panier(p.fruits);
        for(int i=0; i<p.fruits.length; i++){
            res.fruits[i]=Fruit.hybridation(f, p.fruits[i]);
        }
        return res;
    }
    
    
}
