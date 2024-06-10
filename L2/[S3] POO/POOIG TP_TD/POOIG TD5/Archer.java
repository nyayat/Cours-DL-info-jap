public class Archer extends Roturier {
    public Archer(String n, int a, int pdv) {
        super(n, a, pdv);
    }

    public void attaque(Personne p) {
        p.blessure(p.pdv);
    }
}
