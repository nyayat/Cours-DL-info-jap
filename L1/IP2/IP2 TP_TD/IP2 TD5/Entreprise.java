public class Entreprise {
    //1.3
    private String nom;
    private Cellule premier;
    
    Entreprise(String nom){
        this(nom, null);
    }
    
    Entreprise(String nom, Cellule head){
        this.nom=nom;
        premier=head;
    }
    
    void affiche(){
        if(premier==null){
            System.out.println("Cette entreprise est vide :) .");
        }
        else{
            this.premier.affiche();
        }
    }
    
    boolean appartient(String n){
        if(this.premier==null){
            return false;
        }
        return this.premier.appartient(n);
    }
    
    void ajout(Employe emp){
        if(this.premier==null){
            this.premier=new Cellule(emp);
        }
        else{
            this.premier=new Cellule(emp, this.premier);
        }
    }
    
    void demission(String n){
        if(this.premier.getEmp().getNom().equals(n)){
            this.premier=this.premier.getSuivante();
        }
        else if(this.premier!=null){
            this.premier.demission(n);
        }
    }
    
    
    //2.1
    boolean augmente(String nom, int montant){
        if(this.appartient(nom)){
            return this.premier.augmente(nom, montant);
        }
        return false;
    }
    
    //2.2
    Entreprise choixSalaire(int min, int max){
        Entreprise res=new Entreprise(this.nom);
        if(this.premier==null){
            return res;
        }
        this.premier.choixSalaire(min, max, res);
        return res;
    }
    
    //3.1
    boolean croissante(){
        if(this.premier==null){
            return true;
        }
        return this.premier.croissante();
    }
    
    //3.2
    void ajoutTrie(Employe emp){
        if(this.premier==null){
            this.premier=new Cellule(emp);
        }
        else if(emp.getSalaire()<this.premier.getEmp().getSalaire()){
            this.premier=new Cellule(emp, this.premier);
        }
        else{
            this.premier.ajoutTrie(emp);
        }
    }
    
    //3.3
    void acquisition_Version_1(Entreprise ent){
        if(ent.premier!=null){
            ent.premier.acquisition_Version_1(this);
        }
    }
    
    //3.4
    void augmente_Version_1(String nom, int montant){
        if(this.appartient(nom)){
            Employe res=new Employe(nom, this.premier.salaireDe(nom)+montant);
            this.demission(nom);
            /*on peut le licencier plus tard 
              car l'ancien a un salaire plus petit que le nouveau,
              donc le nouveau sera placé après dans la liste,
              et en licenciant "nom", on s'arrêtera au premier qu'on trouve.*/
            this.ajoutTrie(res);
        }
    }
    
    //3.5
    void augmente_Version_2(String nom, int montant){
        if(this.appartient(nom)){
            this.premier.augmente_Version_2(nom, montant);
            this.augmente(nom, montant);
        }
    }
    
    //3.6
    void acquisition_Version_2(Entreprise ent){
        if(ent.premier!=null){
            if(ent.premier.getEmp().getSalaire()<this.premier.getEmp().getSalaire()){
                this.premier=new Cellule(ent.premier.getEmp(),this.premier);
                ent.premier.getSuivante().acquisition_Version_2(this.premier);
            }
            else{
                ent.premier.acquisition_Version_2(this.premier);
            }
        }
    }

}