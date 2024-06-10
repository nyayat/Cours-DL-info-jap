public class Automate {
    //1.2
    Cellule debut;
    Cellule fin;
    
    Automate(){
        this.debut=null;
        this.fin=null;
    }
    
    void ajout(boolean b){  //par défaut, on ajoute à la fin si debut et fin sont non-null
        Cellule tmp=new Cellule(b);
        if(this.debut==null){  //dans ce cas, fin==null aussi
            this.debut=tmp;
            this.fin=tmp;
            return;
        }
        this.fin.setS(tmp);
        this.fin=tmp;
    }
    
    //1.3
    void affichage(){
        if(this.debut!=null){
            this.debut.affichage();
            this.fin.afficher();
            System.out.println();
        }
        else{
            System.out.println("Cette liste est vide.");
        }
    }
    
    //2.1
    void initialisation(){
        this.ajout(true);
        this.ajout(true);
        this.ajout(true);
        this.ajout(false);
        this.ajout(false);
        this.ajout(false);
        this.ajout(true);
        this.ajout(false);
        this.ajout(false);
        this.ajout(false);
        this.ajout(false);
    }
    
    //2.3
    void prochaineEtape(){
        if(this.debut!=null){
            this.debut.setProchaineE(!(false==this.debut.getN() && false==this.debut.getS().getN()));
            this.debut.getS().prochaineEtape();
        }
    }
    
    //2.4
    void miseAJour(){
        if(this.debut!=null){
            this.debut.miseAJour();
        }
    }
    
    //2.5
    void uneEtape(){
        this.prochaineEtape();
        this.miseAJour();
    }
    
    //2.6
    void nbEtapes(int n){
        this.affichage();
        for(int i=0; i<n; i++){
            this.uneEtape();
            this.affichage();
        }
    }
    
    //2.8
    Automate(String str){
        this();
        for(int i=0; i<str.length(); i++){
            switch(str.charAt(i)){
                case '#' : this.ajout(true); break;
                case '-' : this.ajout(false); break;
            }
        }
    }
    
    //2.10
    void prochaineEtapeBis(){  //avec la règle de la majorité
        if(this.debut!=null){
            if(this.debut.nbNoire(false, this.debut.getN(), this.debut.getS().getN())>1){
                this.debut.setProchaineE(true);
            }
            else{
                this.debut.setProchaineE(false);
            }
            this.debut.getS().prochaineEtapeBis();
        }
    }
    
    void uneEtapeBis(){  //avec la règle de la majorité
        this.prochaineEtapeBis();
        this.miseAJour();
    }
    
    void nbEtapesBis(int n){  //avec la règle de la majorité
        this.affichage();
        for(int i=0; i<n; i++){
            this.uneEtapeBis();
            this.affichage();
        }
    }
    
    void nbEtapesAvecChoixRegle(String loi, int n){  //choix de règle
        switch(loi){
            case "unanimite" : this.nbEtapes(n); break;
            case "majorite" : this.nbEtapesBis(n); break;
        }
    }
}