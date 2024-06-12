import java.awt.geom.Point2D;

//4.
public class Quadrilateral implements Shape2D{
    //4.2
    private final Point2D.Double p1, p2, p3, p4;
    
    Quadrilateral(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double p4){
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        this.p4=p4;
    }
    
    public double perimeter(){
        return p1.distance(p2)+p2.distance(p3)+p3.distance(p4)+p4.distance(p1);
    }
    
    public double surface(){
        double x1=p3.getX()-p1.getX();
        double x2=p4.getX()-p2.getX();
        double y1=p3.getY()-p1.getY();
        double y2=p4.getY()-p2.getY();
        return (x1*y2-x2*y1)/2;
    }
    
    //4.3
    Point2D.Double getP1(){
        return new Point2D.Double(p1.x, p1.y);
    }
    
    Point2D.Double getP2(){
        return new Point2D.Double(p2.x, p2.y);
    }
    
    Point2D.Double getP3(){
        return new Point2D.Double(p3.x, p3.y);
    }
    
    Point2D.Double getP4(){
        return new Point2D.Double(p4.x, p4.y);
    }
    
    //4.6
    /*L encapsulation est brisee, il faudrait non pas directement affecter les
        arguments passes en parametre aux attributs, mais peut-etre creer de
        nouvelles instances avant l affectation.
        Sinon il faudrait creer sa propre classe Point, immutable.
    */
}