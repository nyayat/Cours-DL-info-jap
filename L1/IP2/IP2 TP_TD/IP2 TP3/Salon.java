public class Salon {
    //1.
    Utilisateur[]participants;
    int nbreParticipants;
    Message[]boiteMessages;
    int numDernierMessage;
    
    Salon(){
        nbreParticipants=0;
        numDernierMessage=0;
    }
    
    //2.
    public void ajouterUtilisateur(Utilisateur u){
        Utilisateur[] nouveauParticipants=new Utilisateur[nbreParticipants+1];
        for(int i=0; i<nbreParticipants; i++){
            nouveauParticipants[i]=this.participants[i];
        }
        nouveauParticipants[nbreParticipants]=u;
        this.participants=nouveauParticipants;
        nbreParticipants++;
    }
    
    //3.
    public boolean estPresent(Utilisateur u){
        boolean estPresent=false;
        int i=0; 
        while(i<nbreParticipants && !estPresent){
            estPresent=(participants[i]==u);
            i++;
        }
        return estPresent;
    }
    
    //4.
    public void ajouterMessage(Message m){
        if(estPresent(m.u)){
            Message[] nouvelleBoiteMessages=new Message[numDernierMessage+1];
            for(int i=0; i<numDernierMessage; i++){
                nouvelleBoiteMessages[i]=this.boiteMessages[i];
            }
            nouvelleBoiteMessages[numDernierMessage]=m;
            this.boiteMessages=nouvelleBoiteMessages;
            numDernierMessage++;
        }
    }
    
    //5.
    public void afficher(){
        for(int i=0; i<numDernierMessage; i++){
            System.out.println(this.boiteMessages[i].u.getPseudonyme() + " : " + this.boiteMessages[i].message);
        }
    }
    
    //7.
    public void exclusUtilisateur(Utilisateur u){
        if(estPresent(u)){
            //on enlève d'abord le participant de la liste de participants
            int c=0;
            Utilisateur[] nouveauParticipants=new Utilisateur[nbreParticipants-1];
            for(int i=0; i<nbreParticipants; i++){
                if(this.participants[i]!=u){
                    nouveauParticipants[c]=this.participants[i];
                    c++;
                }
            }
            this.participants=nouveauParticipants;
            nbreParticipants--;
            
            //on enlève ensuite tous ses messages
            int num=numDernierMessage;
            c=0;
            for(int i=0; i<num; i++){
                if(this.boiteMessages[i].u==u){
                    c++;
                }
            }
            this.numDernierMessage-=c;
            Message[] nouvelleBoiteMessages=new Message[num-c];
            c=0;
            for(int i=0; i<num; i++){
                if(this.boiteMessages[i].u!=u){
                    nouvelleBoiteMessages[c]=this.boiteMessages[i];
                }
            }
            this.boiteMessages=nouvelleBoiteMessages;
        }
    }
    
    //Exercice 4.3 : on considère que l'utilisateur est dans la liste des participants
    public int nombreMessagesSalon(Utilisateur u){
        int res=0;
        for(int i=0; i<numDernierMessage; i++){
            if(this.boiteMessages[i].u==u){
                res++;
            }
        }
        return res;
    }
}