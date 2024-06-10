public class Cavalier extends Piece{
    //2.
    Cavalier(boolean couleur){
        super(couleur, "Cavalier");
    }
    
    //6.2
    boolean estValide(Deplacement d, Plateau p){
        return (super.estValide(d, p) && d.typeDeplacement()=='c');
    }
}