//1.1
public class Client {
    private final String nom;
    private final String prenom;

    Client(String nom, String prenom){
        this.nom=nom;
        this.prenom=prenom;
    }

    String getNom() {
        return this.nom;
    }

    String getPrenom() {
        return this.prenom;
    }

    /***/
    static boolean memeClient(Client c1, Client c2) {
        return c1.getNom().equals(c2.getNom()) && c1.getPrenom().equals(c2.getPrenom());
    }
    /***/
}