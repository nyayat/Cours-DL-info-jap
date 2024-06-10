//2.1
public class Article {
    final String description;
    Client client;  //celui qui réserve
    FileDAttente file;  //les autres qui veulent réserver
    int identifiant;
    static int nbArticle=0;
    //3
    int stock;

    Article(String description){
        this.description=description;
        this.identifiant=++nbArticle;
        this.file=new FileDAttente();
        this.client=null;
        //3
        this.stock=0;
    }

    //2.2
    void ajouterAuPanierDe(Client c) {
        if(this.file.estVide()) {
                this.client=c;
        }
        else{
            this.file.ajouterClient(c);
        }
    }

    //2.3
    void retirerDuPanierDe(Client c) {
        if(Client.memeClient(c, this.client)) {
            this.client=this.file.extraitPremier();
        }
        else {
            this.file.desiste(c);
        }
    }
}