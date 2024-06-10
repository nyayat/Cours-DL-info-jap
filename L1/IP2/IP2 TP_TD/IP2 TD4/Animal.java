public class Animal {
    
    //1.1
    private String regime;  //herbivore ou carnivore
    private Animal suivant;
    
    Animal(String r){
        regime=r;
        suivant=null;
    }
    
    //1.2
    Animal(String r, Animal a){
        regime=r;
        suivant=a;
    }
    
    //1.4
    int count(){
        Animal tmp=this;
        int res=0;
        while(tmp!=null){
            res++;
            tmp=tmp.suivant;
        }
        return res;
    }
    
    //1.5
    void changeR(){
        this.regime=(this.regime.equals("herbivore"))? "carniore" : "herbivore";
    }
    
    void changeTout(){
        Animal tmp=this;
        while(tmp!=null){
            tmp.changeR();
            tmp=tmp.suivant;
        }
    }
    
    //1.6
    void affiche(){
        Animal tmp=this;
        while(tmp!=null){
            System.out.println(tmp.regime);
            tmp=tmp.suivant;
        }
    }
    
    //1.7
    int countH(){  //pour compter les herbivores
        Animal tmp=this;
        int res=0;
        while(tmp!=null){
            if(tmp.regime.equals("herbivore")){
                res++;
            }
            tmp=tmp.suivant;
        }
        return res;
    }
    
    void afficheBis(){
        System.out.println("<Herbivore(s)>"+this.countH());
        System.out.println("<Carnivore(s)>"+(this.count()-this.countH()));
    }
    
    //2.3
    Animal(){
        this(null);
    }
    
    void ajoutFin(String r){  //ajoute à la fin
        Animal tmp=this;
        while(tmp.suivant!=null){
            tmp=tmp.suivant;
        }
        tmp.suivant=new Animal(r);
    }
    
    Animal nettoyage(){  //enlève les herbivores à côté des carnivores d'abord       
        Animal res=new Animal("herbivore");
        Animal tmp=this;
        if(tmp!=null && (this.countH()!=this.count() || this.countH()!=0)){
        //s'il n'y a pas que des herbivores mais au moins un
            while(tmp!=null && tmp.suivant!=null){
                if(tmp.regime.equals("carnivore") && tmp.suivant.regime.equals("herbivore")){
                //on rajoute le carnivore et saute l'herbivore
                    res.ajoutFin("carnivore");
                    tmp=tmp.suivant;
                }
                else if(!tmp.suivant.regime.equals("carnivore") && tmp.regime.equals("herbivore")){
                    res.ajoutFin(tmp.regime);
                }  
                if(tmp!=null){
                    tmp=tmp.suivant;
                }
            }
            if(tmp!=null){
                res.ajoutFin(tmp.regime);
            }
            return res.suivant;
        }
        return this;
    }
    
    //2.2
    Animal reprodcution(){  //fait reproduire les herbivores côte à côte encore dans la liste
        Animal res=new Animal("herbivore");  //en trop
        Animal tmp=this;
        if(tmp!=null && this.countH()>1){ 
            while(tmp!=null && tmp.suivant!=null){
                if(tmp.regime.equals("herbivore") && tmp.suivant.regime.equals("herbivore")){
                    res.ajoutFin("herbivore");
                    res.ajoutFin("herbivore");
                    res.ajoutFin("herbivore");
                    tmp=tmp.suivant;
                }
                else{
                    res.ajoutFin(tmp.regime);
                }
                tmp=tmp.suivant;  //nouvel animal pas encore testé
            }
            if(tmp!=null){
                res.ajoutFin(tmp.regime);
            }
            return res.suivant;
        }
        return this;
    }
}