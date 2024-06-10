public class TableRonde {
	/*J'ai pensé qu'il serait plus judicieux de laisser tous les attributs en public, du fait qu'on a l'attribut courant
	  et ainsi on traite à chaque fois le courant (courant devient le pointeur et plus seulement un "premier". */
	  
    CellRob courant;
    
    //2.2
    TableRonde(Robot r){
        courant=new CellRob(r, null, null);
        courant.prec=courant;
        courant.suiv=courant;
    }
    
    //2.3
    void affiche(){
        if(this.courant!=null){
            for(int i=0; i<this.nbRobotAutour(); i++){
                this.courant.r.description();
                this.courant=this.courant.suiv;
            }
        }
    }
    
    int nbRobotAutour(){
	//nombre de robots qu'il y a autour de la table ronde
        if(this.courant==null){
            return 0;
        }
        int idRecherche=this.courant.r.id;  //point de départ qu'on cherche à atteindre
        int res=1;
        this.courant=this.courant.suiv;
        while(this.courant.r.id!=idRecherche){
            res++;
            this.courant=this.courant.suiv;
        }
        return res;
    }
    
    //2.4
    void ajouteRob(Robot r){
        this.courant.prec=new CellRob(r, this.courant, this.courant.prec);
        this.courant.prec.prec.suiv=this.courant.prec;
        //pour lier l'ancien dernier au nouveau robot (les autres liaisons sont déjà faites)
    }
    
    //2.5
    boolean supprimer(int id){
        boolean fait=false;
        if(this.courant==null || Robot.nbRob<=id){  //pas dans la table
            return fait;
        }
        int max=this.nbRobotAutour();  //nombre maximum d'identité à vérifier
        if(max==1){  //dans le cas où il ne reste qu'un élément dans la table
            if(this.courant.r.id==id){
                this.courant=null;
                fait=!fait;
            }
            return fait;
        }
        while(max>0 && !fait){  //autres cas
            if(this.courant.r.id==id){
                this.courant.prec.suiv=this.courant.suiv;
                this.courant.suiv.prec=this.courant.prec;
                fait=!fait;
            }
            this.courant=this.courant.suiv;
            max--;
        }
        return fait;
    }
    
    //3.3
    void discussion(){
        while(this.nbRobotAutour()!=1){
            this.courant.r.parle(5);
            if(this.courant.r.finiDeParler()){
            //quand on supprime un robot, le courant devient le robot suivant automatiquement
                this.supprimer(this.courant.r.id);
            }
            else{
                this.courant=this.courant.suiv;
            }
        }
        while(!this.courant.r.finiDeParler()){  //dernier robot qui doit parler
            this.courant.r.parle(5);
        }
        this.supprimer(this.courant.r.id);
    }
}