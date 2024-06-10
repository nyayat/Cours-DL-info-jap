public class Agent {
    //10.
    String nom;
    Tache[]tachesExecutables;
    
    Agent(String n){
        nom=n;
        tachesExecutables=new Tache[0];
    }
    
    //11.
    boolean empty(){
        return (this.tachesExecutables.length==0);
    }    
    
    //12.
    void ajouteTache(Tache t){
        Tache[]tmp=new Tache[this.tachesExecutables.length+1];
        for(int i=0; i<this.tachesExecutables.length; i++){
            tmp[i]=this.tachesExecutables[i];
        }
        tmp[this.tachesExecutables.length]=t;
        this.tachesExecutables=tmp;
    }
    
    //13.
    void enleveTache(Tache t){
        Tache[]tmp=new Tache[this.tachesExecutables.length-1];
        int c=0;
        for(int i=0; i<this.tachesExecutables.length; i++){
            if(this.tachesExecutables[i]!=t){
                tmp[c]=this.tachesExecutables[i];
                c++;
            }
        }
        this.tachesExecutables=tmp;
    }
    
    //15.
    Tache donneTacheExecutable(){
        Tache res=null;
        if(!this.empty()){
            res=this.tachesExecutables[0];
        }
        return res;
    }
    
    //16.
    void executerTout(){
        while(this.donneTacheExecutable()!=null){
            this.donneTacheExecutable().faire();
        }
    }
}