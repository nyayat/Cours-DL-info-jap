public class Noeud{
    private int etiquette;
    private Noeud gauche;
    private Noeud droit;
    
    Noeud(int etiquette, Noeud g, Noeud d){
        this.etiquette=etiquette;
        this.gauche=g;
        this.droit=d;
    }
    
    Noeud(int etiquette){
        this(etiquette, null, null);
    }
    
    //1.
    void afficheInfixe(){
        if(this.gauche!=null){
            this.gauche.afficheInfixe();
        }
        System.out.print(this.etiquette + " ");
        if(this.droit!=null){
            this.droit.afficheInfixe();
        }
    }
    
    //3.
    void affichePrefixe(){
        System.out.print(this.etiquette + " ");
        if(this.gauche!=null){
            this.gauche.affichePrefixe();
        }
        if(this.droit!=null){
            this.droit.affichePrefixe();
        }
    }
    
    void afficheSuffixe(){
        if(this.gauche!=null){
            this.gauche.afficheSuffixe();
        }
        if(this.droit!=null){
            this.droit.afficheSuffixe();
        }
        System.out.print(this.etiquette + " ");        
    }
    
    //4.
    int nbDeNoeuds(){
        if(this.gauche!=null && this.droit!=null){
            return 2+this.gauche.nbDeNoeuds()+this.droit.nbDeNoeuds();
        }
        else if(this.gauche!=null){
            return 1+this.gauche.nbDeNoeuds();
        }
        else if(this.droit!=null){
            return 1+this.droit.nbDeNoeuds();
        }
        return 0;
    }
    
    //5.
    int somme(){
        if(this.gauche!=null && this.droit!=null){
            return this.etiquette+this.gauche.somme()+this.droit.somme();
        }
        else if(this.gauche!=null){
            return this.etiquette+this.gauche.somme();
        }
        else if(this.droit!=null){
            return this.etiquette+this.droit.somme();
        }
        return this.etiquette;
    }
    
    //6.
    int profondeur(){
        if(this.gauche!=null && this.droit!=null){
            return max(1+this.gauche.profondeur(), 1+this.droit.profondeur());
        }
        else if(this.gauche!=null){
            return 1+this.gauche.profondeur();
        }
        else if(this.droit!=null){
            return 1+this.droit.profondeur();
        }
        return 1;
    }
    
    int max(int a, int b){
        if(a<b){
            return b;
        }
        return a;
    }
    
    //7.
    boolean recherche(int e){
        if(this.etiquette==e){
            return true;
        }
        if(this.gauche!=null && this.droit!=null){
            return (this.gauche.recherche(e) || this.droit.recherche(e));
        }
        else if(this.gauche!=null){
            return this.gauche.recherche(e);
        }
        else if(this.droit!=null){
            return this.droit.recherche(e);
        }
        return false;
    }
	
	//8.
    int getEtiquette(){
        return this.etiquette;
    }
    
    void copieArbre(Noeud n){
        if(n.gauche!=null){
            this.gauche=new Noeud(n.gauche.etiquette);
            this.gauche.copieArbre(n.gauche);
        }
        if(n.droit!=null){
            this.droit=new Noeud(n.droit.etiquette);
            this.droit.copieArbre(n.droit);
        }
    }
    
    //9.
    void ajout(int[]tab){
        int r=tab.length/2;
        int[]tab1=new int[r];
        int[]tab2=new int[tab.length-r-1];
        for(int i=0; i<r; i++){  //les valeurs de 0 à r-1
            tab1[i]=tab[i];
        }
        for(int i=0; i<tab2.length; i++){ //les valeurs de r+1 à longueur-1
            tab2[i]=tab[r+i+1];
        }
        if(tab1.length!=0){  //tab1 a plus d'une valeur
            this.gauche=new Noeud(tab1[tab1.length/2]);
            if(tab1.length>1){  //nécessité de continuer sur la gauche ?
                this.gauche.ajout(tab1);
            }
        }
        if(tab2.length!=0){  //tab2 a plus d'une valeur
            this.droit=new Noeud(tab2[tab2.length/2]);
            if(tab2.length>1){  //nécessité de continuer sur la droite ?
                this.droit.ajout(tab2);
            }
        }
    }
}