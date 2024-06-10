import java.io.Serializable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Player implements Serializable{
  private static final long serialVersionUID = 1L;
  private int heart=5;//diminue quand perd un niveau
  private Levels game;
  private int currentLevel=0;
  private Bonus[] bonus=new Bonus[2];
  private String saveFile="save.bin";//le nom du fichier dans lequel se trouve la sauvegarde

  Player(Levels l, Bonus[]bonus){
    game=l;
    this.bonus=bonus;
  }
  
  Player(Levels l){
    Bonus b=new Bonus();
    this.bonus[0]=b.new BlockBuster(0);
    this.bonus[1]=b.new ColumnBlaster(0);
    game=l;
  }
  
  Level[] getGame(){//l objet Levels en lui meme ne nous interesse pas
    return game.getLevels();
  }
  
  int getcurrentL(){
    return this.currentLevel;
  }

  void setCurrentL(int n){
    this.currentLevel=n-1;//decalage tableau de niveau et numero du niveau
  }
  
  int getHeart(){
    return heart;
  }
  
  Bonus[] getBonus(){
    Bonus[]res=new Bonus[this.bonus.length];
    for(int i=0; i<res.length; i++){
      res[i]=new Bonus(bonus[i].getName(), bonus[i].getNumber());
    }
    return res;
  }
  
  void removeBonus(int n) {//enlever un exemplaire du bonus n
    this.bonus[n].remove();
  }
  
  void win(){
    this.resetLevel(currentLevel+1);//chaque reset enleve un coeur
    if(this.heart<5) this.heart++;//donc ajout dans la limite de 5 maximum
    game.unlockNext(currentLevel++);
    for(int i=0; i<bonus.length; i++) bonus[i].add();//ajout d un bonus de chaque apres victoire
  }
  
  boolean fail(){
    for(int i=0; i<bonus.length; i++){
      if(!bonus[i].isNull()) return false;//encore des bonus, donc jeu pas fini
    }
    this.resetLevel(currentLevel+1);//plus de bonus et plus de mouvement possible
    return true;
  }

  void resetLevel(int n){
    this.heart--;
    game.resetLevel(n);
  }

  void oneTurn(int col, int li){
    Board b=game.getLevels()[currentLevel].getBoard();
    b.removeBlock(col, li);
    do{
      for(int i=0; i<9; i++) b.moveDown(i);
      b.removePets();
      for(int i=1; i<9; i++) b.moveLeftCol(i);
      if(b.win()){
        System.out.println("\n  ----------");//pour separer les tableaux
        this.getGame()[this.currentLevel].getBoard().printBoard("Niveau "+Integer.toString(this.currentLevel+1));
        this.win();
      }
    }
    while(b.needACall());
  }
  
  private void useBonus(){
    Board board=this.game.getLevels()[this.currentLevel].getBoard();
    Scanner sc=new Scanner(System.in);
    //choix du bonus
    System.out.println("Choisir un bonus (1 ou 2) ou R pour quitter les bonus :");
    printBonus();
    String selectBonus=sc.next();//le bonus qu on souhaite utiliser
    while(!(selectBonus.equals("1") || selectBonus.equals("2") || selectBonus.equals("R"))){
      System.out.println("Incorrect");
      selectBonus=sc.next();
    }
    if(selectBonus.equals("R")) return;
    //choix des coordonnees
    System.out.println("Entrer un numero de bloc (exemple : A8) ou R pour quitter les bonus :");
    String coord=sc.next();//les coordonnees auxquelles on souhaite utiliser le bonus
    if(coord.equals("R")) return;
    //utilisation du bonus si possible
    try{
      this.bonus[Integer.parseInt(selectBonus)-1].use(board, matchAlphabet(coord.charAt(0)), Integer.parseInt(coord.substring(1)));
      do{
        for(int i=0; i<9; i++) board.moveDown(i);
        board.removePets();
        for(int i=1; i<9; i++) board.moveLeftCol(i);
        if(board.win()){
          System.out.println("\n  ----------");//pour separer les tableaux
          board.printBoard("Niveau "+Integer.toString(this.currentLevel+1));
          this.win();
        }
      }
      while(board.needACall());

    }
    catch(Exception e){
      System.out.println("Impossible");
    }
  }
    
  private void printBonus(){//afficher les bonus sous forme de liste numerotee
    for(int i=0;i<this.bonus.length;i++) System.out.println((i+1)+") "+this.bonus[i]);
  }
  
  void playWithShell(){
    int thisLevel=this.currentLevel;
    Scanner sc=null;
    while(this.currentLevel==thisLevel){
      System.out.println("\n  ----------");//pour separer les tableaux
      this.getGame()[this.currentLevel].getBoard().printBoard("Niveau "+Integer.toString(this.currentLevel+1));
      sc=new Scanner(System.in);
      System.out.println("Entrer un numero de bloc (exemple : A8), BONUS pour utiliser un bonus, AUTO pour jouer automatiquement ou Q pour quitter :");      
      String coord=sc.next();
      if(coord.equals("Q")) return;
      try{
        if(coord.equals("BONUS")) useBonus();
        else if(coord.equals("AUTO")) autoplay(thisLevel);
        else oneTurn(matchAlphabet(coord.charAt(0)), Integer.parseInt(coord.substring(1)));
      }
      catch(IllegalArgumentException e){
        System.out.println("Mauvaises coordonnees.");
      }
    }
    this.serialize();//on serialize des qu un niveau est termine
    System.out.println("Felicitation, vous pouvez passer au niveau suivant !");
    System.out.println("Voulez-vous passer au niveau "+(this.currentLevel+1)+" ? (Y/N)");
    if(sc.next().equals("Y")) playWithShell();
    sc.close();
  }

  int matchAlphabet(char c){
    char[]alpha={'A','B','C','D','E','F','G','H','I'};
    for(int i=0; i<9; i++){
      if(c==alpha[i]) return i;
    }
    return -1;
  }

  private void serialize(){
    try{
      File file=new File(saveFile);
      ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(this);
      oos.close();
    }
    catch(Exception e){
      System.out.println("Echec de sauvegarde");
    }
  }

  void autoplay(int n){
    int nbCoups=0;
    do{
      if(nbCoups!=0){
        System.out.println("\n  ----------");//pour separer les tableaux
        this.getGame()[n].getBoard().printBoard("Niveau "+Integer.toString(this.currentLevel+1));
      }
      Board b=game.getLevels()[n].getBoard();
      int[]coord=b.maxCoord(b);
      oneTurn(coord[0], coord[1]);
      nbCoups++;
    }
    while(n==this.currentLevel);
    System.out.println(nbCoups+" coups ont ete joues.");
  }
}