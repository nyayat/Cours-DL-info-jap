//Exercice 2
public class Groupe {
    private Cellule chefDeFile;
        
    //2.1
    Groupe(){
        this.chefDeFile=null;
    }
    
    //2.4
    void prendreTete(Robot r){
        if(r.nomCorrect()){
            if(this.chefDeFile==null){
                this.chefDeFile=new Cellule(r);
            }
            else{
                this.chefDeFile=new Cellule(r, this.chefDeFile);
            }
        }
    }
    
    //3.1
    void affiche(){
        if(this.chefDeFile==null){
            System.out.println("Ce groupe est vide.");
        }
        else{
            Cellule tmp=this.chefDeFile;
            while(tmp!=null){
                System.out.println(tmp.getRob().description());
                tmp=tmp.getSuivant();
            }
        }
    }
    
    //3.2
    void ajouteNouveau(Robot r){
        if(r.nomCorrect()){
            if(this.chefDeFile==null){
                this.chefDeFile=new Cellule(r);
            }
            else{
                this.chefDeFile.ajout(r);
            }
        }
    }
    
    //3.3
    int numerologie(){
        Cellule tmp=this.chefDeFile;
        int res=0;
        while(tmp!=null){
            res+=(int)tmp.getRob().getNom()-96;
            tmp=tmp.getSuivant();
        }
        res%=9;
        return res;
    }
    
    //3.4
    String bandName(){
        Cellule tmp=this.chefDeFile;
        String res="";
        while(tmp!=null){
            res+=tmp.getRob().getNom();
            tmp=tmp.getSuivant();
        }
        return res;
    }
    
    //3.6
    void chantez(){
        Cellule tmp=this.chefDeFile;
        while(tmp!=null){
            tmp.getRob().chante();
            tmp=tmp.getSuivant();
        }
    }
    
    //4.1
    private Groupe(Cellule c){  //nouveau constructeur
        this.chefDeFile=c;
    }
    
    Groupe couperAPartirDe(char nom){
        Groupe res=new Groupe(this.chefDeFile.couperAPartirDe(nom));
        return res;
    }
    
    //4.2
    Groupe prendrePause(){
        Groupe enForme=new Groupe();
        //on placera tous les membres qui ont plus que 0 énergie dans ce nouveau groupe
        Groupe enPause=new Groupe();
        //on placera tous les membres qui ont 0 énergie dans ce nouveau groupe
        Cellule tmpAncien=this.chefDeFile;
        //pour parcourir l'ancien groupe avec tous les membres
        while(tmpAncien!=null){
            if(tmpAncien.getRob().getEnergie()!=0){
                enForme.ajouteNouveau(tmpAncien.getRob());
            }
            else{
                enPause.ajouteNouveau(tmpAncien.getRob());
            }
            tmpAncien=tmpAncien.getSuivant();
        }
        this.chefDeFile=enForme.chefDeFile;
        //pour supprimer les membres fatigués du groupe,
        //on lui réaffecte le nouveau groupe qui ne contient que ceux en forme 
        return enPause;
    }
    
    //5.3
    int getLong(){
    	int res=0;
    	Cellule tmp=this.chefDeFile;
    	while(tmp!=null) {
    		res++;
    		tmp=tmp.getSuivant();
    	}
        return res;
    }
    
    /*On peut aussi responsabiliser les cellules : 
     
        private Cellule chefDeFile;
	
	//2.1
	public Groupe() {
            this.chefDeFile=null;
	}
	
	//4.1
	private Groupe(Cellule cell) {
            this.chefDeFile=cell;
	}
	
	//2.4
	void prendreTete(Robot r) {
            if(r.nomCorrect()) {
                this.chefDeFile=new Cellule(r, this.chefDeFile);
            }
	}
	
	//3.1
	void affiche() {
            Cellule tmp=this.chefDeFile;
            if(tmp==null) {
                System.out.println("Ce groupe est vide");
            }
            else{
                tmp.affiche();
            }
	}
	
	//3.2
	void ajouteNouveau(Robot n) {
            if(n.nomCorrect()){
                if(this.chefDeFile==null) {
                   this.chefDeFile=new Cellule(n);
                }
                else {
                   this.chefDeFile.ajouteNouveau(n);
                }
            }
	}
	
	//3.3
	int numerologie() {
            return (this.chefDeFile.numerologie());
	}
	
	//3.4
	String bandName() {
            return this.chefDeFile.bandName();
	}
	
	//3.6
	void chantez() {
            this.chefDeFile.chantez();
	}
	
	//4.1
	Groupe couperAPartirDe(char nom) {
            Groupe res=new Groupe(this.chefDeFile.couperAPartirDe(nom));
            return res;
	}
	
	//4.2
	Groupe prendrePause() {
            Groupe res=new Groupe(this.chefDeFile.enPause());
            this.chefDeFile=this.chefDeFile.enForme();
            return res;
	}
	
    Attention : on fera les modifications nécessaires dans la classe Cellule.
    */
}