public class Model{
  private View geneV;
  private Player player;

  Model(View v, Player p){
    this.geneV=v;
    this.player=p;
  }
  
  Player getPlayer(){
    return this.player;
  }

  void setPlayer(Player player){
    this.player=player;
  }
}