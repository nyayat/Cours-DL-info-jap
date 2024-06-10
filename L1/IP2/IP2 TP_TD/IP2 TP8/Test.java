public class Test {
    public static void main(String[]a){
        Robot r1=new Robot("Arthur");
        Robot r2=new Robot("Lancelot");
        Robot r3=new Robot("Gauvain");
        Robot r4=new Robot("Perceval");
        Robot r5=new Robot("Tristan");
        Robot r6=new Robot("Mordred");
        
        /*r1.description();
        r2.description();*/
        
        TableRonde t1=new TableRonde(r1);
        //t1.affiche();
        
        System.out.println("---------------");
        t1.ajouteRob(r2);
        t1.ajouteRob(r3);
        t1.ajouteRob(r4);
        t1.ajouteRob(r5);
        t1.ajouteRob(r6);
        t1.affiche();
        
        System.out.println("---------------");
        /*System.out.println(t1.supprimer(1));
        System.out.println(t1.supprimer(7));
        System.out.println(t1.supprimer(5));
        System.out.println(t1.supprimer(0));
        t1.affiche();*/
        
        t1.discussion();
    }
}