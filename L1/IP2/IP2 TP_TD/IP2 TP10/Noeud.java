import java.util.LinkedList;

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
    
    //1.2.a
    void espace(int n){
        for(int i=0; i<n; i++){
            System.out.print(' ');
        }
    }
    
    //1.2.b
    void affiche(int p){
        if(this.droit!=null){  //on cherche la feuille la plus à droite
            this.droit.affiche(p+1);
        }
        this.espace(p);
        System.out.println(this.etiquette);
        if(this.gauche!=null){  //puis on s'occupe des feuilles à gauche
            this.gauche.affiche(p+1);
        }
    }
    
    //2.1
    void afficheLargeur(){
        LinkedList<Noeud> list=new LinkedList<Noeud>();
        Noeud tmp=this;
        list.offer(tmp);
        while(!list.isEmpty()){
            tmp=list.poll();
            System.out.print(tmp.etiquette+" ");
            if(tmp.gauche!=null){
                list.offer(tmp.gauche);
            }
            if(tmp.droit!=null){
                list.offer(tmp.droit);
            }
        }
    }
    
    //2.2.a
    int profondeur(){
        if(this.gauche!=null && this.droit!=null){
            return 1+max(this.droit.profondeur(), this.gauche.profondeur());
        }
        else if(this.droit!=null){
            return 1+this.droit.profondeur();
        }
        else if(this.gauche!=null){
            return 1+this.gauche.profondeur();
        }
        return 0;
    }
    
    int max(int a, int b){
        return (a<b)? b : a;
    }
    
    //2.2.c && 2.2.d && 2.2.e
    void afficheTopdown(){
        LinkedList<Paire> list=new LinkedList<Paire>();
        Paire tmp;
        int tailleLargeur=0;  //nombre d'éléments sur cette ligne
        int tailleLargeurSuiv=1;  //nombre d'éléments sur la suivante
        int hautCour=0;  //hauteur courante
        list.offer(new Paire(this, hautCour));
        
        while(hautCour!=this.profondeur()+2){
            if(tailleLargeur==0){  //on en a fini avec cette ligne
                tailleLargeur=tailleLargeurSuiv;
                tailleLargeurSuiv=0;
                hautCour++;
                System.out.println();
                this.espace(square(this.profondeur()-hautCour));  //2.2.e
            }
            else{
                this.espace(square(this.profondeur()-hautCour+1)-1);  //2.2.e
                //espace entre l'élément traité et celui à traiter sur la même ligne
            }
            
            tmp=list.poll();
            //2.2.e
            if(tmp==null){
                this.espace(1);  //comme si c'était un chiffre invisible
                list.offer(null);  //pour la gauche
                list.offer(null);  //pour la droite
            }
            else{
                System.out.print(tmp.n.etiquette);
                tailleLargeur--;  //on a traité un élément en plus de la ligne
                if(tmp.n.gauche!=null){
                    list.offer(new Paire(tmp.n.gauche, hautCour));
                    tailleLargeurSuiv++;  //on a ajouté un élément à la ligne suivante
                }
                else{
                    list.offer(null);  //2.2.e
                }

                if(tmp.n.droit!=null){
                    list.offer(new Paire(tmp.n.droit, hautCour));
                    tailleLargeurSuiv++;  //on a ajouté un élément à la ligne suivante
                }
                else{
                    list.offer(null);  //2.2.e
                }
            }
        }
    }
    
    int square(int n){
        int res=1;
        for(int i=0; i<n; i++){
            res*=2;
        }
        return res;
    }
    
    /*//1.
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
    }*/
}