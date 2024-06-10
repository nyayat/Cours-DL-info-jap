public class Jeu {
    //10.
    Joueur joueur;
    Plateau plateau;

    Jeu(Joueur joueur){
        this.joueur=joueur;
        int[] taillePlateau=joueur.askTaille();
        this.plateau=new Plateau(taillePlateau[1],taillePlateau[0],joueur.askMines());
    }

    static void jouer(Joueur joueur){
        Jeu jeu=new Jeu(joueur);
        while(!jeu.plateau.jeuPerdu() && !jeu.plateau.jeuGagne()){
            jeu.plateau.affichage();
            int[] action=jeu.joueur.askMove();
            while(action[1]<=0 || action[1]>jeu.plateau.getLargeur()-2 || action[2]<=0 || action[2]>jeu.plateau.getHauteur()-2){//si les coordonnées sont incorrectes
                System.out.println("Coordonnées incorrectes");
                action=jeu.joueur.askMove();
            }
            if (action[0]==1) jeu.plateau.drapeauCase(action[1], action[2]);//poser/enlever drapeau
            else if(action[0]==2) jeu.plateau.revelerCase(action[1], action[2]);//découvrir case
        }
        if(jeu.plateau.jeuGagne()) System.out.println("Gagné !");
        else System.out.println("Perdu !");
        if(joueur.askPlay().equals("y")) jouer(jeu.joueur);
        else jeu.joueur.finish();
      }
}