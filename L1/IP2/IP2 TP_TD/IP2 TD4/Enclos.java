public class Enclos {
    
    //1.1
    private Animal debut;
    
    Enclos(){
        debut=null;
    }
    
    //1.2
    void ajouter(String r){
        if(this.debut==null){
            this.debut=new Animal(r);
        }
        else{
            this.debut=new Animal(r,this.debut);
        }
    }
    
    //1.4
    int count(){
        return this.debut.count();
    }
    
    //1.5
    void changeTout(){
        this.debut.changeTout();
    }
    
    //1.6
    void affiche(){
        this.debut.affiche();
    }
    
    //1.7
    void afficheBis(){
        this.debut.afficheBis();
    }
    
    //2.4
    void jouer (int n){
        for(int i=0; i<n; i++){
            this.debut=this.debut.nettoyage();
            this.debut=this.debut.reprodcution();
        }
        this.debut.affiche();
    }
}