public class Fourmi {
    int x, y, orientation;
    Grille gril;
    
    public Fourmi(int x, int y, int orientation, Grille gril){
        this.x=x;
        this.y=y;
        this.orientation=orientation;
        this.gril=gril;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void afficheToi(){
        System.out.println("La fourmi se trouve en position ("+getX()+","+getY()+").");
    }
    
    public void tourne(boolean aGauche){
        if(aGauche){
            this.orientation=(this.orientation+3)%4;
        }
        else{
            this.orientation=(this.orientation+5)%4;
        }
    }
    
    public void unPas(){
        if(this.orientation==0){
            this.y--;
        }
        else if(this.orientation==1){
            this.x++;
        }
        else if(this.orientation==2){
            this.y++;
        }
        else{
            this.x--;
        }
    }
    
    public boolean unMouvement(){
        tourne(!gril.getCouleur(this.x, this.y));
        gril.changeCouleur(this.x, this.y);
        unPas();
        return(gril.estHorsGrille(this.x, this.y));
    }
}
