import java.awt.geom.Point2D;

//4.
public class Parallelogram extends Quadrilateral{
    //4.4
    Parallelogram(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3){
        super(p1, p2, p3, new Point2D.Double(p3.x-p1.distance(p2), p1.y-p2.distance(p3)));
    }
    
    public boolean checkInvariants(){
        return getP1().distance(getP2())==getP3().distance(getP4())
            && getP1().distance(getP4())==getP2().distance(getP3());
    }
}