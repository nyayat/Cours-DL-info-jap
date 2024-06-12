//2.1
public class ClarkKent extends Personnage{
    /*void changeEtat(boolean versSuperman){
        if(versSuperman) super.etat=new EtatSuperMan();
    }*/
    
    public String toString(){
        if(super.peutVoler()) return "SuperMan";
        return "Clark Kent";
    }
}