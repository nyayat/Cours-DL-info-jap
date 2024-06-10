public class Lanceur{
  //11.
  public static void main(String[] args){
    Joueur player=new Joueur();
    player.askName();
    String playOrNot=player.askPlay();
    if(playOrNot.equals("y")){
        Jeu.jouer(player);
    }
    if(playOrNot.equals("n")){
        player.finish();
    }
  }
}