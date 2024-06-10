import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ShellView{
  private Player p;
  private String saveFile="save.bin";//le nom du fichier dans lequel se trouve la sauvegarde

  ShellView(Player p){
    this.p=p;
  }

  void home() throws IOException, ClassNotFoundException{
    Scanner sc=new Scanner(System.in);
    System.out.println("Selectionnez une action :");
    System.out.println("Regles du jeu : 1");
    System.out.println("Continuer une partie entamee : 2");
    System.out.println("Commencer une nouvelle partie : 3");
    String s=sc.nextLine();
    while(!(s.equals("1")||s.equals("2")||s.equals("3"))) {//on veut que le choix se fasse parmi ce qu on propose
      incorrect();
      s=sc.nextLine();
    }
    int n=Integer.parseInt(s);
    System.out.println();//sauter une ligne pour faire joli
    switch(n){//lancer la bonne methode selon le choix effectue
      case 1:
        rules();
        break;
      case 2:
        continueGame();
        break;
      case 3:
        newGame();
        break;
    }
    sc.close();
  }

  //affichage des regles
  void rules() throws IOException, ClassNotFoundException{
    System.out.println("Le but du jeu est de sauver tous les animaux bloques en haut des blocs de cubes.\nPour cela, vous devez eliminer des blocs de meme couleur pour faire descendre les animaux, jusqu a arriver a sauver tout le monde.\n");
    home();//on revient automatiquement au menu principal
  }

  void continueGame(){
    try {
      File file=new File(saveFile);
      ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
      this.p=(Player)ois.readObject();//celui qu on recupere depuis le fichier de sauvegarde
      ois.close();
    }
    catch(Exception e) {
      System.out.println("Echec de reprise de sauvegarde");
    }
    p.playWithShell();
  }

  void newGame() throws IOException, ClassNotFoundException{
    this.p=new Player(new Levels(5));
    p.playWithShell();
  }

  void incorrect(){
    System.out.println("Mauvaise entree, veuillez recommencer.");
  }
}