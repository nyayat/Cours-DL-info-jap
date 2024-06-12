import java.awt.geom.Point2D;

//4.
public class Squarre4 extends Rectangle4{
    //4.7
    public Squarre4(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3){
        super(p1, p2, p3);
    }
    
    public boolean checkInvariants(){
        return super.checkInvariants() && getP1().distance(getP2())==getP2().distance(getP3());
    }
}