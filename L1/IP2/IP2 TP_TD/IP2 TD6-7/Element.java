public class Element {
    private int valeur;
    private Element suivant;
    private Element precedent;
    
    Element(int valeur, Element suivant, Element precedent){
        this.valeur=valeur;
        this.suivant=suivant;
        this.precedent=precedent;
    }
    
    Element getS(){
        return this.suivant;
    }
    
    Element getP(){
        return this.precedent;
    }
    
    int getV(){
        return this.valeur;
    }
    
    void setS(int valeur){
        this.suivant.valeur=valeur;
    }
    
    void setP(int valeur){
        this.precedent.valeur=valeur;
    }
    
    void setV(int valeur){
        this.valeur=valeur;
    }
    
    //1.1
    int longueur(){
        int res=1;
        Element tmp=this;
        while(tmp.suivant!=null){
            res++;
            tmp=tmp.suivant;
        }
        return res;
    }
    
    //1.2
    void afficher(){
        System.out.println(this.valeur);
        if(this.suivant!=null){
            this.suivant.afficher();
        }
    }
    
    //1.3
    void afficherInverse(){
        System.out.println(this.valeur);
        if(this.precedent!=null){
            this.precedent.afficherInverse();
        }
    }
    
    void afficherInverse_bis(){
        Element tmp=this;
        while(tmp!=null){
            System.out.println(tmp.valeur);
            tmp=tmp.precedent;
        }
    }
    
    //1.4
    void linkStoP(){
        this.suivant.precedent=this;
    }
    
    //1.5
    void linkPtoS(){
        this.precedent.suivant=this;
    }
    
    //1.6
    void ajouterAvant(Element e, int valeur){
        Element tmp=this.suivant;  //premier est déjà traité
        while(tmp!=null){
            if(tmp.valeur==e.valeur){
                tmp.precedent=new Element(valeur, tmp, tmp.precedent);
                tmp.precedent.linkPtoS();
                return;
            }
            tmp=tmp.suivant;
        }
    }
    
    //1.7
    boolean supprimer(Element e){  //si true alors modifier le dernier
        Element tmp=this;
        while(tmp.suivant!=null && tmp.suivant.suivant!=null){
            if(tmp.suivant==e){
                tmp.suivant=tmp.suivant.suivant;
                tmp.linkStoP();
                return false;
            }
            tmp=tmp.suivant;
        }
        if(tmp==e){  //avant-dernier
            tmp.valeur=tmp.suivant.valeur;
            return true;
        }
        tmp=tmp.suivant;
        if(tmp==e){
            return true;
        }
        return false;  //pas dans la liste
    }
    
    void eraseS(){
        this.suivant=null;
    }
    
    void eraseP(){
        this.precedent=null;
    }
    
    //1.8
    void supprimerTout(int valeur){
        if(this.suivant!=null){
            if(this.suivant.valeur==valeur){
                this.suivant=this.suivant.suivant;
                this.linkStoP();
                this.supprimerTout(valeur);
                return;
            }
            this.suivant.supprimerTout(valeur);
        }
    }
    
    //1.9
    int somme(){
        if(this.suivant!=null){
            return this.valeur+this.suivant.somme();
        }
        return this.valeur;
    }
    
    //1.10
    boolean estTriee(){
        if(this.suivant==null){
            return true;
        }
        if(this.valeur<this.suivant.valeur){
            return this.suivant.estTriee();
        }
        return false;
    }
    
    //2.1
    void inverser(Element e){
        if(this.suivant==null){
            return;
        }
        if(this==e){
            int tmp=this.valeur;
            this.valeur=this.suivant.valeur;
            this.suivant.valeur=tmp;
            return;
        }
        this.suivant.inverser(e);
    }
    
    //2.2
    void passerEnOrdonnant(){
        if(this.suivant==null){
            return;
        }
        if(this.valeur>this.suivant.valeur){
            this.inverser(this);
            this.suivant.passerEnOrdonnant();
        }
    }
    
    //2.4
    boolean verifContrainte(){
        if(this.longueur()>2){
            if(this.valeur + this.suivant.valeur==this.suivant.suivant.valeur){
                return false;
            }
            return this.suivant.verifContrainte();
        }
        return true;
    }
    
    //2.6
    void moyenneLocale(Liste res){
        if(this.precedent==null){
        //pour la moyenne locale du premier élément, on fait comme s'il y avait un 0 au début
            res.ajouterDebut((this.valeur+this.suivant.valeur)/2);
            this.suivant.moyenneLocale(res);
            return;
        }
        if(this.suivant==null){  //idem que premier élément mais 0 à la fin
             res.ajouterFin((this.valeur+this.precedent.valeur)/2);
             return;
        }
        res.ajouterFin((this.precedent.valeur + this.valeur + this.suivant.valeur)/3);
        this.suivant.moyenneLocale(res);
    }
    
    //2.7
    void annuleRedondances(){
        if(this.suivant!=null && this.suivant.suivant!=null){
            if(this.valeur==this.suivant.valeur){
                this.suivant=this.suivant.suivant;
                this.linkStoP();
                //this.suivant.precedent=this;
                this.annuleRedondances();
                return;
            }
            this.suivant.annuleRedondances();
            return;
        }
        if(this.valeur==this.suivant.valeur){  //avant-dernier
            this.eraseS();
        }
    }
}



