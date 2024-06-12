public class Note {
    private int nbPlayed; //nombre de fois ou la note est jouée
    private final int a;
    private static int sommeA;

    Note(int frequency) {
        this.a = frequency;
    }

    int getNbPlayed() {
        return nbPlayed;
    }

    double getFrequency() {
        return (double) a / sommeA;
    }

    void play() {
        nbPlayed++;
    }

    /**
     * Une fois que le nombre de notes jouées atteint cette limite,
     * cette note doit impérativement être jouée.
     * Sinon les conditions ne seront plus vérifiées pour cette note.
     *
     * @return la dernière position dans la séquence où cette note peut ne pas être joué
     */
    int getLimit() {
        return (int) Math.floor((nbPlayed + 1) / getFrequency());
    }

    public String toString() {
        return "nb = " + nbPlayed + " freq = " + getFrequency() + " limit = " + getLimit();
    }

    static void setSommeA(int x) {
        sommeA = x;
    }
}
