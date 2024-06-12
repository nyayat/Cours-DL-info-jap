public class Equipe {
    private long nbJoueur = 0;
    private long sommePoids = 0;

    public long calculeCoutWith(long poids) {
        return nbJoueur * sommePoids + nbJoueur * poids;
    }

    public void ajouterJoueur(Joueur j){
        nbJoueur ++;
        sommePoids += j.getPoids();
    }

    public long getCout(){
        return sommePoids * (nbJoueur - 1);
    }
}
