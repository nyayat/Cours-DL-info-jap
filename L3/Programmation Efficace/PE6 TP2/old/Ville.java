package TP2;

public class Ville {
    private final String nom;
    private final int index;
    private final int creditsA, creditsB, creditsC;

    private boolean visited = false;

    public Ville(String nom, int index, int creditsA, int creditsB, int creditsC) {
        this.nom = nom;
        this.index = index;
        this.creditsA = creditsA;
        this.creditsB = creditsB;
        this.creditsC = creditsC;
    }

    public String getNom() {
        return nom;
    }

    public int getIndex() {
        return index;
    }

    public int getCredits(Bus.BUS_TYPE type) {
        return switch (type) {
            case A -> this.creditsA;
            case B -> this.creditsB;
            case C -> this.creditsC;
        };
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void visit(){
        this.visited = true;
    }
}
