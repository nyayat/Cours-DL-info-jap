public class Test {
    public static void main(String[]a){
        Liste l=new Liste();
        //l.ajouterDebut(1);
        l.ajouterDebut(0);
        l.ajouterDebut(0);
        l.ajouterFin(2);
        l.ajouterFin(4);
        l.ajouterFin(4);
        Element e3=new Element(3, null, null);
        Element e4=new Element(4, null, null);
        l.ajouterAvant(e4, 3);
        l.ajouterAvant(e3, 2);
        //l.ajouterDebut(4);
        l.afficher();
        
        System.out.println("longueur : "+l.longeur());
        
        /*System.out.println("---------------");
        l.supprimerTout(4);
        l.afficher();        
        System.out.println("longueur : "+l.longeur());*/
        
        System.out.println("---------------");
        l.afficherInverse();
        
        System.out.println("---------------");
        System.out.println(l.somme());
        
        System.out.println("---------------");
        System.out.println(l.estTriee());
        
        System.out.println("---------------");
        l.inverser(e3);
        l.afficher();
        
        System.out.println("---------------");
        l.passerEnOrdonnant();
        l.afficher();
        
        System.out.println("---------------\n"+l.verifContrainte());
        
        System.out.println("---------------");
        l.moyenneLocale().afficher();
        
        System.out.println("---------------");
        l.annuleRedondances();
        l.afficher();
    }
}