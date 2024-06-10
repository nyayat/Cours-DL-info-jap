public class Test {
    public static void main(String[]a){
        FileToboggan t1=new FileToboggan(2);
        Toboggan to1=new Toboggan(t1);
        
        t1.ajouter("Anna", 0);
        t1.ajouter("Léa", 0);
        t1.ajouter("Christophe", 0);
        //t1.affiche();
        System.out.println("---------------");
        
        /*t1.supprimer("Anna");
        //t1.affiche();*/
        System.out.println("---------------");
        
        /*t1.supprimer("Léa");
        //t1.affiche();*/
        System.out.println("---------------");
        
        /*t1.supprimer("Christophe");
        t1.affiche();*/
        System.out.println("---------------");
        
        /*t1.unTour_bis();
        /*t1.unTour();
        t1.unTour();
        t1.unTour();*/
        System.out.println("---------------");
        
        //t1.affiche();
        System.out.println("---------------");
        
        /*System.out.println("total : "+to1.jouer());
        t1.affiche();*/
        System.out.println("---------------");
        
        /*FileToboggan t2=to1.jouer_sortis();
        System.out.println("Dans l'odre de sortie : ");
        t2.affiche();*/
        System.out.println("---------------");
        
        FileToboggan t2=new FileToboggan(2);        
        t2.ajouter("Anna", 0);
        t2.ajouter("Léa", 0);
        t2.ajouter("Christophe", 0);
        t2.affiche();
        System.out.println("---------------");
        
        t2.unTourPre_bis();
        System.out.println("---------------");
        
        t2.affiche();
        System.out.println("---------------");        
    }
}