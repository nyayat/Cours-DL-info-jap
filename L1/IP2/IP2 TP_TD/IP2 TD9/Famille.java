public class Famille {
    private Personne sommet;
    
    Famille(Personne sommet){
        this.sommet=sommet;
    }
    
    Famille(){
        this(null);
    }
    
    //1.1
    boolean estFrereOuSoeur(Personne p){
        if(sommet==null){
            return false;
        }
        return this.sommet.estFrereOuSoeur(p);
    }
    
    //1.2
    boolean estCousinGermain(Personne p){
        if(sommet==null || this.estFrereOuSoeur(p)){
            return false;
        }
        return this.sommet.estCousinGermain(p);
    }
    
    //2.3
    int nbAscendantsVivants(){
        if(sommet==null){
            return 0;
        }
        return this.sommet.nbAscendantsVivants();
    }
    
    //2.4
    boolean possedeCommeAscendants(Personne p){
        if(sommet==null){
            return false;
        }
        return this.sommet.possedeCommeAscendants(p);  //on ne se compte pas
    }
    
    //3.5
    int distanceDAscendance(Personne p){
        if(sommet==null || !this.sommet.possedeCommeAscendants(p) || p==this.sommet){
            return 0;
        }
        return this.sommet.distanceDAscendance(p);
    }
    
    //3.6
    void afficheAscendantUn(Personne p){
        if(!this.sommet.possedeCommeAscendants(p) || this.sommet==p){
            System.out.println("C'est pas un ascendant :) peace peace.");
        }
        else if(this.sommet==null){
            System.out.println("L'arbre est vide.");
        }
        else{
            System.out.print(this.sommet.getPrenom()+" "+this.sommet.getNom());
            this.sommet.afficheAscendantUn(p);
            System.out.println();
        }
    }
    
    //3.7
    int nbDeGenerations(){
        if(this.sommet==null){
            return 0;
        }
        return 1+this.sommet.nbDeGenerations();
    }
    
    //4.7
    boolean Verification(){
        if(this.sommet==null){
            return true;
        }
        return this.sommet.Verification();
    }
    
    //5.8
    ListePersonne getTousLesAscendants(){
        ListePersonne res=new ListePersonne();
        if(this.sommet==null){
            return res;
        }
        this.sommet.getTousLesAscendants(res);
        return res;
    }
    
    //5.9
    boolean estDeMaFamille(Personne p){
        if(this.nbDeGenerations()<2){
            return false;
        }
        if(this.sommet.estCousinGermain(p) 
        || this.sommet.estFrereOuSoeur(p) || this.sommet.possedeCommeAscendants(p)){
            return true;
        }
        ListePersonne tmp1=this.getTousLesAscendants();
        Famille fam2=new Famille(p);
        ListePersonne l2=fam2.getTousLesAscendants();
        return tmp1.similaire(l2);
    }
    
    //5.10
    int distanceDHeritage(Personne p){
        if(this.estDeMaFamille(p)){
            if(this.sommet.possedeCommeAscendants(p)){  //si p est un ascendant direct
                return this.distanceDAscendance(p);
            }
            if(p.possedeCommeAscendants(this.sommet)){  //p est plus petit
                return p.distanceDAscendance(this.sommet);
            }
            return this.sommet.distanceDAscendance(this.ascendantCommun(p))
                   +p.distanceDAscendance(this.ascendantCommun(p));
            //même génération ou ascendant indirect
        }
        return -1;
    }
    
    Personne ascendantCommun(Personne p){
        return this.sommet.ascendantCommun(p);
    }
}