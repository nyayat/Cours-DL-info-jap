public class Roi extends Piece{
    //2.
    Roi(boolean couleur){
        super(couleur, "Roi");
    }
    
    //6.2
    boolean estValide(Deplacement d, Plateau p){
        return (super.estValide(d, p) && d.dist()==1);
    }
}