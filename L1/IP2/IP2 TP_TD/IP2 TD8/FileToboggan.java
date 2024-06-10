import java.util.Random;
public class FileToboggan {
    //2.1
    Enfant courant;
    int nbMax;
    
    //2.2
    FileToboggan(int max){
        this.nbMax=max;
        this.courant=null;
    }
    
    //2.3
    void ajouter(String nom, int nb){
        Enfant tmp=new Enfant(nom, nb);
        if(this.courant==null){
            this.courant=new Enfant(tmp);
            //this.courant.precedent=this.courant;
            //this.courant.suivant=this.courant;
            return;
        }
        if(nb<nbMax){
            //this.courant.precedent=new Enfant(nom, nb, this.courant, this.courant.precedent);
            //this.courant.precedent.precedent.suivant=this.courant.precedent;
            tmp.suivant=this.courant;
            tmp.precedent=this.courant.precedent;
            this.courant.precedent=tmp;
            this.courant.precedent.suivant=tmp;
        }
    }
    
    //2.4
    void supprimer(String nom){
        if(this.courant==null){
            return;
        }
        int max=this.longueur();
        if(max==1){
            if(this.courant.nom.equals(nom)){
                this.courant=null;
            }
            return;
        }
        boolean fait=false;
        Enfant tmpC=this.courant;  //pour revenir au premier courant à la fin
        while(!fait && max!=0){
            if(this.courant.nom.equals(nom)){
                Enfant next=this.courant.suivant;
                this.courant.suivant.precedent=this.courant.precedent;
                this.courant.precedent.suivant=this.courant.suivant;
                fait=!fait;
                this.courant=next;
            }
            max--;
            this.courant=this.courant.suivant;
        }
        while(tmpC!=this.courant){  //pour ne pas modifier le courant
            this.courant=this.courant.suivant;
        }
    }
    
    int longueur(){
        if(this.courant==null){
            return 0;
        }
        String tmp=this.courant.nom;  //valeur initiale
        int res=1;
        this.courant=courant.suivant;
        while(!(this.courant.nom.equals(tmp))){
            res++;
            this.courant=this.courant.suivant;
        }
        return res;
    }
    
    //2.5
    void affiche(){
        if(this.courant==null){
            System.out.println("Il n'y a pas d'enfants à enlever.");
            return;
        }
        for(int i=0; i<this.longueur(); i++){
            System.out.println(this.courant.nom+" a glissé "+this.courant.nbTob+" fois.");
            this.courant=this.courant.suivant;
        }
    }
    
    //2.6
    Enfant unTour(){
        if(this.courant==null){
            return null;
        }
        System.out.println(this.courant.nom+" vient de glisser.");
        Enfant tmp=this.courant;
        this.courant=this.courant.suivant;
        if(tmp.unTour()>=nbMax){
            this.supprimer(tmp.nom);
            return tmp;
        }
        return null;
    }
    
    //2.7
    static Random rand=new Random();
    
    Enfant unTour_bis(){
        if(this.courant==null){
            return null;
        }
        System.out.println(this.courant.nom+" vient de glisser.");
        Enfant tmp=this.courant;
        int alea=rand.nextInt(2);
        System.out.println(alea); //pour vérifier
        this.courant=this.courant.suivant;
        if(alea==0 || tmp.unTour()>=nbMax){
            this.supprimer(tmp.nom);
            return tmp;
        }
        return null;
    }
    
    //3.3
    FileToboggan(){
        this(0);
    }
    
    //4.1
    void laisserPasser(){
        if(this.longueur()>1 && this.courant.suivant.nbTob<this.courant.nbTob){
            String tmpNom=this.courant.nom;
            int tmpNB=this.courant.nbTob;
            this.courant.nom=this.courant.suivant.nom;
            this.courant.nbTob=this.courant.suivant.nbTob;
            this.courant.suivant.nom=tmpNom;
            this.courant.suivant.nbTob=tmpNB;
        }
    }
    
    //4.2
    Enfant unTourPre(){
        this.laisserPasser();
        return this.unTour_bis();
    }
    
    //4.3
    Enfant unTourPre_bis(){
        if(this.longueur()>1 && this.courant.suivant.nbTob==this.courant.nbTob){
            int alea=rand.nextInt(3);
            System.out.println(alea);
            if(alea==0){  //courant glisse
                return this.unTour_bis();
            }
            if(alea==1){  //laisse passer
                String tmpNom=this.courant.nom;
                int tmpNB=this.courant.nbTob;
                this.courant.nom=this.courant.suivant.nom;
                this.courant.nbTob=this.courant.suivant.nbTob;
                this.courant.suivant.nom=tmpNom;
                this.courant.suivant.nbTob=tmpNB;
                return this.unTour_bis();
            }
            this.courant.unTour();
            System.out.println(this.courant.nom+" vient de glisser.");
            Enfant tmp=this.courant;
            this.courant=this.courant.suivant;
            this.supprimer(tmp.nom);
            return tmp;
        }
        return null;
    }
}