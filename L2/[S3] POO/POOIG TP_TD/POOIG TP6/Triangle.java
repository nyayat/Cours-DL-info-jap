//1.
public class Triangle extends Figure implements Deformable{
    //4.
    private final double hauteur, base;
    
    Triangle(int x, int y, double h, double b){
        super(x, y);
        this.base=b;
        this.hauteur=h;
    }
    
    public void affiche(){
        System.out.println("Le centre du triangle se trouve en ("+this.getX()+", "
                +this.getY()+") et il a pour hauteur "+this.hauteur
                +" et base "+this.base+".");
    }
    
    //5.
    public Figure deformation(double coeffH, double coeffV){
        double b=this.base*coeffH;
        double h=this.hauteur*coeffV;
        return new Triangle(this.getX(), this.getY(), h, b);
    }
    
    //6.
    public double surface(){
        return this.base*this.hauteur/2;
    }
}