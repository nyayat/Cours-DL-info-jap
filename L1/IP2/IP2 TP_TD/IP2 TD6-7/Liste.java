public class Liste {
    private Element premier;
    private Element dernier;
    
    Liste(){
        this.dernier=null;
        this.premier=null;
    }
    
    //1.1
    int longeur(){
        if(this.premier==null){
            return 0;
        }
        return this.premier.longueur();
    }
    
    //1.2
    void afficher(){
        if(this.premier!=null){
            this.premier.afficher();
            return;
        }
        System.out.println("La liste est vide.");
    }
    
    //1.3
    void afficherInverse(){
        if(this.dernier!=null){
            this.dernier.afficherInverse();
            return;
        }
        System.out.println("La liste est vide.");
    }
    
    void afficherInverse_bis(){
        this.dernier.afficherInverse_bis();
    }
    
    //1.4
    void ajouterDebut(int valeur){
        this.premier=new Element(valeur, this.premier, null);
        if(this.premier.getS()!=null){
            this.premier.linkStoP();
            return;
        }
        dernier=premier;
    }
    
    //1.5
    void ajouterFin(int valeur){
        if(dernier==null){
            premier=new Element(valeur, null, null);
        }
        this.dernier=new Element(valeur, null, this.dernier);
        if(this.dernier.getP()!=null){
            this.dernier.linkPtoS();
            return;
        }
        dernier=premier;
    }
    
    //1.6
    void ajouterAvant(Element e, int valeur){
        if(this.premier==null){
            return;
        }
        if(this.premier.getV()==e.getV()){
            this.ajouterDebut(valeur);
            return;
        }
        this.premier.ajouterAvant(e,valeur);
    }
    
    //1.7
    void supprimer(Element e){  //première occurence
        if(this.premier==null){
            return;
        }
        if(this.premier.getV()==e.getV()){
            this.premier=this.premier.getS();
            this.premier.eraseP();
            return;
        }
        if(this.premier.supprimer(e)){
            this.dernier=this.dernier.getP();
            this.dernier.eraseS();
        }
    }
    
    //1.8
    void supprimerTout(int valeur){
        if(this.premier!=null){
            if(this.premier.getV()==valeur){  //on enlève les premiers éléments
                this.premier=this.premier.getS();
                this.premier.eraseP();
                this.supprimerTout(valeur);
            }
            if(this.dernier.getV()==valeur){  //on enlève les derniers éléments
                this.dernier=this.dernier.getP();
                this.dernier.eraseS();
                this.supprimerTout(valeur);
            }
            this.premier.supprimerTout(valeur);
        }
    }
    
    //1.9
    int somme(){
        if(this.premier==null){
            return 0;
        }
        return this.premier.somme();
    }
    
    //1.10
    boolean estTriee(){
        if(this.premier==null){
            return true;
        }
        return this.premier.estTriee();
    }
    
    //2.1
    void inverser(Element e){
        if(this.premier==null){
            return;
        }
        this.premier.inverser(e);
    }
    
    //2.2
    void passerEnOrdonnant(){
        if(this.premier==null){
            return;
        }
        this.premier.passerEnOrdonnant();
    }
    
    //2.3
    void trier(){
        while(!this.estTriee()){
            this.passerEnOrdonnant();
        }
    }
    
    //2.4
    boolean verifContrainte(){
        if(this.premier.longueur()<3){
            return true;
        }
        return this.premier.verifContrainte();
    }
    
    //2.5
    boolean estUnPalindrome(){
        Element p=this.premier;
        Element d=this.dernier;
        for(int i=0; i<this.premier.longueur()/2; i++){
            if(p.getV()!=d.getV()){
                return false;
            }
            p=p.getS();
            d=d.getP();
        }
        return true;
    }
    
    //2.6
    Liste moyenneLocale(){
        Liste res=new Liste();
        if(this.premier==null){
            return res;
        }
        this.premier.moyenneLocale(res);
        return res;
    }
    
    //2.7
    void annuleRedondances(){
        if(this.premier==null){
            return;
        }
        this.premier.annuleRedondances();
    }
}