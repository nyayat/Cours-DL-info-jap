public class Chevalier extends Noble {
    //1.2.b
    private Personne geolier;
    
    //1.3
    private static final int prixLiberté = 50;

    public Chevalier(String n, int a, int pdv) {
        super(n, a, pdv);
    }

    //1.2.b
    public void attaque(Personne p) {
        if(p instanceof Chevalier) {
            ((Chevalier) p).geolier = this;
        }
    }

    //1.2.c
    public void capture(Fantassin x) {
        this.geolier = x;
    }

    //1.3
    public boolean acheteLiberte() {
        if(this.perte(prixLiberté)) {
            geolier.gain(prixLiberté);
            this.geolier = null;
            return true;
        }
        return false;
    }
}
