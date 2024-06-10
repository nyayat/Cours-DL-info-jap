package tp1;

public class Fruit{
    
    //1.
    public String nom; //le nom du fruit
    public int poids; //le poids du fruit en grammes
    
    public Fruit(String n, int p){
        nom=n;
        poids=p;
    }
    
    //2.f et h pointent vers la même instance et g pointe vers une autre instance.
        
    //5.
    public static void afficher(Fruit f){
        System.out.println("Ce fruit est un(e) " + f.nom
                + " et pèse " + f.poids + " grammes.");
     }
     
    //8.
    public static Fruit hybridation(Fruit f1, Fruit f2){
        String sommeNom=f1.nom+f2.nom;
        int sommeMasse=f1.poids+f2.poids;
        Fruit res=new Fruit(sommeNom,sommeMasse);
        return res;
    }
        
}
