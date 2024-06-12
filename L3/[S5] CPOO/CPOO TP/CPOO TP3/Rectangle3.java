//3.
public class Rectangle3{
    //3.1
    private double x0, y0, angle, len1, len2;
    
    Rectangle3(double x0, double y0, double angle, double len1, double len2){
        this.x0=x0;
        this.y0=y0;
        this.angle=angle;
        this.len1=len1;
        this.len2=len2;
    }
    
    double getX0(){
        return x0;
    }
    
    double getY0(){
        return y0;
    }
    
    double getAngle(){
        return angle;
    }
    
    double getLen1(){
        return len1;
    }
    
    double getLen2(){
        return len2;
    }
    
    //3.5
    void setX0(double x){
        this.x0=x;
    }
    
    void setY0(double x){
        this.y0=x;
    }
    
    void setAngle(double x){
        this.angle=x;
    }
    
    void setLen1(double x){
        this.len1=x;
    }
    
    void setLen2(double x){
        this.len2=x;
    }
}