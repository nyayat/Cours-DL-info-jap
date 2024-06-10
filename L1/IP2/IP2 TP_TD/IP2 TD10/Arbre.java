public class Arbre {
    private Noeud racine;
    
    public Arbre(){
        this.racine=null;
    }
    
    public Arbre(Noeud n){
        this.racine=n;
    }
    
    //1.1
    void bourgeons(){
        if(this.racine!=null) this.racine.bourgeons();
    }
    
    //1.2
    void elagage(){
        if(this.racine!=null){
            if(this.racine.estFeuille()) this.racine=null;
            else this.racine.elagage();
        }
    }
    
    //1.3
    void croissance(){
        if(this.racine!=null) this.racine.croissance();
    }
    
    //1.4
    void decroissance(){
        if(this.racine!=null) this.racine.decroissance();
    }
    
    //2.1
    Arbre sousArbre(String chemin){
        if(this.racine!=null) return this.racine.sousArbre(chemin);
        return null;
    }
    
    //2.2
    void greffe(Arbre a, String chemin){
        if(this.racine!=null) this.racine.greffe(a, chemin);
    }
    
    Noeud getRacine(){
        return this.racine;
    }
    
    //2.3
    void echange(String chemin1, String chemin2){
        Arbre sA1=this.sousArbre(chemin1);
        Arbre sA2=this.sousArbre(chemin2);
        if(sA1!=null && sA2!=null){
            this.greffe(sA2, chemin1);
            this.greffe(sA1, chemin2);
        }
    }
}