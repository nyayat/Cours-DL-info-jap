public class Noeud {
    private Noeud gauche;
    private Noeud droit;
    
    public Noeud(Noeud g, Noeud d){
        this.gauche=g;
        this.droit=d;
    }
    
    //1.1
    void bourgeons(){
        if(this.gauche!=null){
            this.gauche.droit.bourgeons();
            this.gauche.gauche.bourgeons();
        }
        else this.gauche=new Noeud(null, null);
        if(this.droit!=null){
            this.droit.droit.bourgeons();
            this.droit.gauche.bourgeons();
        }
        else this.droit=new Noeud(null, null);
    }
    
    //1.2
    boolean estFeuille(){
        return (this.gauche==null && this.droit==null);
    }
    
    void elagage(){
        if(this.gauche.estFeuille()) this.gauche=null;
        else this.gauche.elagage();
        if(this.droit.estFeuille()) this.droit=null;
        else this.droit.elagage();
    }
    
    //1.3
    void croissance(){
        if(this.gauche!=null){
            this.gauche.croissance();
            this.gauche=new Noeud(this.gauche.gauche, this.gauche.droit);
        }
        if(this.droit!=null){
            this.droit.croissance();
            this.droit=new Noeud(this.droit.gauche, this.droit.droit);
        }
    }
    
    //1.4
    void decroissance(){
        if(this.gauche!=null){
            if(this.gauche.gauche!=null){
                this.gauche=this.gauche.gauche;
                this.gauche.decroissance();
            }
        }
        if(this.droit!=null){
            if(this.droit.droit!=null){
                this.droit=this.droit.droit;
                this.droit.decroissance();
            }
        }
    }
    
    //1.5
    int bourgeonsI(){
        if(this.gauche==null && this.droit==null){
            this.gauche=new Noeud(null, null);
            this.droit=new Noeud(null, null);
            return 2;
        }
        if(this.gauche==null){
            this.gauche=new Noeud(null, null);
            return 1+this.droit.bourgeonsI();
        }
        if(this.droit==null){
            this.droit=new Noeud(null, null);
            return 1+this.gauche.bourgeonsI();
        }
        return this.gauche.bourgeonsI()+this.droit.bourgeonsI();
    }
    
    int elagageI(){
        if(this.gauche.estFeuille() && this.droit.estFeuille()){
            this.gauche=null;
            this.droit=null;
            return 2;
        }
        if(this.gauche.estFeuille()){
            this.gauche=null;
            return 1+this.droit.elagageI();
        }
        if(this.droit.estFeuille()){
            this.droit=null;
            return 1+this.gauche.elagageI();
        }
        return this.gauche.elagageI()+this.droit.elagageI();
    }
    
    int croissanceI(){
        if(this.gauche!=null && this.droit!=null){
            this.gauche=new Noeud(this.gauche.gauche, this.gauche.droit);
            this.droit=new Noeud(this.droit.gauche, this.droit.droit);
            return 2+this.gauche.gauche.croissanceI()+this.droit.droit.croissanceI();
        }
        if(this.gauche!=null){
            this.gauche=new Noeud(this.gauche.gauche, this.gauche.droit);
            return 1+this.gauche.gauche.croissanceI();
        }
        if(this.droit!=null){
            this.droit=new Noeud(this.droit.gauche, this.droit.droit);
            return 1+this.droit.droit.croissanceI();
        }
        return 0;
    }
    
    int decroissanceI(){
        if(this.gauche!=null && this.droit!=null){
            if(this.gauche.gauche!=null) this.gauche=this.gauche.gauche;
            if(this.droit.droit!=null) this.droit=this.droit.droit;
            return 2+this.gauche.decroissanceI()+this.droit.decroissanceI();
        }
        if(this.gauche!=null){
            if(this.gauche.gauche!=null){
                this.gauche=this.gauche.gauche;
                return 1+this.gauche.decroissanceI();
            }
        }
        if(this.droit!=null){
            if(this.droit.droit!=null){
                this.droit=this.droit.droit;
                return 1+this.droit.decroissanceI();
            }
        }
        return 0;
    }
    
    //2.1
    Arbre sousArbre(String chemin){
        if(chemin.length()==0) return new Arbre(this);
        if(chemin.charAt(0)=='g' && this.gauche!=null){
            return this.gauche.sousArbre(chemin.substring(1));
        }
        if(chemin.charAt(0)=='d' && this.droit!=null){
            return this.droit.sousArbre(chemin.substring(1));
        }
        return null;
    }
    
    //2.2
    void greffe(Arbre a, String chemin){
        if(chemin.length()==0) return;  //invalide
        if(chemin.charAt(0)=='g' && this.gauche!=null){
            if(chemin.length()==1) this.gauche=a.getRacine();
            else this.gauche.greffe(a, chemin.substring(1));
        }
        else if(chemin.charAt(0)=='d' && this.droit!=null){
            if(chemin.length()==1) this.droit=a.getRacine();
            else this.droit.greffe(a, chemin.substring(1));
        }
    }
}