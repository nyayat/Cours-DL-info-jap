//2.1
public class Personnage{
    String nom;
    private EtatPersonnage etat;
    
    public String toString(){
        return nom+" : "+etat;
    }
    
    public boolean peutVoler(){
        return etat instanceof EtatSuperMan;
    }
    
    void changeEtat(boolean versSuperman) throws Exception{
        throw new Exception();
    }
    
    //2.2
    public class ClarkKent extends Personnage{
        void changeEtat(boolean versSuperman){
            //if(versSuperman) super.etat=new EtatSuperMan();
            if(versSuperman) super.etat=EtatSuperMan.getInstance();
        }

        public String toString(){
            if(super.peutVoler()) return "SuperMan";
            return "Clark Kent";
        }
    }
}