//1.
public class Carre extends Rectangle{
    //3.
    Carre(int x, int y, double c){
        super(x, y, c, c);
    }
    
    public void affiche(){
        System.out.println("Le centre du carré se trouve en ("+this.getX()+", "
                +this.getY()+") et il a pour côté "+this.getL()+".");
    }
}