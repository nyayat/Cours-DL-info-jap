public class Reseau {
    
    //4
    private String nom;
    private Maison siege;
    
    Reseau(String n, Maison s){
        nom=n;
        siege=s;
    }
    
    Reseau(String n){
        this(n,null);
    }
    
    void afficher(){
        System.out.println(this.nom);
        this.siege.afficher();
    }
    
    //5.
    boolean estPresent(Espion e){
        return this.siege.estPresent(e);
    }
    
    //6.
    void ajouterEspion(Espion e){
        if(siege!=null){
            this.siege.ajouterEspion(e);
        }
        else{
            this.siege=new Maison(e);
        }
    }
    
    //7.
    void setLigne(Maison m1, Maison m2){//on met m2 après m1
    	m1.setLigne(m2);
    }
    
    //8.
    void desertion(Espion e) {
    	this.siege.desertion(e);
    }
    
    //9.
    void insertion(Espion e) {
    	if(!this.siege.getHabitant().getLoyal()) {
    		Maison add=new Maison(e,this.siege);
    		this.siege=add;
    	}
    	else {
    		this.siege.insertion(e);
    	}
    }
    
    //10.
    void enlever(Espion e) {
    	this.siege.enlever(e);
    }
    
    //11.
    Espion premierLoyal() {
    	return this.siege.premierLoyal();
    }
    
    //12.
    Reseau nettoyer() {//[0]=>propre ; [1]=>sale
    	this.siege=this.siege.nettoyer()[0];
    	Reseau sale=new Reseau(this.nom+"Deloyal", this.siege.nettoyer()[0]);
    	return sale;
    }
}