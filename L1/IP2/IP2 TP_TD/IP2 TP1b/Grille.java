public class Grille {
    int largeur, longueur;
    boolean[][]t;
    
    public Grille(int largeur, int longueur){
        this.largeur=largeur;
        this.longueur=longueur;
        this.t=new boolean[longueur][largeur];
    }
    
    public void dessine(){
        for(int i=0; i<this.t.length; i++){
            for(int j=0; j<this.t[i].length; j++){
                if(this.t[i][j]){
                System.out.print(" ");
            }
                else{
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
    
    public void dessineAvecFourmi(Fourmi f){
        for(int i=0; i<this.t.length; i++){
            for(int j=0; j<this.t[i].length; j++){
                if(i==f.y && j==f.x){
                    System.out.print("0");
                }
                else if(this.t[i][j]){
                    System.out.print(" ");
                }
                else{
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
    
    public boolean getCouleur(int x, int y){
        return this.t[y][x];
    }
    
    public void changeCouleur(int x, int y){
        this.t[y][x]=!this.t[y][x];
    }
    
    public boolean estHorsGrille(int x, int y){
        return(x<0 || x>this.largeur || y<0 || y>longueur);
    }
}
