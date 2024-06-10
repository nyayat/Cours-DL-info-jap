//5.8
public class Cellule {
    Personne contenu;
    Cellule suivant;
    Cellule precedent;
    
    Cellule(Personne p, Cellule suiv, Cellule prec){
        this.contenu=p;
        this.precedent=prec;
        this.suivant=suiv;
    }
    
    Cellule(Personne p){
        this(p, null, null);
    }
    
    void affiche(){
        if(this.suivant!=null){
            System.out.println(this.contenu.getPrenom());
            this.suivant.affiche();
        }
    }
    
    boolean similaire(Cellule c2){
        if(c2.suivant!=null){
            if(this.contenu.getNom().equals(c2.contenu.getNom())
            && this.contenu.getPrenom().equals(c2.contenu.getPrenom())){
                return true;
            }
            return (false || this.similaire(c2.suivant));
        }
        return false;
    }
}