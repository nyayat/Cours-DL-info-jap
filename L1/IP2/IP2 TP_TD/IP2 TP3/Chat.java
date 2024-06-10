public class Chat {
    //1.
    Salon[]salons;
    int nbreSalons;
    
    Chat(){
        nbreSalons=0;
    }
    
    public void ajouterSalon(Salon s){
        Salon[] nouveauSalons=new Salon[nbreSalons+1];
        for(int i=0; i<nbreSalons; i++){
            nouveauSalons[i]=this.salons[i];
        }
        nouveauSalons[nbreSalons]=s;
        this.salons=nouveauSalons;
        nbreSalons++;
    }
    
    //2.
    public boolean estPresent(Utilisateur u){
        int i=0;
        boolean estPresent=false;
        while(i<nbreSalons && !estPresent){
            estPresent=salons[i].estPresent(u);
            i++;
        }
        return estPresent;
    }
    
    //3.
    public int nombreMessages(Utilisateur u){
        int res=0;
        if(estPresent(u)){
            for(int i=0; i<nbreSalons; i++){
                res+=salons[i].nombreMessagesSalon(u);
            }
        }
        return res;
    }
    
    //4.
    public Utilisateur bavard(){
        int nbreParticipants=0;
        for(int i=0; i<nbreSalons; i++){
            nbreParticipants+=salons[i].participants.length;
        }
        
        //on fait un tableau avec tous les participants
        Utilisateur[]listeParticipants=new Utilisateur[nbreParticipants];
        nbreParticipants=0;
        for(int i=0; i<nbreSalons; i++){
            for(int j=0; j<salons[i].nbreParticipants; j++){
                listeParticipants[nbreParticipants]=salons[i].participants[j];
                nbreParticipants++;
            }
        }
        
        Utilisateur bavard=listeParticipants[0];
        for(int i=1; i<nbreParticipants; i++){
            if(this.nombreMessages(listeParticipants[i])>this.nombreMessages(bavard)){
                bavard=listeParticipants[i];
            }
        }
        return bavard;
    }
}