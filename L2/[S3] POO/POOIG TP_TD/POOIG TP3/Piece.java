public class Piece {
    //1.1
    private boolean couleur;  //true=blanc, false=noir
    private String nom;
    
    //1.2
    Piece(boolean couleur, String nom){
        this.couleur=couleur;
        this.nom=nom;
    }
    
    //1.3
    public String toString(){
        if(this.couleur){
            return this.nom.toLowerCase();
        }
        return (this.nom);
        //Par défaut la première lettre est en majuscule, sinon on devrait retourner :
        //this.nom.toUpperCase().charAt(0)+this.nom.toLowerCase().substring(1)
    }
    
    //3.2
    String getNomSelonCouleur(){
        if(this.couleur){
            return this.nom.toLowerCase();
        }
        return this.nom;  //par défaut correctement écrit depuis le début pour false
    }
    
    //6.1
    boolean estValide(Deplacement d, Plateau p){
        if(!(p.horsLimite(d.getX0(), d.getY0(), d.getX1(), d.getY1()))){
            if(p.getCase(d.getX1(), d.getY1()).estVide()
            || p.getCase(d.getX1(), d.getY1()).getPiece().couleur!=this.couleur){
                return true;
            }
        }
        return false;
    }
    
    //6.2
    boolean getCouleur(){
        return this.couleur;
    }
}