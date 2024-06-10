import java.io.Serializable;

public class Level implements Serializable{
  private static final long serialVersionUID = 1L;
  
  private boolean unlocked, completed;//niveau accessible, niveau termine
  private final int n;//le numero du niveau
  private Board board;//tableau associe au niveau

  Level(Board board, int n){
    this.unlocked=(n==1);//le premier niveau est forcement deverrouille
    this.completed=false;
    this.board=board;
    this.n=n;
  }

  Board getBoard(){
    return this.board;
  }

  boolean getUnlocked(){
    return this.unlocked;
  }

  void complete(){
    this.completed=true;
  }

  void unlock(){
    this.unlocked=true;
  }
  
  int getN(){
    return n;
  }
}