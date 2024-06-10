//1.
public class Circle extends Ellipse{
    //3.
    Circle(int x, int y, double r){
        super(x, y, r, r);
    }
    
    public void affiche(){
        System.out.println("Le centre du cercle se trouve en ("+this.getX()+", "
                +this.getY()+") et il a pour rayon "+this.getR()+".");
    }
}