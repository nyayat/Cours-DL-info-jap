public class Ville {
    final String nom;
    final int index;
    final int creditsA, creditsB, creditsC;

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
        switch (type){
            case A : return this.creditsA;
            case B : return this.creditsB;
            default : return this.creditsC;
        }
    }
    
    int getMaxCredits(){ //peut-etre pas besoin
        return Math.max(creditsA, Math.max(creditsB, creditsC));
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void visit(){
        this.visited = true;
    }
}
