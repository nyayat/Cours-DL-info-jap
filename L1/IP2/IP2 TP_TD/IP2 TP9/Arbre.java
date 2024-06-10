public class Arbre {
    private Noeud sommet;
    
    Arbre(Noeud sommet){
        this.sommet=sommet;
    }
    
    Arbre(){
        //this(null);  //ne marche plus :)
        this.sommet=null;
    }
    
    //1.
    void afficheInfixe(){
        if(sommet==null){
            System.out.println("Cet arbre est vide.");
        }
        else{
            this.sommet.afficheInfixe();
        }
    }
    
    //3.
    void affichePrefixe(){
        if(sommet==null){
            System.out.println("Cet arbre est vide.");
        }
        else{
            this.sommet.affichePrefixe();
        }
    }
    
    void afficheSuffixe(){
        if(sommet==null){
            System.out.println("Cet arbre est vide.");
        }
        else{
            this.sommet.afficheSuffixe();
        }
    }
    
    //4.
    int nbDeNoeuds(){
        if(sommet==null){
            return 0;
        }
        return 1+this.sommet.nbDeNoeuds();
    }
    
    //5.
    int somme(){
        if(sommet==null){
            return 0;
        }
        return this.sommet.somme();
    }
    
    //6.
    int profondeur(){
        if(sommet==null){
            return 0;
        }
        return this.sommet.profondeur()-1;
    }
    
    //7.
    boolean recherche(int e){
        if(sommet==null){
            return false;
        }
        return this.sommet.recherche(e);
    }
    
    //8.
    //8.
    Arbre(Arbre a){
        this(new Noeud(a.sommet.getEtiquette()));
        this.sommet.copieArbre(a.sommet);
    }
    
    //9.
    Arbre(int[]tab){
        if(tab.length==0){
            //this();  //ne marche pas :)
            this.sommet=null;
            return;
        }
        int r=tab.length/2;
        this.sommet=new Noeud(tab[r]);
        if(tab.length!=1){  //cas où il y aurait au moins deux éléments à ajouter dans l'arbre
            this.sommet.ajout(tab);
        }
    }
}