import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.Timer;

public class Control{
  private Model m;
  private View v;
  private Timer t1=new Timer(600,null);//0.6s d attente pour moveDown
  private Timer t2=new Timer(600,null);//pour removePets
  private Timer t3=new Timer(600,null);//pour moveLeftCol
  private Timer t4=new Timer(2400,null);//pour autoplay
  private String saveFile="save.bin";//le nom du fichier dans lequel se trouve la sauvegarde

  Control(View v){
    this.v=v;
    this.m=v.model;
  }
  
  void exitFrame(){//a utiliser a chaque fois qu on change de vue
    v.setVisible(false);
    v.dispose();
  }
  
  void IG(){//debut affichage graphique
    this.exitFrame();
    this.v=new ViewHomePage();
    this.m=v.model;
  }
  
  void shell(){//debut affichage textuel
    this.exitFrame();
    try{
      ShellView sh=new ShellView(new Player(new Levels(5)));
      sh.home();  
    }
    catch(Exception e){}
  }
  
  void help(){//affiche regles du jeu
    ((ViewHomePage)v).printRule();
  }
  
  void log(){//connexion/deserialization
    try {
      File file=new File(saveFile);
      ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
      Player p=(Player)ois.readObject();//celui qu on recupere depuis le fichier de sauvegarde
      this.exitFrame();
      this.v=new ViewLevelSummary(p);
      this.m=v.model;
      ois.close();
    }
    catch(Exception e) {
      System.out.println("Echec de reprise de sauvegarde");
    }
  }
  
  void save(){//sauvegarde du joueur/serialization
    try{
      File file=new File(saveFile);
      //ouverture d un flux sur un fichier
      ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
      //serialization de l objet
      oos.writeObject(this.m.getPlayer());
      //fermeture du flux
      oos.close();
    }
    catch(Exception e){
      System.out.println("Echec de sauvegarde");
    }
  }
  
  void sub(){//nouveau joueur
    this.exitFrame();
    Player p=new Player(new Levels(5));
    this.v=new ViewLevelSummary(p);
    this.m=v.model;       
  }
  
  void sub(Player p){//retour au sommaire du joueur p
    this.stop();//si on etait en auto-play
    this.exitFrame();
    this.v=new ViewLevelSummary(p);
    this.m=v.model;
  }
  
  void playLevel(int n, int h){//joue au niveau n avec h coeurs
    if(h>0){
    this.exitFrame();
    m.getPlayer().setCurrentL(n);
    this.v=new ViewPlayingLevel(m.getPlayer(), n, m.getPlayer().getHeart());
    this.m=v.model;
    stepByStep(m.getPlayer().getGame()[n-1], n, m.getPlayer().getHeart());//si on reprend une partie, pas derangeant si nouvelle partie
    }
  }
  
  void oneTouch(int col, int li, Level l, int n, int h){//un clic du joueur ; on ajuste l action selon usage ou non de bonus
    boolean[] bonusState=((ViewPlayingLevel)(this.v)).getBonusState();//connaitre l etat des bonus
    int bonus=whichBonus(bonusState);//savoir si on utilise un bonus et, si oui, lequel
    Player p=this.m.getPlayer();
    try{
      boolean willRemove=bonus==1 || l.getBoard().getBoard()[col][li].canBeRemoved();//case vide ou interdite pour casser une case
      switch(bonus) {
      case -1:
        l.getBoard().removeBlock(col, li);
        break;
      case 0:
        if(!p.getBonus()[0].isNull()) l.getBoard().removeCase(col, li);
        break;
      case 1:
        if(!p.getBonus()[1].isNull()) l.getBoard().removeCol(col);
        break;
      }
      if(bonus!=-1 && willRemove){
        p.removeBonus(bonus);
        ((ViewPlayingLevel)this.v).setBonusFalse(bonus);
      }
      ((ViewPlayingLevel)this.v).setLevel(m.getPlayer().getGame()[l.getN()-1]);//on met le tableau a jour dans la vue
      ((ViewPlayingLevel)this.v).update();//mise a jour de la vue
      stepByStep(l, n, h);//"animation" des etapes
    }
    catch(IllegalArgumentException ex){}//ne rien faire si on clique au mauvais endroit
  }
  
  private int whichBonus(boolean[] b) {
    int res=-1;//si on n utilise pas de bonus
    for(int i=0;i<b.length;i++) res=b[i]?i:res;//pas de probleme car au plus un booleen vrai
    return res;
  }
  
