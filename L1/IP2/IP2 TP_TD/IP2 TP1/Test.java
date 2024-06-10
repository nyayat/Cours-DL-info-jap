package tp1;

public class Test {
       
    public static void main (String[] args){
        //4.
        Fruit f = new Fruit("pamplemousse", 330);
        Fruit g = new Fruit("pamplemousse", 330);
        Fruit h = f;
        System.out.println("Test Termine");
        /*On doit respecter les noms (majuscules en miniscules),
            fermer toutes les accolades,
            nommer identiquement la nouvelle classe et le fichier qui contient la classe.*/
        
        //6.
        Fruit.afficher(f);
        //on attend : Ce fruit est un(e) pamplemousse et pèse 330 grammes.
        
        //8.
        Fruit f1=new Fruit("kiwi",100);
        Fruit f2=new Fruit("kaki",150);
        Fruit.afficher(Fruit.hybridation(f1, f2));  
        //on attend : Ce fruit est un(e) kiwikaki et pèse 250 grammes.
        
        //10.
        Fruit[]fruits={f,g,h,f1,f2};
        Panier p=new Panier(fruits);
        Panier.afficher(p);
        /*on attend :   Ce fruit est un(e) pamplemousse et pèse 330 grammes.
                        Ce fruit est un(e) pamplemousse et pèse 330 grammes.
                        Ce fruit est un(e) pamplemousse et pèse 330 grammes.
                        Ce fruit est un(e) kiwi et pèse 100 grammes.
                        Ce fruit est un(e) kaki et pèse 150 grammes.*/
        
        //11.
        Panier.afficher(Panier.hybridePanier(f1, p));
        /*on attend :   Ce fruit est un(e) pamplemoussekiwi et pèse 430 grammes.
                        Ce fruit est un(e) pamplemoussekiwi et pèse 430 grammes.
                        Ce fruit est un(e) pamplemoussekiwi et pèse 430 grammes.
                        Ce fruit est un(e) kiwikiwi et pèse 200 grammes.
                        Ce fruit est un(e) kakikiwi et pèse 250 grammes.*/
        
    }
        
}
