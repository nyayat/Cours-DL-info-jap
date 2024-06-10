public class Fantassin extends Roturier {
    private int degats;

    public Fantassin(String n, int a, int pdv, int degats) {
        super(n, a, pdv);
        this.degats = degats;
    }

    //1.2.c
    public void attaque(Personne p) {
        if (p instanceof Chevalier) {
            ((Chevalier) p).capture(this);
            //((Chevalier) p).geolier = this;
        } else {
            p.blessure(degats);
        }
    }
}
