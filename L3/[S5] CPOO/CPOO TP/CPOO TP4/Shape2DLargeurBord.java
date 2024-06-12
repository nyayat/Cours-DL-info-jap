//4.
public class Shape2DLargeurBord extends Shape2DDecoree{    
    Shape2DLargeurBord(Shape2D shape){
        shape2d=shape;
    }
    
    public double perimeter(){
        return shape2d.perimeter();
    }

    public double surface(){
        return shape2d.surface();
    }
}