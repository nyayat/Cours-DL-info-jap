import java.awt.geom.Point2D;

//4.
public class Rectangle4 extends Parallelogram{
    //4.7
    Rectangle4(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3){
        super(p1, p2, p3);
    }
    
    protected boolean angleIs90(Point2D.Double a, Point2D.Double b, Point2D.Double c){
        return Math.cos(c.distance(a)/a.distance(b))==90;
    }
    
    public boolean checkInvariants(){
        return super.checkInvariants() && angleIs90(getP1(), getP2(), getP3())
            && angleIs90(getP2(), getP3(), getP4())
            && angleIs90(getP3(), getP4(), getP1())
            && angleIs90(getP4(), getP1(), getP2());
    }
}