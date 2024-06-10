import java.awt.Color;
import java.io.Serializable;

public class Board implements Serializable{
  private static final long serialVersionUID = 1L;
  private Case[][] board;
  private int score;//x cases cassees=>score+=x*x*10 ; +1000 par pet en bas
  private int nbPets;
  
  Board(int li){
    this.board=new Case[9][li];//jamais plus de 9 colonnes
    for(int i=0;i<9;i++){
      for(int j=0;j<li;j++){
        this.board[i][j]=new Case();
      }
      this.score=0;
      this.nbPets=0;
    }
  }
  
  private Board(Board b){//pour auto-play exclusivement
    this.board=b.getBoard();//nouveau tableau de cases, independant de b
    this.score=0;
    this.nbPets=0;
  }

  void setPet(int col, int li){//placer les pets
    this.board[col][li].pet=true;
    this.board[col][li].color=Color.white;
    this.nbPets++;
  }

  void setColor(int col, int li, Color c){
    if(!c.equals(Color.black)) this.board[col][li].color=c;
  }

  int getHeight(){
    return this.board[0].length;//nombre de lignes
  }

  int getWidth(){
    return this.board.length;//nombre de colonnes
  }
  
  int getScore(){
    return this.score;
  }

  void forbidCase(int col, int li){//interdire des cases dans un plateau donne
    this.board[col][li]=new Case();
  }
  
