public class ListeDEntiers {
    private Cellule premier;
    
    //1.1
    /*La liste vide est une liste qui n'a pas d'élément (ici, premier = null) donc de taille 0,
      c'est le point de départ d'une liste. */
    
    //1.5
    ListeDEntiers(){
        premier=null;
    }
    
    //1.6
    void add(int x){
        if(this.premier==null){
            this.premier=new Cellule(x);
        }
        else{
            this.premier.add(x);
        }
    }
    
    //2.1
    String description(){
        /*if(this.premier==null){
            return "la liste est vide";
        }
        return this.premier.description();*/
        return (this.premier==null)? "la liste est vide" : this.premier.description();
    }
    
    //2.2
    String descriptionElegant(){
        /*if(this.premier==null){
            return "la liste est vide";
        }*/
        return (this.premier==null)? "la liste est vide" : "("+this.premier.descriptionElegant()+")";
    }
    
    //2.3
    int taille(){
        /*if(this.premier==null){
            return 0;
        }
        return this.premier.taille();*/
        return (this.premier==null)? 0 : this.premier.taille();
    }
    
    //2.4
    void ajouter_en_i(int i, int v){
        if(this.premier==null || i<=0){
            this.premier=new Cellule(v, this.premier);
        }
        else{
            this.premier.ajouter_en_i(i, v);
        }
    }
    
    //2.5
    boolean supprimer_en_i(int i){
        if(this.premier==null || this.taille()<=i){
            return false;
        }
        if(i==0){
            this.premier=this.premier.getSuivante();
            return true;
        }
        return this.premier.supprimer_en_i(i);
    }
    
    //2.6   
    boolean egal(ListeDEntiers l){
        if(this.taille()==l.taille() && this.premier.getValeur()==l.premier.getValeur()){
            return this.premier.egal(l.premier);
        }
        return false;
    }
    
    //2.7
    int supprimer_k_premieres_occ(int k, int v){
        if(this.premier==null || k==0){
            return 0;
        }
        if(this.premier.getValeur()==v){
            this.premier=this.premier.getSuivante();
            return 1+this.supprimer_k_premieres_occ(k-1, v);
        }
        return this.premier.supprimer_k_premieres_occ(k, v);
    }
    
    //2.8
    int getValeurPos(int i){
        return this.premier.getValeurPos(i);
    }
    
    void change_valeur_en_i(int i, int vj){
        this.supprimer_en_i(i);
        this.ajouter_en_i(i, vj);
    }
    
    void swap(int i, int j){
        if(i>=this.taille() || j>=this.taille()){
            System.out.println("On sort de la liste.");
        }
        else{
            int vi=this.getValeurPos(i);
            int vj=this.getValeurPos(j);
            this.change_valeur_en_i(i, vj);
            this.change_valeur_en_i(j, vi);
        }
    }
    
    //2.9
    ListeDEntiers(Cellule c){
        premier=c;
    }
    
    ListeDEntiers obtenir_sous_liste_inf_k(int k){
    	ListeDEntiers res=new ListeDEntiers();
        if(this.premier==null){
            return res;
        }
        this.premier.obtenir_sous_liste_inf_k(k,res);
        return res;
    }
}