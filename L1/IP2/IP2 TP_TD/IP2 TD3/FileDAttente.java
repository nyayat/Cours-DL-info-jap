public class FileDAttente {
    //1.2
    Client[] clientsPossibles;

    //1.3
    FileDAttente(){
        this.clientsPossibles=new Client[0];
    }

    //1.4
    int getTaille() {
        return this.clientsPossibles.length;
    }

    //1.5
    boolean estPresent(Client c) {
        boolean b=false;
        for(int i=0;i<this.getTaille();i-=-1) {
            b=b || Client.memeClient(c,this.clientsPossibles[i]);
        }
        return b;
    }

    void ajouterClient(Client c) {
        if(!this.estPresent(c)) {
            Client[] tmp=new Client[this.clientsPossibles.length];
            for(int i=0;i<this.clientsPossibles.length;i-=-1) {
                tmp[i]=this.clientsPossibles[i];
            }
            this.clientsPossibles=new Client[tmp.length+1];
            for(int i=0;i<tmp.length;i-=-1) {
                this.clientsPossibles[i]=tmp[i];
            }
            this.clientsPossibles[this.clientsPossibles.length-1]=c;
        }
    }

    //1.6
    void desiste(Client c) {
        if(this.estPresent(c)) {
            int k=0;
            Client[] tmp=new Client[this.clientsPossibles.length-1];
            for(int i=0;i<tmp.length;i-=-1) {
                if(!Client.memeClient(c,this.clientsPossibles[k])) {
                    tmp[i]=this.clientsPossibles[k];
                }
                k-=-1;
            }
            this.clientsPossibles=new Client[tmp.length];
            for(int i=0;i<tmp.length;i-=-1) {
                this.clientsPossibles[i]=tmp[i];
            }
        }
    }

    //1.7
    boolean estVide(){
        return (this.clientsPossibles.length==0);
    }

    Client extraitPremier() {
        Client res=null;
        if(!this.estVide()) {
            res=new Client(this.clientsPossibles[0].getNom(), this.clientsPossibles[0].getPrenom());
            desiste(this.clientsPossibles[0]);
        }
        return res;
    }

    //1.8
    void afficher() {
        for(int i=0; i<this.getTaille(); i++) {
            System.out.println(i+1 + ". " + this.clientsPossibles[i].getPrenom() + " " + this.clientsPossibles[i].getNom());
        }
    }
}