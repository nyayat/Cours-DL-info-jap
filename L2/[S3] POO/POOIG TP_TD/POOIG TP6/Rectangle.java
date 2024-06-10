//1.
public class Rectangle extends Figure implements Deformable{
    //2.
    private final double longueur, largeur;
    
    Rectangle(int x, int y, double lo, double la){
        super(x, y);
        this.longueur=lo;
        this.largeur=la;
    }
    
    public void affiche(){
        System.out.println("Le centre du rectangle se trouve en ("+this.getX()+", "
                +this.getY()+") et il a pour longueur "+this.longueur
                +" et largeur "+this.largeur+".");
    }
    
    //3.
    public double getL(){  //pour l'affichage dans Carre
        return this.longueur;
    }
    
    //5.
    public Figure deformation(double coeffH, double coeffV){
        double la=this.largeur*coeffH;
        double lo=this.longueur*coeffV;
        if (la==lo) return new Carre(this.getX(), this.getY(), la);
        return new Rectangle(this.getX(), this.getY(), lo, la);
    }
    
    //6.
    public double surface(){
        return this.largeur*this.longueur;
    }
}