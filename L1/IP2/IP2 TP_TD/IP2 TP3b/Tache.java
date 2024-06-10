public class Tache {
    //2.
    final String nom;
    private Tache[]necessairePour; 
    //6.
    final int numero;
    static int dernierNum=0;
    //8.
    int nbDependances;
    //12.
    Agent agent;
    
    //3.
    Tache(String n){
        nom=n;
        necessairePour=new Tache[0];
        //6.
        numero=++dernierNum;
        nbDependances=0;
    }
    
    //12.
    Tache(String n, Agent a){
        nom=n;
        necessairePour=new Tache[0];
        numero=++dernierNum;
        nbDependances=0;
        a.ajouteTache(this);
    }
    
    //4.
    void declareNecessairePour(Tache t){
        Tache[]tmp=new Tache[this.necessairePour.length+1];
        for(int i=0; i<this.necessairePour.length; i++){
            tmp[i]=this.necessairePour[i];
        }
        tmp[this.necessairePour.length]=t;
        this.necessairePour=tmp;
        //8.
        t.nbDependances++;
    }
    
    //7.
    void afficher(){
        System.out.print("Tâche " + this.numero + " : " + this.nom + ". ");
        if(this.necessairePour.length!=0){
            System.out.print("Est nécessaire avant la (ou les) tâche(s) ");
            for(int i=0; i<this.necessairePour.length-1; i++){
                System.out.print(this.necessairePour[i].numero + ", ");
            }
            System.out.print(this.necessairePour[this.necessairePour.length-1].numero + ".");
        }
        //8.
        System.out.println(" Dépend de " + this.nbDependances + " tâche(s).");
    }
    
    //9.
    boolean estExecutable(){
        return (this.nbDependances==0);
    }
    
   //13.
    void enleveNecessairePourBis(){
        for(int i=0; i<this.agent.tachesExecutables.length; i++){
            if(this.agent.tachesExecutables[i].nbDependances!=0){
                this.agent.enleveTache(this.agent.tachesExecutables[i]);
            }
        }
        //ne compile pas zmeihqgm
    }
    
    //14.
    void faire(){
        if(this.estExecutable()){
            System.out.println("Agent " + this.agent.nom + " : " + this.nom);
            this.agent.enleveTache(this);
            for(int i=0; i<this.necessairePour.length; i++){
                this.necessairePour[i].nbDependances--;  //car on a accomplit une tâche
                if(this.necessairePour[i].estExecutable()){
                    this.agent.ajouteTache(this.necessairePour[i]);
                }
            }
        }
    }
}