  Case[][] getBoard(){
    int height=this.board.length;
    int width=this.board[0].length;
    Case[][] res=new Case[height][width];
    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
        res[i][j]=this.board[i][j];
      }
    }
    return res;
  }

  private Board getNewBoard(){//pour auto-play exclusivement
    return new Board(this);
  }
  
  void moveDown(int col){
    int c=0;
    for(int i=0; i<this.board[col].length-1; i++){
      if(this.board[col][i].isEmpty()){
        c=i+1;//cherche case non vide en haut
        while(c<board[col].length-1 && !board[col][c].isForbidden() && board[col][c].isEmpty()) c++;
        Color newColor=board[col][c].isForbidden()?Color.white:board[col][c].color;//on ne "deplacera" pas une case interdite, on ne fera que liberer ce qui est en-dessous
        this.board[col][i]=new Case(newColor, board[col][c].pet);
        removeCase(col, c);
        board[col][c].pet=false;
      }
    }
  }

  void moveLeft(int col, int li){//par defaut, on se deplace vers la gauche
    if(col>0 && !this.board[col][li].isForbidden() && !this.board[col-1][li].isForbidden()){//on ne deplace pas les cases interdites
      Color newColor=board[col][li].isForbidden()?Color.white:board[col][li].color;
      this.board[col-1][li]=new Case(newColor, board[col][li].pet);
      removeCase(col, li);
      board[col][li].pet=false;
      this.removePets();//un animal peut tomber s il se trouve a droite d une case interdite
    }
  }

  void moveLeftCol(int col){//utilisation : moveDown --> moveLeftCol --> moveDown
    if(col<1) return;
    int height=heightForbiddenC(col)+1;//0 ou taille de premiere case interdite "utile"
    if(colEmpty(col-1, height)){
      for(int i=height; i<this.board[col].length; i++) moveLeft(col, i);
      if(height>0) moveDown(col-1);
      moveLeftCol(col-1);//pour la bouger le plus a gauche possible
    }
  }
  
  boolean colEmpty(int col, int height){//colonne vide a partir de ligne height ?
    for(int i=height; i<this.board[col].length; i++){
      if(!(board[col][i].isEmpty() || board[col][i].isForbidden())) return false;//case interdite gere dans moveLeft
    }
    return true;
  }

  int heightForbiddenC(int col){//hauteur de la premiere case interdite en bas d une case de couleur
    for(int i=this.board[col].length-2; i>-1; i--){//si la case tout en haut est interdite, pas derangeant
      if(board[col][i].isForbidden() && !board[col][i+1].isEmpty() &&!board[col][i+1].isForbidden()) return i;
    }
    return -1;  //pas de cases interdites dans la colonne
  }

  int removeCase(int col, int li){
    int res=0;
    try{
      if(this.board[col][li].canBeRemoved()){//on prefere ne pas retirer une case avec un animal ou une case interdite
        this.board[col][li]=new Case(Color.white);
        res++;
      }
    }
    catch (IllegalArgumentException e){
      System.out.println("Impossible");
    }
    return res;
  }

  void removeBlock(int col, int li){
    int res=0;//variable pour le score
    boolean isValid=(col>=0 && col<=this.board.length && li>=0 && li<=this.board[col].length);
    if(isValid && this.board[col][li].canBeRemoved() && this.existsSameColor(col, li)){//on prefere ne pas retirer une case avec un animal ou une case interdite
      Color c=this.board[col][li].color;
      res=this.removeCase(col,li);
      //enlever cases de meme couleur autour
      if(li>0 && this.board[col][li-1].color.equals(c)) res+=this.removeBlockTemp(col,li-1);
      if(li<this.board[col].length-1 && this.board[col][li+1].color.equals(c)) res+=this.removeBlockTemp(col,li+1);
      if(col>0 && this.board[col-1][li].color.equals(c)) res+=this.removeBlockTemp(col-1,li);
      if(col<8 && this.board[col+1][li].color.equals(c)) res+=this.removeBlockTemp(col+1,li);
    }
    else throw new IllegalArgumentException();
    this.score+=res*res*10;
  }

  int removeBlockTemp(int col, int li){
    int temp=0;
    boolean isValid=(col>=0 && col<=this.board.length && li>=0 && li<=this.board[col].length);
    if(isValid){//les autres conditions de couleur sont normalement verifiees
      Color c=this.board[col][li].color;
      temp=this.removeCase(col,li);
      if(li>0 && this.board[col][li-1].color.equals(c)) temp+=this.removeBlockTemp(col,li-1);
      if(li<this.board[col].length-1 && this.board[col][li+1].color.equals(c)) temp+=this.removeBlockTemp(col,li+1);
      if(col>0 && this.board[col-1][li].color.equals(c)) temp+=this.removeBlockTemp(col-1,li);
      if(col<8 && this.board[col+1][li].color.equals(c)) temp+=this.removeBlockTemp(col+1,li);
    }
    return temp;
  }

  boolean existsSameColor(int col, int li){
    Color c=this.board[col][li].color;
    if(c.equals(Color.white) || c.equals(Color.black)) return false;//blanc=vide ou pet, pas interessant dans tous les cas
    boolean res=false;
    if(li>0) res|=this.board[col][li-1].color.equals(c);
    if(li<this.board[col].length-1) res|=this.board[col][li+1].color.equals(c);
    if(col>0) res|=this.board[col-1][li].color.equals(c);
    if(col<8) res|=this.board[col+1][li].color.equals(c);
    return res;
  }

  void removeCol(int col){//pour le bonus brise-colonne
    int res=0;//nombre de cases enleve
    for(int i=0;i<this.board[col].length;i++) res+=this.removeCase(col,i);
    for(int j=col+1; j<this.board.length; j++) this.moveLeftCol(j);//on deplace ensuite toutes les colonnes
    this.score+=res*res*10;
  }

  void removePets(){//ceux arrives en bas ; a faire apres chaque tour
    int res=0;//nombre de pets sauves
    for(int j=0;j<9;j++){
      while(this.board[j][0].pet){
        res++;
        this.board[j][0].pet=false;
        this.nbPets--;
        this.moveDown(j);
      }
    }
    this.score+=res*1000;
  }
  
  boolean needACall(){
    int bottomLine=0;
    for(int col=0; col<9; col++){
      while(!colEmpty(col,0) && board[col][bottomLine].isForbidden())bottomLine++;
      if(board[col][bottomLine].isEmpty() && !colEmpty(col,0)) return true;
      if(col<8 && colEmpty(col,0) && !colEmpty(col+1,0)) return true;
    }
    return false;
  }

  boolean win(){
    return this.nbPets==0;
  }
  
  boolean fail(){
    for(int col=0; col<board.length; col++){
      for(int li=0; li<board[col].length; li++){
        if(this.existsSameColor(col, li)) return false;//mouvement encore possible
      }
    }
    return true;//plus de cases adjacentes de meme couleur
  }

  //renvoie le nombre de cases adjacentes a la case de coordonnees donnees en parametre de la meme couleur que celle-ci
  int countCaseInBlock(Board b, int col, int li){//b est manipulable sans crainte
    int res=0;
    boolean isValid=(col>=0 && col<this.board.length && li>=0 && li<this.board[col].length);
    if(isValid && this.board[col][li].canBeRemoved() && this.existsSameColor(col, li)){
      Color c=this.board[col][li].color;
      res=b.removeCase(col, li);
      //enlever cases de meme couleur autour
      if(li>0 && board[col][li-1].color.equals(c)) res+=b.countCaseInBlock(b,col,li-1);
      if(li<board[col].length-1 && board[col][li+1].color.equals(c)) res+=b.countCaseInBlock(b,col,li+1);
      if(col>0 && board[col-1][li].color.equals(c)) res+=b.countCaseInBlock(b,col-1,li);
      if(col<8 && board[col+1][li].color.equals(c)) res+=b.countCaseInBlock(b,col+1,li);
    }
    return res;
  }
  
  int[] maxCoord(Board b){//retourne les ccordonnees de l endroit ou il y a le plus de cases adjacentes de meme couleur
    int[]res={0,0};//{col, li}
    for(int col=0; col<board.length; col++){
      for(int li=0; li<board[col].length; li++){
        if(countCaseInBlock(b.getNewBoard(),res[0],res[1])<countCaseInBlock(b.getNewBoard(),col,li)){
          res[0]=col;
          res[1]=li;
        }
      }
    }
    return res;
  }


  class Case {//classe interne
    Color color;//noir=>interdit ; blanc=>vide ou avec animal
    boolean pet;

    Case(Color color, boolean pet){
      this.pet=pet;
      if(!pet) this.color=color;
      else this.color=Color.white;//on force le blanc s il y a un animal
    }

    Case(Color color){
      this.pet=false;
      this.color=color;
    }

    Case(){
      this.color=Color.black;
      this.pet=false;
    }

    boolean isForbidden(){//case noir ?
      return this.color.equals(Color.black);
    }

    boolean isEmpty(){//case blanche sans pet ?
      return (this.color.equals(Color.white) && !this.pet);
    }
    
    boolean canBeRemoved(){//sans animal et pas noir ?
      return (!this.pet && !this.isForbidden() && !this.isEmpty());
    }
  }//fin de classe interne Case

  void printBoard(String mot){
    System.out.println("   ABCDEFGHI"+"          "+mot);
    System.out.println("  ----------");
    for(int j=this.board[0].length-1;j>=0;j--){
      System.out.print(String.format("%02d",j)+"|");
      for(int i=0;i<this.board.length;i++){
        Case c=this.board[i][j];
        Color col=c.color;
        if(c.pet) System.out.print(";");
        else{
          if(col.equals(Color.white)) System.out.print(" ");
          if(col.equals(Color.black)) System.out.print("X");
          if(col.equals(Color.blue)) System.out.print("B");
          if(col.equals(Color.green)) System.out.print("G");
          if(col.equals(Color.magenta)) System.out.print("P");
          if(col.equals(Color.red)) System.out.print("R");
          if(col.equals(Color.yellow)) System.out.print("Y");
        }
      }
      System.out.println();
    }
  }
}