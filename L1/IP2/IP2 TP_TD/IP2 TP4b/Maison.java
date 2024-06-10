public class Maison {
    
    //3.
    private Espion habitant;
    private Maison ligneVers;
    private char id;
    static int nommer=97;
    
    Maison(Espion h, Maison lV){
        habitant=h;
        ligneVers=lV;
        id=(char)nommer++;
    }
    
    Maison(Espion h){
        this(h,null);
    }
    
    //12.
    Maison(){
    	this(null);
    }
        
    String description(){
        String connecte=(this.ligneVers==null)? "non connectée" : "vers maison "+this.ligneVers.id;
        String res="maison "+this.id+" : habitée par "+this.habitant.description()+", ligne "+connecte;
        return res;
    }
    
    //4.
    void afficher(){
        System.out.println("----------");
        Maison tmp=this.ligneVers;
        String mai="Maison   "+this.id;
        String hab="Habitant "+this.habitant.getId();
        String loy="Loyal ?  ";
        loy+=(this.habitant.getLoyal())? "O" : "N";
        while(tmp!=null){
            mai+=" -> "+tmp.id;
            hab+="    "+tmp.habitant.getId();
            loy+="    ";
            loy+=(tmp.habitant.getLoyal())? "O" : "N";
            tmp=tmp.ligneVers;
        }
        System.out.println(mai);
        System.out.println(hab);
        System.out.println(loy);
    }
    
    //5.
    boolean estPresent(Espion e){
        Maison tmp=this;
        while(tmp!=null){
            if(tmp.habitant==e){
                return true;
            }
            tmp=tmp.ligneVers;
        }
        return false;
    }
    
    //6.
    void ajouterEspion(Espion e){
        if(this.estPresent(e)){
            System.out.println("L'espion "+e.getId()+" est déjà dans le réseau de la maison de "+this.id);
        }
        else{
            Maison tmp=this;
            while(tmp.ligneVers!=null){
                tmp=tmp.ligneVers;
            }
            tmp.ligneVers=new Maison(e);
        }
    }
    
    //7.
    void setLigne(Maison m){
        m.ligneVers=this.ligneVers;
        this.ligneVers=m;
    }
    
    //8.
    void desertion(Espion e) {
    	if(this.estPresent(e)) {
    		Maison tmp=this;
            while(tmp.habitant!=e){
                tmp=tmp.ligneVers;
            }
            tmp.habitant.setLoyal(false);
    	}
    }
    
    //9.
    void insertion(Espion e) {
    	Maison tmp=this;
    	while(tmp.ligneVers!=null && tmp.ligneVers.habitant.getLoyal()) {
    		tmp=tmp.ligneVers;
    	}
    	Maison add=new Maison(e);
    	if(tmp.ligneVers.habitant.getLoyal()) {
    		add.setLigne(tmp);
    	}
    	else {
    		tmp.ajouterEspion(e);
    	}
    }
    
    Espion getHabitant() {
    	return this.habitant;
    }
    
    //10.
    void enlever(Espion e) {
    	Maison tmp=this;
    	if(tmp.estPresent(e)) {
    		while(tmp.ligneVers.habitant!=e) {
    			tmp=tmp.ligneVers;
    		}
    		tmp.ligneVers=tmp.ligneVers.ligneVers;
    	}
    }
    
    //11.
    Espion premierLoyal() {
    	Maison tmp=this;
    	while(tmp!=null && !tmp.habitant.getLoyal()) {
    		tmp=tmp.ligneVers;
    	}
    	return tmp.habitant;
    }
    
    //12.
    Maison[] nettoyer() {
    	Maison propre=new Maison();
    	Maison sale=this; //On extrait les propres de ce bourbier
    	while(sale.ligneVers!=null) {
    		Espion ok=sale.premierLoyal();
    		propre.insertion(ok);
    		sale.enlever(ok);
    	}
    	Maison[] res= {propre, sale};
    	return res;
    }
}