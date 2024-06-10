import java.util.LinkedList;

public class Condottiere extends Personne {
    LinkedList<Archer> archers = new LinkedList<Archer>();
    LinkedList<Fantassin> fantassins = new LinkedList<Fantassin>();

    public Condottiere(String n, int a, int pdv) {
        super(n, a, pdv);
        archers.add(new Archer("Tom", 5, 100));
        fantassins.add(new Fantassin("Tom2", 8, 100, 10));
    }

    public void attaque(Village v) {
        int vol = v.volArgent();
        int m = vol/2;
        int nCie = archers.size() + fantassins.size();
        int reste = m/nCie;
        this.gain(m);
        for (Archer a : archers) {
            a.gain(reste);
        }
        for (Fantassin f : fantassins) {
            f.gain(reste);
        }
    }
}
