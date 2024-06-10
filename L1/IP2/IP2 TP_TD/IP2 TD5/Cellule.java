public class Cellule {
    //1.2
    private Employe emp;
    private Cellule suivante;;
    
    Cellule(Employe emp){
        this(emp, null);
    }
    
    Cellule(Employe emp, Cellule suiv){
        this.emp=emp;
        suivante=suiv;
    }
    
    void affiche(){
        this.emp.affichage();
        if(this.suivante!=null){
            this.suivante.affiche();
        }
    }
    
    boolean appartient(String n){
        if(this.emp.getNom().equals(n)){
            return true;
        }
        if(this.suivante!=null){
            return this.suivante.appartient(n);
        }
        return false;
    }
    
    Employe getEmp(){
        return this.emp;
    }
    
    Cellule getSuivante(){
        return this.suivante;
    }
    
    void demission(String n){
        if(this.suivante!=null){
            if(this.suivante.emp.getNom().equals(n)){
                this.suivante=this.suivante.suivante;
            }
            else{
                this.suivante.demission(n);
            }
        }
    }
    
    //2.1
    boolean augmente(String nom, int montant){
        if(this.emp.getNom().equals(nom)){
            this.emp.setSalaire(this.emp.getSalaire()+montant);
            return true;
        }
        if(this.suivante!=null){
            return this.suivante.augmente(nom, montant);
        }
        return false;
    }
    
    //2.2
    void choixSalaire(int min, int max, Entreprise e){
        if(this.emp.getSalaire()<=max && this.emp.getSalaire()>=min){
            e.ajout(this.emp);
        }
        if(this.suivante!=null){
            this.suivante.choixSalaire(min, max, e);
        }
    }
    
    //3.1
    boolean croissante(){
        if(this.suivante!=null){
            if(this.emp.getSalaire()>this.suivante.emp.getSalaire()){
                return false;
            }
            return this.suivante.croissante();
        }
        return true;
    }
    
    //3.2
    boolean ajoutTrie(Employe emp){
        if(this.suivante!=null){
            if(emp.getSalaire()<=this.suivante.emp.getSalaire()){
                this.suivante=new Cellule(emp, this.suivante);
                return true;
            }
            this.suivante.ajoutTrie(emp);
            return true;
        }
        this.suivante=new Cellule(emp);
        return true;
    }
    
    //3.3
    void acquisition_Version_1(Entreprise courant){  //this=ent dans Entreprise (on inverse les deux)
        if(this.suivante!=null){
            courant.ajoutTrie(this.getEmp());
            this.suivante.acquisition_Version_1(courant);
        }
        else{
            courant.ajoutTrie(this.getEmp());
        }
    }
    
    //3.4
    int salaireDe(String n){  //on présume qu'il sera toujours dans l'entreprise
        if(this.emp.getNom().equals(n)){
            return this.emp.getSalaire();
        }
        return this.suivante.salaireDe(n);
    }
        
    //3.5
    void pushIt(){
        Employe tmp=this.emp;
        this.emp=this.suivante.emp;
        this.suivante=new Cellule(tmp, this.suivante.suivante);
    }
    
    void augmente_Version_2(String nom, int montant){
        if(this.emp.getNom().equals(nom)){  //this est l'employé qu'on cherche
            if(this.suivante!=null && this.suivante.emp.getSalaire()<this.emp.getSalaire()+montant){
                this.pushIt();
                this.suivante.augmente_Version_2(nom, montant);
            }
            //si le suivant est null, l'employé recherché est à la dernière position
        }
        else{
            this.suivante.augmente_Version_2(nom, montant);
        }
    }
    
    //3.6
    void acquisition_Version_2(Cellule courante){
    //on rajoutera après la cellule courante (de this dans Entreprise) à chaque fois
        if(this.suivante!=null && courante.suivante!=null){
            if(this.emp.getSalaire()<=courante.suivante.emp.getSalaire()){
                courante.suivante=new Cellule(this.emp, courante.suivante);
                this.suivante.acquisition_Version_2(courante.suivante);
            }
            else{
                this.acquisition_Version_2(courante.suivante);
            }
        }
        else if(this.suivante!=null){  //on a finit de parcourir la liste courante
            courante.suivante=new Cellule(this.emp);
            this.suivante.acquisition_Version_2(courante.suivante);
        }
        else if(courante.suivante!=null){  //on a finit de parcourir la liste à rajouter
            if(this.emp.getSalaire()<=courante.suivante.emp.getSalaire()){
                courante.suivante=new Cellule(this.emp, courante.suivante);
            }
            else{
                this.acquisition_Version_2(courante.suivante);
            }
        }
        else{  //on arrive au bout des deux listes
            courante.suivante=new Cellule(this.emp);
        }
    }
}