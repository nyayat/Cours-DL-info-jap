public class Plateau {
    //3.3
    private final int longueur, largeur;
    private Case[][]cases;
    
    /* cases[x][y]=(x,y)=(largeur, longueur)
            longueur longueur longueur (y)
    largeur     0.0     0.1     0.2
    largeur     1.0     1.1     1.2
    largeur     2.0     2.1     2.2
      (x)
    */
    
    //3.4
    Plateau(int longueur, int largeur){
        this.longueur=longueur;
        this.largeur=largeur;
        this.cases=new Case[this.largeur][this.longueur];
        for(int x=0; x<this.largeur; x++){
            for(int y=0; y<this.longueur; y++){
                if(x%2==y%2){
                    cases[x][y]=new Case(false, null);  //noir
                }
                else{
                    cases[x][y]=new Case(true, null);  //blanc
                }
            }
        }
    }
    
    boolean horsLimite(int x, int y){
        return !(x>-1 && x<this.largeur && y>-1 && y<this.longueur);
    }
    
    Case getCase(int x, int y){
        return cases[x][y];
    }
    
    void videCase(int x, int y){
        if(!this.horsLimite(x, y)){
            this.cases[x][y].enleverPiece();
        }
    }
    
    void remplirCase(int x, int y, Piece p){
        if(!this.horsLimite(x, y)){
            this.cases[x][y].remplirPiece(p);
        }
    }
    
    void afficher(){
        for(int x=0; x<this.largeur; x++){
            for(int y=0; y<this.longueur; y++){
                System.out.print(this.cases[x][y]+" ");
            }
            System.out.println();
        }
    }
    
    //5.
    boolean horsLimite(int x0, int y0, int x1, int y1){
        return !((x0>-1 && x0<this.largeur && y0>-1 && y0<this.longueur)
                    &&(x1>-1 && x1<this.largeur && y1>-1 && y1<this.longueur));
    }
    
    //6.2
    int getLargeur(){
        return this.largeur;
    }
}