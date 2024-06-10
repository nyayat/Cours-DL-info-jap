public class Pion extends Piece{
    //2.
    Pion(boolean couleur){
        super(couleur, "Pion");
    }
    
    //6.2
    boolean estValide(Deplacement d, Plateau p){
        if(super.estValide(d, p)){
            if(d.typeDeplacement()=='v' && p.getCase(d.getX1(), d.getY1()).estVide()){
                if(super.getCouleur() && d.getX1()-d.getX0()<0){  //les pions blancs doivent "monter"
                    if((d.getX0()==p.getLargeur()-2) && d.dist()==2) return true;
                    if(d.dist()==1) return true;
                }
                if(!super.getCouleur() && d.getX1()-d.getX0()>0){  //les pions noirs doivent "descendre"
                    if((d.getX0()==1) && d.dist()==2) return true;
                    if(d.dist()==1) return true;
                }
            }
            if(d.typeDeplacement()=='d' && d.dist()==1
               && !p.getCase(d.getX1(), d.getY1()).estVide()){
                if(super.getCouleur() && d.getX1()-d.getX0()<0) return true;
                if(!super.getCouleur() && d.getX1()-d.getX0()>0) return true;
            }
        }
        return false;
    }
}