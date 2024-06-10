public class Test {
    public static void main(String[]args){
        Employe emp1=new Employe("Sam", 1800);
        Employe emp2=new Employe("Anne-Lise", 1900);
        Employe emp3=new Employe("Yukarin", 1600);
        Employe emp4=new Employe("Cristina", 1700);
        
        Cellule c1=new Cellule(emp1);
        Cellule c2=new Cellule(emp2);
        Cellule c3=new Cellule(emp3);
        
        Entreprise e1=new Entreprise("Diderot", c1);
        Entreprise e2=new Entreprise("Sorbonne");
        //e1.ajout(emp2);
        //e1.ajout(emp3);
        
        e1.affiche();
        System.out.println("-----------------------");
        
        //System.out.println(e1.appartient("Sam"));
        //System.out.println(e1.appartient("Samy"));
        
        /*e1.demission("Yukarin");
        e1.affiche();
        System.out.println("-----------------------");
        e1.demission("Sam");
        e1.affiche();*/
        
        /*System.out.println(e1.augmente("Anne-Lise", 300));
        e1.affiche();
        System.out.println(e1.augmente("Samy", 300));*/
        
        /*e1.choixSalaire(1900, 2000).affiche();
        e1.affiche();*/
        
        //System.out.println(e1.croissante());
        
        /*e1.ajoutTrie(emp3);
        e1.affiche();
        System.out.println("-----------------------");
        
        e1.ajoutTrie(emp2);
        e1.affiche();
        System.out.println("-----------------------");*/
        
        /*e2.ajout(emp3);
        e2.ajout(emp2);
        e2.affiche();
        System.out.println("-----------------------");
        e1.acquisition_Version_1(e2);
        e1.affiche();
        System.out.println("-----------------------");
        e2.affiche();*/
        
        /*e1.ajoutTrie(emp3);
        e1.affiche();
        System.out.println("-----------------------");
        e1.augmente_Version_1("Yukarin", 400);
        e1.affiche();
        System.out.println("-----------------------");
        e1.ajoutTrie(emp2);
        e1.augmente_Version_1("Anne-Lise", 0);
        e1.affiche();*/
        
        /*e1.ajoutTrie(emp3);
        e1.affiche();
        System.out.println("-----------------------");
        e1.augmente_Version_2("Yukarin", 400);
        e1.affiche();
        System.out.println("-----------------------");
        e1.ajoutTrie(emp2);
        e1.augmente_Version_2("Anne-Lise", 250);
        e1.affiche();*/
        
        e2.ajoutTrie(emp2);
        e2.ajoutTrie(emp3);
        e2.ajoutTrie(emp4);
        e2.affiche();
        System.out.println("-----------------------");
        e1.acquisition_Version_2(e2);
        e1.affiche();
    }
}