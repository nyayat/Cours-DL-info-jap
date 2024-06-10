import java.awt.Color;
import java.io.Serializable;

public class Levels implements Serializable{
  private static final long serialVersionUID = 1L;
  private Level[] levels;

  Levels(int n){//dans notre cas, on initialisera une partie a 5 niveaux
    this.levels=new Level[n];
    AddLevels a=new AddLevels();
    a.addAll();
  }

  Levels(Level[] levels){//pour reprendre une sauvegarde
    this.levels=levels;
  }

  Level[] getLevels(){
    Level[]res=new Level[levels.length];//proteger l encapsulation
    for(int i=0; i<res.length; i++){
      res[i]=levels[i];
    }
    return res;
  }

  void unlockNext(int n){//n=niveau qui vient d etre fini
    levels[n].complete();
    if(n<this.levels.length-1) levels[n+1].unlock();
  }

  //on ecrase le niveau qui a ete modifie avec celui qui ne l a jamais ete sans oublier de deverrouiller le niveau
  void resetLevel(int n){
    if(n>0 && n<levels.length+1){//levels[0]~niveau 1, d où le decalage
      AddLevels a=new AddLevels();
      switch(n){
        case 1:
          a.addLevel1();
          break;
        case 2:
          a.addLevel2();
          break;
        case 3:
          a.addLevel3();
          break;
        case 4:
          a.addLevel4();
          break;
        case 5:
          a.addLevel5();
          break;
      }
      levels[n-1].unlock();
    }
  }


  private class AddLevels{//debut classe interne
    private Color[] colors=new Color[]{Color.blue, Color.green, Color.magenta, Color.red, Color.yellow};

    //on remplit un tableau de coordonnees pour lesquelles on changera la couleur du bloc du plateau par la couleur en parametre ; idem pour les animaux
    void fillBoardColor(Board b, int[][] coord, Color color){
      for(int[] t:coord) b.setColor(t[0],t[1],color);
    }

    void fillBoardPets(Board b, int[][] coord){
      for(int[] t:coord) b.setPet(t[0],t[1]);
    }

    void addAll(){
      addLevel1();
      addLevel2();
      addLevel3();
      addLevel4();
      addLevel5();
    }

    void addLevel1(){
      Board res=new Board(11);
      int[][] coordPets=new int[][]{{1,6}, {5,6}};
      int[][] coordColor0=new int[][]{{0,0},{0,1},{0,4},{0,5},{1,4},{1,5},{4,0},{4,1},{5,0},{5,1},{1,7}};
      int[][] coordColor1=new int[][]{{0,2},{0,3},{1,2},{1,3},{2,5},{3,5},{4,5},{6,0},{6,1},{3,0},{3,1},{1,8}};
      int[][] coordColor2=new int[][]{{1,0},{1,1},{2,0},{2,1},{5,4},{5,5},{6,4},{6,5},{1,9}};
      int[][] coordColor3=new int[][]{{5,2},{5,3},{6,2},{6,3},{1,10}};
      int[][] coordColor4=new int[][]{{2,2},{2,3},{2,4},{3,2},{3,3},{3,4},{4,2},{4,3},{4,4}};
      fillBoardColor(res, coordColor0,colors[0]);
      fillBoardColor(res, coordColor1,colors[1]);
      fillBoardColor(res, coordColor2,colors[2]);
      fillBoardColor(res, coordColor3,colors[3]);
      fillBoardColor(res, coordColor4,colors[4]);
      fillBoardPets(res, coordPets);
      levels[0]=new Level(res, 1);
    }

    void addLevel2(){
      Board res=new Board(8);
      int[][] coordPets=new int[][]{{0,6},{2,5},{2,6},{2,7},{4,6}};
      int[][] coordColor0=new int[][]{{0,0},{0,1},{1,2},{1,3},{1,6},{1,7},{3,2},{3,3},{3,6},{3,7},{4,0},{4,1}};
      int[][] coordColor1=new int[][]{{0,4},{0,5},{2,0},{2,1},{2,2},{2,3},{2,4},{4,4},{4,5}};
      int[][] coordColor2=new int[][]{{0,2},{0,3},{1,0},{1,1},{1,4},{1,5},{3,0},{3,1},{3,4},{3,5},{4,2},{4,3}};
      fillBoardColor(res, coordColor0,colors[0]);
      fillBoardColor(res, coordColor1,colors[1]);
      fillBoardColor(res, coordColor2,colors[2]);
      fillBoardPets(res, coordPets);
      levels[1]=new Level(res, 2);
    }

    void addLevel3(){
      Board res=new Board(9);
      int[][] coordPets=new int[][]{{2,8}, {4,8}, {6,8}};
      int[][] coordColor0=new int[][]{{0,4},{2,6},{2,7},{3,2},{3,3},{4,4},{4,5},{5,2},{5,3},{6,4},{6,5}};
      int[][] coordColor1=new int[][]{{1,1},{1,2},{1,4},{2,0},{2,1},{2,4},{2,5},{3,6},{3,7},{4,0},{4,1},{5,4},{5,5},{5,6},{5,7}};
      int[][] coordColor2=new int[][]{{0,0},{0,1},{0,2},{0,3},{1,0},{1,3},{2,2},{2,3},{3,0},{3,1},{3,4},{3,5},{4,2},{4,3},{4,6},{4,7},{6,2},{6,3},{6,6},{6,7}};
      int[][] coordEmpty=new int[][]{{3,8},{5,8}};
      fillBoardColor(res, coordColor0,colors[0]);
      fillBoardColor(res, coordColor1,colors[1]);
      fillBoardColor(res, coordColor2,colors[2]);
      fillBoardColor(res, coordEmpty, Color.white);
      fillBoardPets(res, coordPets);
      levels[2]=new Level(res, 3);
    }

    void addLevel4(){
      Board res=new Board(8);
      int[][] coordPets=new int[][]{{4,7},{5,7},{6,7},{7,7}};
      int[][] coordColor0=new int[][]{{0,1},{0,3},{1,0},{1,1},{2,2},{2,3},{3,0},{3,1},{3,4},{3,5},{5,2},{5,3},{5,4},{7,5},{7,6}};
      int[][] coordColor1=new int[][]{{0,2},{1,2},{1,3},{3,2},{3,3},{4,3},{4,4},{5,5},{5,6},{6,3},{6,4},{7,4}};
      int[][] coordColor2=new int[][]{{0,0},{1,4},{2,0},{2,1},{2,4},{2,5},{3,6},{4,1},{4,2},{4,5},{4,6},{6,5},{6,6}};
      fillBoardColor(res, coordColor0,colors[0]);
      fillBoardColor(res, coordColor1,colors[1]);
      fillBoardColor(res, coordColor2,colors[2]);
      fillBoardPets(res, coordPets);
      levels[3]=new Level(res, 4);
    }

    void addLevel5(){
      Board res=new Board(9);
      int[][] coordPets=new int[][]{{1,8}, {2,8}};
      int[][] coordColor0=new int[][]{{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{2,5},{2,6},{2,7},{3,0},{3,1},{3,2},{3,3},{3,4},{4,0},{4,1},{4,2},{4,3},{4,4}};
      int[][] coordColor1=new int[][]{{1,5},{1,7}};
      int[][] coordColor2=new int[][]{{1,6}};
      fillBoardColor(res, coordColor0,colors[0]);
      fillBoardColor(res, coordColor1,colors[1]);
      fillBoardColor(res, coordColor2,colors[2]);
      fillBoardPets(res, coordPets);
      levels[4]=new Level(res, 5);
    }
  }//fin classe interne AddLevels
}