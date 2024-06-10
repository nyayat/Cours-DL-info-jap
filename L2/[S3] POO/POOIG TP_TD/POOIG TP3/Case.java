public class Case {
    //3.1
    private boolean couleur;  //true=blanc, false=noir
    private Piece piece;  //null si case vide
    
    Case(boolean couleur, Piece piece){
        this.couleur=couleur;
        this.piece=piece;
    }
    
    Piece getPiece(){
        return this.piece;
    }
    
    boolean estVide(){
        return (this.piece==null);
    }
    
    void remplirPiece(Piece p){
        this.piece=p;
    }
    
    void enleverPiece(){
        this.piece=null;
    }
    
    //3.2
    public String toString(){
        if(!this.estVide()){
            return this.piece.getNomSelonCouleur().substring(0, 1);
        }
        return ".";
    }
    
    //3.4
    void setCouleur(boolean couleur){
        this.couleur=couleur;
    }
}