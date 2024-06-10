//Exercice 2
public class Cellule {
    private Robot rob;
    private Cellule suivant;
        
    //2.2
    Cellule(Robot r, Cellule c){
        this.rob=r;
        this.suivant=c;
    }
    
    //2.3
    Cellule(Robot r){
        this(r,null);
    }
    
    //3.1
    Robot getRob(){
        return this.rob;
    }
    
    Cellule getSuivant(){
        return this.suivant;
    }
    
    //3.2
    void ajout(Robot r){  //il y a toujours un premier élément non null
        Cellule tmp=this;
        while(tmp.suivant!=null){
            tmp=tmp.suivant;
        }
        tmp.suivant=new Cellule(r);
    }
    
    //4.1
    Cellule couperAPartirDe(char nom){
        Cellule tmp=this;
        while(tmp.rob.getNom()!=nom && tmp.suivant!=null){
        //on vérifie si le nom est celui qu'on cherche ou non
        //puis on regarde si la cellule suivante est vide ou non
            tmp=tmp.suivant;
        }
        Cellule res=tmp.suivant;  //cellule qui contient tous les membres supprimés
        tmp.suivant=null;  //on "supprime" les membres après le nom recherché
        return res;
    }
    
    /*On peut aussi responsabiliser les cellules : 
    
        private Robot rob;
        private Cellule suivant;

        //2.2
        public Cellule(Robot rob, Cellule cell){
            this.rob=rob;
            suivant=cell;
        }

        //2.3
        public Cellule(Robot rob){
            this(rob, null);
        }

        //4.2
        private Cellule() {
            this(null);
        }

        //3.1
        void affiche() {
            Cellule tmp=this;
            while(tmp!=null) {
                System.out.println(tmp.rob.description());
                tmp=tmp.suivant;
            }
        }

        //3.2
        void ajouteNouveau(Robot n) {
            Cellule tmp=this;
            while(tmp.suivant!=null) {
                tmp=tmp.suivant;
            }
            tmp.suivant=new Cellule(n);
        }

        //3.3
        int numerologie() {
            Cellule tmp=this;
            int res=0;
            while(tmp!=null){
                res+=(int)tmp.rob.getNom()-96;
                tmp=tmp.suivant;
            }
            res%=9;
            return res;
        }

        //3.4
        String bandName() {
            String res="";
            Cellule tmp=this;
            while(tmp!=null) {
                res+=tmp.rob.getNom();
                tmp=tmp.suivant;
            }
            return res;
        }

        //3.6
        void chantez() {
            Cellule tmp=this;
            while(tmp!=null) {
                tmp.rob.chante();
                tmp=tmp.suivant;
            }
        }

        //4.1
        Cellule couperAPartirDe(char nom) {
            Cellule tmp=this;
            while(tmp!=null) {
                if(tmp.rob.getNom()==nom) {
                    Cellule res=tmp.suivant;
                    tmp.suivant=null;
                    return res;
                }
                tmp=tmp.suivant;
            }
            return null;
        }

        //4.2
        Cellule enPause(){
            Cellule tmp=this;
            Cellule enPause=new Cellule();
            while(tmp!=null) {
                if(tmp.rob.getEnergie()==0){
                    enPause.ajouteNouveau(tmp.rob);
                }
                tmp=tmp.suivant;
            }           
            return enPause;
        }	
    
        Cellule enForme(){
            Cellule tmp=this;
            Cellule enForme=new Cellule();
            while(tmp!=null) {
                if(tmp.rob.getEnergie()>0) {
                    enForme.ajouteNouveau(tmp.rob);
                }
                tmp=tmp.suivant;
            }
            return enForme;
        }	
    
    Attention : on fera les modifications nécessaires dans la classe Groupe.
    */
}