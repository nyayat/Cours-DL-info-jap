import java.util.*;
public class Personnage {
    //5.2
    private Informations inf;

    Personnage(Informations inf){
        this.inf=inf;
    }

    public String toString() {
        return(this.inf.toString());
    }

    boolean estVivant() {
        return(this.inf.getVitalite()>0);
    }

    void rebirth() {
        this.rebirth();
    }
    
    //5.3
    void attaque(Personnage def){
        Random rand=new Random();
        int max=((this.inf.getForce()-def.inf.getForce()>1)? (this.inf.getForce()-def.inf.getForce()):1);
        int n=rand.nextInt(max)+1;
        if(def.inf.getAgilite()<this.inf.getAgilite()){
            def.inf.setVitalite(def.inf.getVitalite()-n);
            //5.4
            System.out.println("Le joueur "+def.inf.getNom()+" a reçu "+n+" dégâts.");
        }
        else{
            def.inf.setVitalite(def.inf.getVitalite()-n/2);
            def.inf.setAgilite(def.inf.getAgilite()*2/3);
            //5.4
            System.out.println("Le joueur "+def.inf.getNom()+" a reçu "+n/2+" dégâts.");
        }
    }
    
    //5.4
    void lutteIt(Personnage def){
        while(def.inf.getVitalite()>0 && this.inf.getVitalite()>0){
            this.attaque(def);
            if(def.inf.getVitalite()>0){
                def.attaque(this);
            }
        }
        this.annonceGagnant(def);
    }
    
    void lutteRec(Personnage def){
        this.attaque(def);
        if(def.inf.getVitalite()>0) def.attaque(this);
        if(def.inf.getVitalite()>0 && this.inf.getVitalite()>0){
            this.lutteRec(def);
        }
        else{
            this.annonceGagnant(def);
        }
    }
        
    //annexe :
    void annonceGagnant(Personnage def){
        boolean attGagnant=(this.inf.getVitalite()>0);
        if(attGagnant){
            System.out.println("Gagnant : "+this.inf.getNom());
        }
        else{
            System.out.println("Gagnant : "+def.inf.getNom());
        }
    }
}