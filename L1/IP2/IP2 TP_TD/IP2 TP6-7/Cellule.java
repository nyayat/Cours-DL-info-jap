public class Cellule {
    //1.1.a
    private Cellule precedente;
    private Cellule suivante;
    private boolean noire;
    
    //1.1.b
    Cellule getP(){
        return this.precedente;
    }
    
    Cellule getS(){
        return this.suivante;
    }
    
    boolean getN(){
        return this.noire;
    }
    
    //1.1.c
    Cellule(boolean noire){
        this.noire=noire;
        this.precedente=null;
        this.suivante=null;
        //2.2
        this.prochainEtat=false;
    }
    
    //1.1.d
    void afficher(){
        char res =(noire)? '#':'-';
        System.out.print(res);
    }      
    
    //1.2
    void setS(Cellule c){
        this.suivante=c;
        this.suivante.precedente=this;
    }
    
    void setP(Cellule c){
        this.precedente=c;
        this.precedente.suivante=this;
    }
    
    //1.3
    void affichage(){
        if(this.suivante!=null){
            this.afficher();
            this.suivante.affichage();
        }
    }
    
    //2.2
    private boolean prochainEtat;
    
    void setProchaineE(boolean b){
        this.prochainEtat=b;
    }
    
    //2.3
    void prochaineEtape(){
        if(this.suivante!=null){
            this.prochainEtat=!(this.precedente.noire==this.noire && this.noire==this.suivante.noire);
            this.suivante.prochaineEtape();
            return;
        }
        this.prochainEtat=!(this.precedente.noire==this.noire && this.noire==false);
    }
    
    //2.4
    void miseAJour(){
        if(this.suivante!=null){
            this.noire=this.prochainEtat;
            this.suivante.miseAJour();
            return;
        }
        this.noire=this.prochainEtat;
    }
    
    //2.10
    int nbNoire(boolean b1, boolean b2, boolean b3){  //retourne le nombre de true
        int res=0;
        if(b1){
            res++;
        }
        if(b2){
            res++;
        }
        if(b3){
            res++;
        }
        return res;
    }
    
    void prochaineEtapeBis(){
        if(this.suivante!=null){
            if(this.nbNoire(this.precedente.noire, this.noire, this.suivante.noire)>1){
                this.prochainEtat=true;
            }
            else{
                this.prochainEtat=false;
            }
            this.suivante.prochaineEtapeBis();
        }
        else{
            if(this.nbNoire(this.precedente.noire, this.noire, false)>1){
                this.prochainEtat=true;
            }
            else{
                this.prochainEtat=false;
            }
        }
    }
}