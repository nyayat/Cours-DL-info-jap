import static java.lang.Math.sqrt;

//1.
abstract public class Figure {
    private int posX, posY;
    
    public Figure(int x, int y){
        posX=x;
        posY=y;
    }
    
    public int getX(){
        return this.posX;
    }
    
    public int getY(){
        return this.posY;
    }
    
    public abstract void affiche();
    
    //6.
    public double estDistantDe(Figure fig){
        double diffX=(this.posX-fig.posX)*(this.posX-fig.posX);
        double diffY=(this.posY-fig.posY)*(this.posY-fig.posY);
        return sqrt(diffX+diffY);
    }
    
    public abstract double surface();
    
    public void deplacement(int x, int y){
        this.posX=x;
        this.posY=y;
    }
}