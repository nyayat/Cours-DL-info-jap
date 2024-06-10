//5.8
public class ListePersonne {
    Cellule courant;
    
    ListePersonne(Personne c){
        this.courant=new Cellule(c);
    }
    
    ListePersonne(){
        this(null);
    }
    
    void ajoutListe(Personne p){
        this.courant=new Cellule(p, this.courant, null);
    }
    
    void affiche(){
        if(this.courant==null){
            System.out.println("La liste est vide");
        }
        else{
            this.courant.affiche();
        }
    }
    
    boolean similaire(ListePersonne c2){
        if(this.courant==null || c2.courant==null){
            return false;
        }
        boolean res=this.courant.similaire(c2.courant);
        while(this.courant.suivant!=null && !res){
            this.courant=this.courant.suivant;
            res=this.courant.similaire(c2.courant);
        }
        return res;
    }
}