import static java.lang.Math.PI;

//1.
public class Ellipse extends Figure implements Deformable{
    //2.
    private final double grand_rayon, petit_rayon;
    
    Ellipse(int x, int y, double gr, double pr){
        super(x, y);
        this.grand_rayon=gr;
        this.petit_rayon=pr;
    }
    
    public void affiche(){
        System.out.println("Le centre de l'ellipse se trouve en ("+this.getX()+", "
                +this.getY()+") et elle a pour grand rayon "+this.grand_rayon
                +" et petit rayon "+this.petit_rayon+".");
    }
    
    public double getR(){  //pour l'affichage dans Circle
        return this.grand_rayon;
    }
    
    //5.
    public Figure deformation(double coeffH, double coeffV){
        double gr=this.grand_rayon*coeffH;  //par défaut grand_rayon pour l'horizontal
        double pr=this.petit_rayon*coeffV;  //et petit_rayon pour la verticale
        if (gr==pr) return new Circle(this.getX(), this.getY(), gr);
        return new Ellipse(this.getX(), this.getY(), gr, pr);
    }
    
    //6.
    public double surface(){
        return this.grand_rayon*this.petit_rayon*PI;
    }
}