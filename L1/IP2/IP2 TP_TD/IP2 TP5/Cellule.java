public class Cellule {
    private int valeur;
    private Cellule suivante;
    
    //1.2
    /*Une cellule dans une liste est rattachée à la suivante,
      d'où l'utilisation du mot "chaînée" dans "liste chaînée".
      Cette définition d'une cellule dans une autre n'est pas absurde,
      car depuis la cellule actuelle, on renvoie vers l'adresse de la suivante et
      non pas vers une cellule qui définie la cellule actuelle (boucle infinie dans ce cas). */
    
    //1.3
    Cellule(int n){
        valeur=n;
    }
    
    //1.4
    Cellule(int n, Cellule c){
        valeur=n;
        suivante=c;
    }
    
    //1.6
    void add(int x){
        if(this.suivante!=null){
            this.suivante.add(x);
        }
        else{
            this.suivante=new Cellule(x);
        }
    }
    
    //1.7
    int getValeur(){
        return this.valeur;
    }
    
    Cellule getSuivante(){
        return this.suivante;
    }
    
    //2.1
    String description(){
        if(this.suivante!=null){
            return this.valeur+" "+this.suivante.description();
        }
        return String.valueOf(this.valeur);
    }
    
    //2.2
    String descriptionElegant(){
        if(this.suivante!=null){
            return this.valeur+" ; "+this.suivante.description();
        }
        return String.valueOf(this.valeur);
    }
    
    //2.3
    int taille(){
        if(this.suivante!=null){
            return 1+this.suivante.taille();
        }
        return 1;
    }
    
    //2.4
    void ajouter_en_i(int i, int v){
        if(this.suivante!=null && i>1){
            this.suivante.ajouter_en_i(i-1, v);
        }
        else{
            this.suivante=new Cellule(v, this.suivante);
        }
    }
    
    //2.5
    boolean supprimer_en_i(int i){
        if(i>1){
            return this.suivante.supprimer_en_i(i-1);
        }
        this.suivante=this.suivante.suivante;
        return true;
    }
    
    //2.6
    boolean egal(Cellule cL){
        if(this.valeur==cL.valeur && this.suivante!=null){
            return this.suivante.egal(cL.suivante);
        }
        if(this.valeur!=cL.valeur){
            return false;
        }
        return true;
    }
    
    //2.7
    int supprimer_k_premieres_occ(int k, int v){
        if(k>0 && this.suivante!=null && this.suivante.valeur==v){
            this.suivante=this.suivante.suivante;
            return 1+this.supprimer_k_premieres_occ(k-1, v);
        }
        if(k>0 && this.suivante!=null){
            return this.suivante.supprimer_k_premieres_occ(k, v);
        }
        return 0;
    }
    
    //2.8
    int getValeurPos(int i){  //on suppose qu'il n'y aura pas de débordement
        if(i==0){
            return this.valeur;
        }
        return this.suivante.getValeurPos(i-1);
    }
    
    //2.9
    void obtenir_sous_liste_inf_k(int k, ListeDEntiers c){
    	if(this.valeur<k) {
    		c.add(this.valeur);
    	}
        if(this.suivante!=null){
        	this.suivante.obtenir_sous_liste_inf_k(k, c);
        }
    }
}