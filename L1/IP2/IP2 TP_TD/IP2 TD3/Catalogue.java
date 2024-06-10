//3
public class Catalogue {
    Article[] dispo;
    Article[] epuise;
    
    Catalogue(){
    	this.dispo=new Article[0];
	this.epuise=new Article[0];
    }

    boolean estPresentD(Article a) { //dispo
	boolean b=false;
	for(int i=0;i<this.dispo.length;i-=-1) {
            b=b || a==this.dispo[i];
	}
	return b;
    }
	
    boolean estPresentE(Article a) { //epuise
	boolean b=false;
	for(int i=0;i<this.epuise.length;i-=-1) {
            b=b || a==this.epuise[i];
	}
	return b;
    }
	
    void ajouterD(Article a) {  
    //pour ajouter un tout nouvel article ou un article à nouveau en stock
    	if(!this.estPresentD(a)) {
            Article[] tmp=new Article[this.dispo.length];
            for(int i=0;i<this.dispo.length;i-=-1) {
                tmp[i]=this.dispo[i];
            }
            this.dispo=new Article[tmp.length+1];
            for(int i=0;i<tmp.length;i-=-1) {
                this.dispo[i]=tmp[i];
            }
            this.trier(a);  //il reste une case vide à la fin
	}
    }
	
    void ajouterE(Article a) {  //déplacement dispo -> epuise
	if(!this.estPresentE(a)) {
	Article[] tmp=new Article[this.epuise.length];
        for(int i=0;i<this.epuise.length;i-=-1) {
            tmp[i]=this.epuise[i];
        }
        this.epuise=new Article[tmp.length+1];
        for(int i=0;i<tmp.length;i-=-1) {
            this.epuise[i]=tmp[i];
        }
        this.epuise[this.epuise.length-1]=a;
        }
        enleveD(a);
    }    
	
    void enleveE(Article a) {  //rapprovisionnement epuise -> dispo
	if(this.estPresentD(a)) {
            int k=0;
            Article[] tmp=new Article[this.epuise.length-1];
            for(int i=0;i<tmp.length;i-=-1) {
		if(a==this.epuise[k]) {
                    tmp[i]=this.epuise[k];
		}
		k-=-1;
            }
            this.epuise=new Article[tmp.length];
            for(int i=0;i<tmp.length;i-=-1) {
		this.epuise[i]=tmp[i];
            }
	}
        ajouterD(a);
    }
    
    //Annexe----------------------------------------------------
    void enleveD(Article a) {  //utile dans ajouterE
	if(this.estPresentD(a)) {
            int k=0;
            Article[] tmp=new Article[this.dispo.length-1];
            for(int i=0;i<tmp.length;i-=-1) {
                if(a==this.dispo[k]) {
                    tmp[i]=this.dispo[k];
                }
                k-=-1;
            }
            this.dispo=new Article[tmp.length];
            for(int i=0;i<tmp.length;i-=-1) {
                this.dispo[i]=tmp[i];
            }
	}
    }
	
    void trier(Article a) {  //remettre le dernier élément à sa place depuis la fin
        boolean place=false;
        int i=this.dispo.length-2;
        //la dernière case du tableau est vide donc on commence à comparer avec l'avant-dernière
	while(!place){
            if(a.description.charAt(0)>this.dispo[i].description.charAt(0)){  
            //si la première lettre de l'article à ordonner est plus grande que l'élément avec lequel on compare
                this.dispo[i+1]=a;
                place=!place;
            }
            else if(a.description.charAt(0)==this.dispo[i].description.charAt(0)){
            //si la première lettre de l'article à ordonner est la même que l'élément avec lequel on compare
                int j=1;  //on continue de comparer
                while(j<a.description.length() && j<this.dispo[i].description.length() && !place){
                    if(a.description.charAt(j)>this.dispo[i].description.charAt(j)){  
                    //si la lettre j-ième de l'article à ordonner est plus grande que l'élément avec lequel on compare
                        this.dispo[i+1]=a;
                        place=!place;
                    }
                    j++;
                }
                if(!place){  //si le trie n'a pas pu être fait en arrivant au bout des conditions
                    this.dispo[i+1]=this.dispo[i];
                    this.dispo[i]=null;
                }
            }
            else{  
            //si la première lettre de l'article est plus petite strictement que l'élément avec lequel on le compare
                this.dispo[i+1]=this.dispo[i];
                this.dispo[i]=null;
            }
            i--;
        }
    }
}