import java.util.*;

public class Joueur {
    private String nom;
    private Scanner scanReponse;

    //7.
    Joueur() {
        this.nom = "Anonyme";
        this.scanReponse = new Scanner(System.in);	
    }

    //8.
    void setNom(String nom) {
        this.nom = nom;
    }

    public void finish() {
        this.scanReponse.close();
    }

    //9
    String askPlay() {
        System.out.println("Wanna play ? Reply 'y' (yes) or 'n' (no)");
        String r= this.scanReponse.next();
        if(!(r.equals("y") || r.equals("n"))){
            if(!r.equals("")){
                System.out.println("I'm not sure I understand.");
                this.askPlay();
            }
        }
        return r;
    }

    void askName() { //change le nom
        System.out.println("Pick your name :");
        String r = this.scanReponse.nextLine();
        while (r.equals("")) {
            System.out.println("This is not a valid name. Try again :");
            r = this.scanReponse.next();
        }
        this.setNom(r);
    }

    int[] askTaille() { //renvoie {largeur, hauteur}
        System.out.println("Please choose the board's width :");
        int l = this.scanReponse.nextInt();
        while (l < 0) {
            System.out.println("Must be positive ! Choose again :");
            l = this.scanReponse.nextInt();
        }
        System.out.println("Please choose the board's height :");
        int h = this.scanReponse.nextInt();
        while (h < 0) {
            System.out.println("Must be positive ! Choose again :");
            h = this.scanReponse.nextInt();
        }
        int[]t = {l, h};
        return t;
    }

    int[] askMove() { // {reponse, i, j}
        //1 = enlever/poser un drapeau
        //2 = reveler une case 
        System.out.println("Choose your next move from : 1- enlever/poser drapeau; 2- reveler une case");//super franglais
        int r = this.scanReponse.nextInt();
        while (r!=1 && r!=2) {
            System.out.println("This is not a valid move. Try again :");
            r = this.scanReponse.nextInt();
        }
        System.out.println("Please choose the position (i, j) in the board:");
        int i = this.scanReponse.nextInt();
        int j = this.scanReponse.nextInt();
        int[]res = {r,i,j}; 
        return res; 
    }

    int askMines() {
        System.out.println("How many mines do you want ?");
        int r = this.scanReponse.nextInt();
        while (r < 0) {//il faudrait aussi avoir acces aux dimensions pour pas que ca depasse le nombre de cases=>prévu dans Plateau
            System.out.println("Must be positive ! Choose again :");
            r = this.scanReponse.nextInt();
        }
        return r;
    }
}