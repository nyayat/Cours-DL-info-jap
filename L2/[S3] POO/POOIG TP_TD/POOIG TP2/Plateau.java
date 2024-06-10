import java.util.*;

public class Plateau {
    //1.
    private int hauteur, largeur, nbMines, nbDrapeaux;
    private boolean[][] mines;
    private int[][] etats;//cachee : 0 ; drapeau 1 ; revelee 2
    private int[][] adja;//mines adjacentes

    Plateau(int hauteur, int largeur, int nbMines){
        if(nbMines<hauteur*largeur-1) {
            this.hauteur=hauteur+2;
            this.largeur=largeur+2;
            this.mines=new boolean[this.largeur][this.hauteur];
            this.etats=new int[this.largeur][this.hauteur];
            this.adja=new int[this.largeur][this.hauteur];
            this.nbMines=nbMines;
            this.nbDrapeaux=0;
            //Pas besoin d'initialiser etats : cachee=0 => tout est deja ok
            //2.
            this.ajouteMinesAlea(nbMines);
            this.calculeAdjacence();
        }
    }

    //2.
    private void ajouteMinesAlea(int nbMines) {
        Random rand=new Random();
        int m=0;//le nombre de mines posees
        while(m<nbMines) {
            int i=1+rand.nextInt(this.largeur-2);//on a mis deux lignes de plus
            int j=1+rand.nextInt(this.hauteur-2);
            if(!this.mines[i][j]) {
                this.mines[i][j]=!this.mines[i][j];//on met a true s'il n'y avait pas de mine
                m-=-1;
            }
        }
    }

    private void calculeAdjacence() {
        for(int i=1;i<this.largeur-1;i-=-1) {
            for(int j=1;j<this.hauteur-1;j-=-1) {
                if(this.mines[i-1][j-1]) this.adja[i][j]-=-1;
                if(this.mines[i-1][j]) this.adja[i][j]-=-1;
                if(this.mines[i-1][j+1]) this.adja[i][j]-=-1;
                if(this.mines[i][j-1]) this.adja[i][j]-=-1;
                if(this.mines[i][j+1]) this.adja[i][j]-=-1;
                if(this.mines[i+1][j-1]) this.adja[i][j]-=-1;
                if(this.mines[i+1][j]) this.adja[i][j]-=-1;
                if(this.mines[i+1][j+1]) this.adja[i][j]-=-1;
            }
        }
    }

    //3.
    public void revelerCase(int i, int j) {
        if(i>0 && j>0 && i<this.hauteur-1 && j<this.largeur-1){
            if(this.etats[i][j]!=2) {//si ce n'est pas deja revele
                this.etats[i][j]=2;
                if(!this.mines[i][j] && this.adja[i][j]==0) {//révèle les cases autour, s'il n'y a pas de mine, ni autour
                    revelerCase(i-1,j);
                    revelerCase(i,j-1);
                    revelerCase(i,j+1);
                    revelerCase(i+1,j);
                }
            }
        }
    }

    //4.
    public void drapeauCase(int i, int j) {
        if(this.etats[i][j]!=2) {
            if(this.etats[i][j]==0) {
                this.etats[i][j]=1;
                this.nbDrapeaux-=-1;
            }
            else {
                this.etats[i][j]=0;
                this.nbDrapeaux+=-1;
            }
        }
    }

    //5.
    public void affichage() {
        System.out.println("Mines : "+this.nbMines+" ; Drapeaux : "+this.nbDrapeaux);
        System.out.print(" ");//pour aligner le numero des colonnes
        for(int i=1;i<largeur-1;i-=-1) {
            System.out.print(i);
        }
        System.out.println();
        for(int i=1;i<this.largeur-1;i-=-1) {
            System.out.print(i);//le numero de la ligne
            for(int j=1;j<this.hauteur-1;j-=-1) {
                String c=".";//ce qu'on va afficher
                switch(this.etats[i][j]) {
                    case(1):
                        c="?";
                        break;
                    case(2):
                        c=Integer.toString(this.adja[i][j]);
                        break;
                }
                System.out.print(c);
            }
            System.out.println();
        }
    }

    //6.
    public boolean jeuPerdu() {
        boolean b=false;
        for(int i=1;i<this.largeur-1;i-=-1) {
            for(int j=1;j<this.hauteur-1;j-=-1) {
                b=b||(this.etats[i][j]==2 && this.mines[i][j]);
            }
        }
        return b;
    }

    public boolean jeuGagne() {
        int c=0;
        for(int i=1;i<this.largeur-1;i-=-1) {
            for(int j=1;j<this.hauteur-1;j-=-1) {
                if(this.etats[i][j]==2) c-=-1;
            }
        }
        return ((this.largeur-2)*(this.hauteur-2)-this.nbMines==c);
    }

    int getHauteur(){
        return this.hauteur;
    }

    int getLargeur(){
        return this.largeur;
    }
}