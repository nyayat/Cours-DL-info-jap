public class Personne {
    private final String prenom, nomDeFamille;
    private Personne mere, pere;
    
    Personne(String prenom, String nomDeFamille, Personne pere, Personne mere){
        this.nomDeFamille=nomDeFamille;
        this.prenom=prenom;
        this.mere=mere;
        this.pere=pere;
    }
    
    Personne(String prenom, String nomDeFamille){
        this(prenom, nomDeFamille, null, null);
    }
    
    //1.1
    boolean estFrereOuSoeur(Personne p){  //demi-frère ou demie-soeur comptent aussi
        boolean res=false;
        if(this.mere!=null && p.mere!=null){
            res=res || (this.mere==p.mere);
        }
        if(this.pere!=null && p.pere!=null){
            res=res || (this.pere==p.pere);
        }
        return res;
    }
    
    //1.2
    boolean estCousinGermain(Personne p){
        boolean res=false;
        if(this.mere!=null && p.mere!=null){
            res=res || (p.mere.estFrereOuSoeur(this.mere));
        }
        if(this.mere!=null && p.pere!=null){
            res=res || (p.pere.estFrereOuSoeur(this.mere));
        }
        if(this.pere!=null && p.mere!=null){
            res=res || (p.mere.estFrereOuSoeur(this.pere));
        }
        if(this.pere!=null && p.pere!=null){
            res=res || (p.pere.estFrereOuSoeur(this.pere));
        }
        return res;
    }
    
    //2.3
    int nbAscendantsVivants(){
        if(this.mere!=null && this.pere!=null){
            return 2+this.mere.nbAscendantsVivants()+this.pere.nbAscendantsVivants();
        }
        else if(this.mere!=null){
            return 1+this.mere.nbAscendantsVivants();
        }
        else if(this.pere!=null){
            return 1+this.pere.nbAscendantsVivants();
        }
        return 0;
    }
    
    //2.4
    boolean possedeCommeAscendants(Personne p){
        if(this.mere!=null && this.pere!=null){
            return (this.pere==p || this.mere==p
                 || this.mere.possedeCommeAscendants(p) || this.pere.possedeCommeAscendants(p));
        }
        else if(this.mere!=null){
            return (this.mere==p || this.mere.possedeCommeAscendants(p));
        }
        else if(this.pere!=null){
            return (this.pere==p || this.pere.possedeCommeAscendants(p));
        }
        return false;
    }
    
    //3.5
    int distanceDAscendance(Personne p){
        if(this.mere!=null && this.pere!=null){
            if(this.mere==p || this.pere==p){
                return 1;
            }
            return min(this.mere.distanceDAscendance(p)+1, this.pere.distanceDAscendance(p)+1);
        }
        else if(this.mere!=null){
            if(this.mere==p){
                return 1;
            }
            return 1+this.mere.distanceDAscendance(p);
        }
        else if(this.pere!=null){
            if(this.pere==p){
                return 1;
            }
            return 1+this.pere.distanceDAscendance(p);
        }
        return 0;
    }
    
    int min(int a, int b){
        if(a<b){
            return a;
        }
        return b;
    }
    
    //3.6
    void afficheAscendantUn(Personne p){
        if(this.mere!=null){
            if(this.mere.possedeCommeAscendants(p) || this.mere==p){
                System.out.print(", enfant de "+this.mere.prenom+" "+this.mere.nomDeFamille);
                if(this.mere!=p){
                    this.mere.afficheAscendantUn(p);
                }
            }
        }
        if(this.pere!=null){
            if(this.pere.possedeCommeAscendants(p) || this.pere==p){
                System.out.print(", enfant de "+this.pere.prenom+" "+this.pere.nomDeFamille);
                if(this.pere!=p){
                    this.pere.afficheAscendantUn(p);
                }
            }
        }
    }
    
    String getPrenom(){
        return this.prenom;
    }
    
    String getNom(){
        return this.nomDeFamille;
    }
    
    //3.7
    int nbDeGenerations(){
        if(this.mere!=null && this.pere!=null){
            return max(1+this.pere.nbDeGenerations(), 1+this.mere.nbDeGenerations());
        }
        else if(this.pere!=null){
            return 1+this.pere.nbDeGenerations();
        }
        else if(this.mere!=null){
            return 1+this.mere.nbDeGenerations();
        }
        return 0;
    }
    
    int max(int a, int b){
        if(a<b){
            return b;
        }
        return a;
    }
    
    //4.7
    boolean Verification(){
        if(this.mere!=null && this.pere!=null){
            if(this.nomDeFamille.equals(this.pere.nomDeFamille) 
            && !this.mere.estCousinGermain(this.pere)
            && !this.mere.estFrereOuSoeur(this.pere)){
                return (true && this.mere.Verification() && this.pere.Verification());
            }
            return false;
        }
        else if(this.mere!=null){
            if(!this.nomDeFamille.equals(this.mere.nomDeFamille)){  //mère et père pas frère et soeur
                return (true && this.mere.Verification());
            }
            return false;
        }
        else if(this.pere!=null){
            if(this.pere.nomDeFamille.equals(this.nomDeFamille)){
                return (true && this.pere.Verification());
            }
            return false;
        }
        return true;  //par défaut, on suppose que la plus vieille génération respecte cette règle
    }
    
    //5.8
    void getTousLesAscendants(ListePersonne res){
        if(this.mere!=null){
            res.ajoutListe(this.mere);
            this.mere.getTousLesAscendants(res);
        }
        if(this.pere!=null){
            res.ajoutListe(this.pere);
            this.pere.getTousLesAscendants(res);
        }
    }
    
    //5.10
    Personne ascendantCommun(Personne p){
        if(this.mere!=null){
            if(p.possedeCommeAscendants(this.mere)){
                return this.mere;
            }
            return this.mere.ascendantCommun(p);
        }
        if(this.pere!=null){
            if(p.possedeCommeAscendants(this.pere)){
                return this.pere;
            }
            return this.pere.ascendantCommun(p);
        }
        return null;  //on n'arrive normalement jamais à cette étape
    }
}