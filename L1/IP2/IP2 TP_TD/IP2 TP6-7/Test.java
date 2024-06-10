import java.util.Scanner;
public class Test {
    public static void main(String[]a){
        /*//1.3
        Automate l1=new Automate();
        l1.ajout(true);
        l1.ajout(true);
        l1.ajout(true);
        l1.ajout(false);
        l1.ajout(false);
        l1.ajout(false);
        l1.ajout(true);
        l1.ajout(false);
        l1.ajout(false);
        l1.ajout(false);
        l1.ajout(false);
        l1.affichage();*/

        /*//2.7
        Automate l2=new Automate();
        l2.initialisation();        
        l2.nbEtapes(4);*/
        
        /*//2.9
        Automate l3=new Automate("-*#");
        l3.affichage();*/
        
        //2.10
        /*//pour tester la loi de la majorité
        Automate l4=new Automate();
        l4.initialisation();
        l4.nbEtapesBis(4);*/
        
        play();
    }
    
    static void play(){
        String aut;  //choix de l'automate
        String loi;  //choix de la loi
        int n;  //choix du nombre d'étapes
        
        Scanner sc0=new Scanner(System.in);
        System.out.println("Saisissez votre ligne de - et # ?");
        aut=sc0.nextLine();
        
        Automate l5=new Automate(aut);
        
        do{
            Scanner sc1=new Scanner(System.in);
            System.out.println("unanimite ou majorite ?");
            loi=sc1.nextLine();
        }
        while(!(loi.equals("unanimite") || loi.equals("majorite")));
        
        Scanner sc2=new Scanner (System.in);
        System.out.println("Combien d'étapes ?");
        n=Integer.valueOf(sc2.nextLine());
        
        l5.nbEtapesAvecChoixRegle(loi, n);
    }
}