  void stepByStep(Level l, int n, int h){//"animation" des mouvements du plateau
    //deplacer vers le bas
    if(t1.getActionListeners().length!=0) t1.removeActionListener(t1.getActionListeners()[0]);//chaque appel a stepByStep ajoute un listener
    t1.addActionListener((e1)->{
      for(int i=0; i<9; i++) l.getBoard().moveDown(i);
      if(this.v instanceof ViewPlayingLevel){//si victoire, probleme de vue
        ((ViewPlayingLevel)Control.this.v).setLevel(m.getPlayer().getGame()[l.getN()-1]);//mise a jour du tableau de jeu
        ((ViewPlayingLevel)Control.this.v).update();//mise a jour de la vue
        ((ViewPlayingLevel)Control.this.v).updateScore();//mise a jour du score
      }
      t2.start();//appel au mouvement suivent
    });
       
    //enlever les animaux
    if(t2.getActionListeners().length!=0) t2.removeActionListener(t2.getActionListeners()[0]);
    t2.addActionListener((e2)->{
      l.getBoard().removePets();
      if(this.v instanceof ViewPlayingLevel){
        ((ViewPlayingLevel)Control.this.v).setLevel(m.getPlayer().getGame()[l.getN()-1]);//mise a jour du tableau de jeu
        ((ViewPlayingLevel)Control.this.v).update();//mise a jour de la vue
        ((ViewPlayingLevel)Control.this.v).updateScore();//mise a jour du score
      }
      t3.start();
    });
          
    //remettre a gauche
    if(t3.getActionListeners().length!=0) t3.removeActionListener(t3.getActionListeners()[0]);
    t3.addActionListener((e3)->{
      for(int i=1; i<9; i++) l.getBoard().moveLeftCol(i);
      if(this.v instanceof ViewPlayingLevel){
        ((ViewPlayingLevel)Control.this.v).setLevel(m.getPlayer().getGame()[l.getN()-1]);//mise a jour du tableau de jeu
        ((ViewPlayingLevel)Control.this.v).update();//mise a jour de la vue
        ((ViewPlayingLevel)Control.this.v).updateScore();//mise a jour du score
      }
      if(l.getBoard().win()){
        this.win(n, h);
        if(t4.isRunning()) stop();
      }
      else if(l.getBoard().needACall()){
        if(t4.isRunning()){
          stop();
          t4.start();//t4 vide de 2.4s, pour laisser le temps de ranger avant un autre mouvement
        }
        t1.start();
      }//s il reste des operations a faire pour finir le tour
      else if(l.getBoard().fail()){
        this.fail(n, h);
        if(t4.isRunning()) stop();
      }
      else if(t4.isRunning()) this.auto(l, n, h);//pour l auto-play
    });
    
    t1.setRepeats(false);//empeche la repetition du timer
    t2.setRepeats(false);
    t3.setRepeats(false);
    t1.start();//lance le mouvement t1
  }
  
  void auto(Level l, int n, int h){//auto-play
    if(t4.getActionListeners().length!=0) t4.removeActionListener(t4.getActionListeners()[0]);
    t4.addActionListener((e3)->{
      int[]coord=l.getBoard().maxCoord(l.getBoard());//col et li
      this.oneTouch(coord[0], coord[1], l, n, h);
    });
    t4.start();
  }
  
  void stop(){//stop auto-play
    if(t4.getActionListeners().length!=0) t4.removeActionListener(t4.getActionListeners()[0]);
    t4.stop();
  }
  
  void win(int n, int h){//victoire du niveau n
    if(n==m.getPlayer().getcurrentL()+1){//debloquer qu un niveau si plusieurs oneTouch en meme temps
      this.exitFrame();
      m.getPlayer().win();
      this.v=new ViewEndGame(this.m.getPlayer(), n+1, h, true);
      this.m=this.v.model;
    }
  }
  
  void fail(int n, int h){//defaite du niveau n
    if(h==m.getPlayer().getHeart() && m.getPlayer().fail()){//appliquer qu une fois la methode si plusieurs oneTouch
      this.exitFrame();
      this.v=new ViewEndGame(this.m.getPlayer(), n, h-1, false);
      this.m=this.v.model;  
    }
  }
  
  void retry(int n, int h){//recommencer le niveau n
    this.stop();//si on etait en auto-play
    if(h>0){
      m.getPlayer().resetLevel(n);
      this.playLevel(n, h);
    }
  }
}