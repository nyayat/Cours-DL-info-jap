public class Zoo {
    //3.
    String ville;
    Enclos[]contenu;
    
    public Zoo(String nom, int nbEn, int tailleEn){
        ville=nom;
        contenu=new Enclos[nbEn];
        for(int i=0; i<nbEn; i++){
            this.contenu[i].e=new Animal[tailleEn];
        }
    }
    
    //4.
    public boolean ajoutZoo(Animal a){
        for(Enclos e : this.contenu){
            if(e.ajout(a)){
                return true;
            }
        }
        return false;
    }
    
    //5.
    public void nourrirZoo(){
        /*for(int i=0; i<this.contenu.length; i++){
            for(int j=0; j<this.contenu[i].e.length; j++){
                if(this.contenu[i].e[j].faim>5){
                    this.contenu[i].e[j].faim--;
                }
            }
        }*/
        for(Enclos e : this.contenu){
            for(Animal a : e.e){
                if(a.faim>5){
                    a.nourrir();
                }
            }
        }
    }
    
    //6.
    public void unJourPasseAuZoo(){
        /*for(int i=0; i<this.contenu.length; i++){
            for(int j=0; j<this.contenu[i].e.length; j++){
                this.contenu[i].e[j].age+=1;
                this.contenu[i].e[j].faim+=2;
                if(this.contenu[i].e[j].faim>=10){
                    this.contenu[i].e[j]=null;
                }
            }
        }*/
        for(Enclos e : this.contenu){
            for(Animal a : e.e){
                if(a.faim>=8){
                    a=null;
                }
                else{
                    a.faim+=2;
                    a.age++;
                }
            }
        }
    }
    
    //7.
    public void unJourPasseAuZooBis(){
        for(Enclos e : this.contenu){
            for(int i=0; i<e.e.length; i++){
                int indAj=-1;
                e.e[i].age+=1;  //Ajout age
                e.e[i].faim+=2;  //Ajout faim
                if(e.e[i].faim>=10){  //nettoie les cages :)
                    e.e[i]=null;
                }          
                int j=0;
                while(j<e.e.length && indAj==-1){  //repère emplacement vide
                    if(e.e[j]==null){  
                        indAj=j;
                    }
                    j++;
                }
                if(e.e[i].sexe=='f' && e.e[i].age>0 && indAj!=-1){  //rencontre une femelle "adulte" et un emplacement vide
                    boolean djRep=false;
                    int k=i+1;
                    while(k<e.e.length && !djRep){  //teste toutes les possibilités de reproduction
                        e.e[indAj]=e.e[i].reproduction(e.e[i],e.e[k]);
                        if(e.e[indAj]!=null){
                            djRep=true;
                        }
                        k++;
                    }
                }
            }
        }
    }
}