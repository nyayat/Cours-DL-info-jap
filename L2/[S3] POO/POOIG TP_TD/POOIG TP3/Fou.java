public class Fou extends Piece{
    //2.
    Fou(boolean couleur){
        super(couleur, "Fou");
    }
    
    //6.2
    boolean estValide(Deplacement d, Plateau p){
        if(super.estValide(d, p) && d.typeDeplacement()=='d'){
            for(int i=1; i<d.dist(); i++){
                if(d.getX0()<d.getX1()){
                    if(d.getY0()<d.getY1()){
                        if(!(p.getCase(d.getX0()+i, d.getY0()+i).estVide())) return false;
                    }
                    else{
                        if(!(p.getCase(d.getX0()+i, d.getY0()-i).estVide())) return false;
                    }
                }
                else{
                    if(d.getY0()<d.getY1()){
                        if(!(p.getCase(d.getX0()-i, d.getY0()+i).estVide())) return false;
                    }
                    else{
                        if(!(p.getCase(d.getX0()-i, d.getY0()-i).estVide())) return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}