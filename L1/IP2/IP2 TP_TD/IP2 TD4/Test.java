public class Test {
    public static void main(String[] args) {
        //1.3
        //Animal a1=new Animal("herbivore");
        //Animal a2=new Animal("carnivore");
        
        Enclos e1=new Enclos();
        Enclos e2=new Enclos();
        
        e1.ajouter("carnivore");
        e1.ajouter("herbivore");
        e1.ajouter("herbivore");
        //on inverse l'ordre puisqu'on insère au début de la liste à chaque fois
        
        e2.ajouter("carnivore");
        e2.ajouter("herbivore");
        e2.ajouter("herbivore");
        e2.ajouter("herbivore");
        //idem
        
        /*e1.affiche();
        System.out.println("----------------");        
        e2.affiche();
        System.out.println("----------------");*/
        
        //2.1
        //e1 : HHC -> HC -> C
        //e2 : HHHC -> (HHHC)^w
        
        //2.4
        Enclos e3=new Enclos();
        e3.ajouter("herbivore");
        e3.ajouter("herbivore");
        e3.ajouter("carnivore");
        
        e3.affiche();
        System.out.println("----------------");
        e3.jouer(1);
        System.out.println("----------------");
        e3.jouer(2);
        System.out.println("----------------");
        
        /*e1.affiche();
        System.out.println("----------------");
        e1.jouer(1);
        System.out.println("----------------");
        e1.jouer(2);
        System.out.println("----------------");*/
    }
}