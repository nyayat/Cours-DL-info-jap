import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class ViewPlayingLevel extends View{
  private Level level;
  private final int n;//numero du niveau
  private final int h;//nombre de coeur en commençant le niveau
  private int score;//score de ce niveau
  private int topLine;//ligne la plus en haut
  private int upperElements;//nombre d elements sur la ligne la plus haute
  private JPanel game=new JPanel();//panneau pour le plateau de jeu
  private ViewElements[][] tabV;//position des differents elements (case/pet) du jeu
  private ViewBonus viewBonus;//pour manipuler les bonus
  private JPanel endPage=new JPanel();//panneau pour bonus et reste
  private JLabel scoreText=new JLabel();//label du score, a mettre a jour apres chaque tour
  private JButton auto=new JButton("Auto");
  private JButton stop=new JButton("Stop");
    
  ViewPlayingLevel(Player p, int n, int h){
    super(p);
    this.setTitle("Niveau "+n);
    this.level=p.getGame()[n-1];//decalage d indices dans le tableau
    this.n=n;
    this.h=h;
    this.score=0;
    this.topLine=this.level.getBoard().getHeight()-1;//ligne tout en haut
    this.updateUpperEl();//mise a jour de topLine et upperElements
    this.setLayout(new BorderLayout());
    
    //construction de l affichage du plateau de jeu
    game.setLayout(null);
    game.setBackground(Color.white);
    tabV=new ViewElements[level.getBoard().getWidth()][topLine+1];
    for(int li=topLine; li>=0; li--){
      for(int col=0; col<this.level.getBoard().getWidth(); col++){
        Board.Case c=this.level.getBoard().getBoard()[col][li];
        ViewElements el=c.pet?new ViewPet(n%4+1):new ViewSquare(col,li,c.color);//l element qu on va ajouter
        if(topLine<9){//si l affichage ne depasse pas la fenetre
          el.setBounds(col*el.getWidth(),(8-li)*el.getWidth()+20,el.getWidth(),el.getHeight());
        }
        else el.setBounds(col*el.getWidth(),(topLine-li)*el.getWidth(),el.getWidth(),el.getHeight());
        game.add(el);
        tabV[col][li]=el;
      }
    }
    
    //bas de page
    endPage.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 0));
    endPage.setBorder(BorderFactory.createLineBorder(Color.black,2));
    //coeurs et score
    JPanel panReste=new JPanel();//panneau pour le score et les coeurs
    panReste.setLayout(new GridLayout(2, 1));//2 lignes, 1 colonne
    JLabel heartText=new JLabel("Coeur : "+((Integer)h).toString());
    this.scoreText.setText("Score : "+((Integer)score).toString());
    panReste.add(heartText);
    panReste.add(scoreText);
    endPage.add(panReste);
    //bonus
    viewBonus=new ViewBonus(p.getBonus());
    endPage.add(viewBonus);
    //bouton pause
    JButton pause=new JButton("Pause");
    pause.addActionListener((event)->ViewPlayingLevel.super.control.sub(p));
    endPage.add(pause);
    //bouton reset
    JButton reset=new JButton("Reessayer");
    reset.addActionListener((event)->ViewPlayingLevel.super.control.retry(n, h));
    endPage.add(reset);
    //bouton auto-play et stop
    auto.addActionListener((event)->{
      ViewPlayingLevel.super.control.auto(this.level, this.n, this.h);
      ViewPlayingLevel.this.addStop();
    });
    stop.addActionListener((event)->{
      ViewPlayingLevel.super.control.stop();
      ViewPlayingLevel.this.addAuto();
    });
    this.addAuto();

    //ajout des panneaux
    this.add(game, BorderLayout.CENTER);//ajout du JPanel de jeu au centre
    this.add(endPage, BorderLayout.PAGE_END);//le reste en fin de page
  }
  
  void addAuto(){
    endPage.remove(stop);
    endPage.add(auto);
    SwingUtilities.updateComponentTreeUI(this);
  }
  
  void addStop(){
    this.endPage.remove(auto);
    endPage.add(stop);
    SwingUtilities.updateComponentTreeUI(this);
  }
  
  int getHeart(){
    return this.h;
  }
  
  boolean[] getBonusState() {
    return this.viewBonus.state;
  }
  
  void setBonusFalse(int n) {
    this.viewBonus.state[n]=false;
  }
  
  void setLevel(Level l){
    this.level=l;
  }
  
  void updateUpperEl(){//on actualise topLine et upperElements
    upperElements=9;
    for(int i=0; i<9; i++){
      Board.Case c=level.getBoard().getBoard()[i][topLine];
      if(c.isEmpty() || c.isForbidden()) upperElements--;
    }
    if(topLine>1 && upperElements==0){
      topLine--;
      updateUpperEl();
    }
  }
  
  void update(){
    this.updateUpperEl();//mise a jour de topLine et upperElements
    this.clearUpperCase();//enleve les cases en haut de topLine
    for(int li=topLine; li>=0; li--){
      for(int col=0; col<level.getBoard().getWidth(); col++){
        Board.Case act=this.level.getBoard().getBoard()[col][li];//case actuelle
        game.remove(tabV[col][li]);//enleve l ancienne case de l affichage
        ViewElements el=act.pet?new ViewPet(n%4+1):new ViewSquare(col,li,act.color);//nouvel element
        if(topLine<9)//si l affichage ne depasse pas la fenetre
          el.setBounds(col*el.getWidth(),(8-li)*el.getWidth()+20,el.getWidth(),el.getHeight());
        else el.setBounds(col*el.getWidth(),(topLine-li)*el.getWidth(),el.getWidth(),el.getHeight());
        game.add(el);
        tabV[col][li]=el;
      }
    }
    for(int i=0; i<viewBonus.tabButt.length; i++){//mise a jour du nombre de chaque bonus
      viewBonus.tabButt[i].setText(((Integer)model.getPlayer().getBonus()[i].getNumber()).toString());
      if(!viewBonus.state[i]) viewBonus.tabButt[i].setBackground(null);//couleur seulement si selectionne
    }
    SwingUtilities.updateComponentTreeUI(this);
  }
  
  void updateScore(){
    this.score=this.level.getBoard().getScore();
    this.scoreText.setText("Score : "+((Integer)score).toString());
  }
  
  void clearUpperCase(){
    for(int col=0; col<9; col++){
      for(int li=topLine+1; li<tabV[col].length; li++){
        Color temp=tabV[col][li].getBackground();
        if(tabV[col][li] instanceof ViewPet || !temp.equals(Color.black)) game.remove(tabV[col][li]);
        game.add(new ViewSquare(col, li, Color.white));
      }
    }
  }
  

  private class ViewElements extends JPanel implements MouseInputListener{//carre ou animal
    ViewElements(){
      this.addMouseListener(this);
      int side=ViewPlayingLevel.super.getSize().width/9;//jusqu a 9 carres horizontalement (nombre de colonnes)
      this.setBounds(0, 0, side, side);//on les positionne correctement dans ViewPlayingLevel
    }
	
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    
    void fillImage(String filename){
      ImageIcon icon=new ImageIcon(getClass().getResource(filename));
      icon=new ImageIcon(icon.getImage().getScaledInstance(54, 54, Image.SCALE_DEFAULT));
      JLabel image=new JLabel(icon);
      this.add(image);
    }
  }//fin de classe interne ViewElements
  
  
  private class ViewSquare extends ViewElements{//carres
    int col, li;
      
    ViewSquare(int col, int li, Color color){
      super();
      this.col=col;
      this.li=li;
      
      //image de la case
      fillImage(filenameColor(color));
      this.setBackground(color);
    }
	  
    public void mouseClicked(MouseEvent e){
      ViewPlayingLevel.super.control.oneTouch(col, li, ViewPlayingLevel.this.level, ViewPlayingLevel.this.n, ViewPlayingLevel.this.h);
      ViewPlayingLevel.super.control.stop();//si on interrompt l auto-play
      ViewPlayingLevel.this.addAuto();
    }
    
    String filenameColor(Color c){
      String res="images/";
      String[]colorName={"blue","green","magenta","red","yellow"};
      Color[]tabColor={Color.blue, Color.green,Color.magenta,Color.red,Color.yellow};
      for(int i=0; i<5; i++){
        if(c.equals(tabColor[i]))res+=colorName[i]+".png";
      }
      return res;
    }
  }//fin de classe interne ViewSquare
  
  
  private class ViewPet extends ViewElements{//animaux
    ViewPet(int n){
      super();
      
      //image de la case
      fillImage(filenamePet(n));
      this.setBackground(Color.white);
    }
    
    String filenamePet(int n){
      return "images/pet"+n+".png";
    }
  }//fin de classe interne ViewPet
  

  private class ViewBonus extends JPanel{//bonus
    boolean[] state;//savoir si un bonus est pret a etre utilise ou non
    JButton[] tabButt;//pour gerer l affichage du nombre de bonus
    
    ViewBonus(Bonus[]b){//tableau des bonus
      this.state=new boolean[b.length];
      this.tabButt=new JButton[b.length];
      for(int i=0; i<b.length; i++){
        JPanel oneBonus=new JPanel();
        oneBonus.setLayout(new GridLayout(2, 1));//2 lignes, 1 colonne
        tabButt[i]=new JButton(((Integer)b[i].getNumber()).toString());
        int numBonus=i;//effectively final dans l expression lambda
        tabButt[i].addActionListener((event)->{
          for(int j=0;j<state.length;j++) state[j]=(j==numBonus?!state[j]:false);//change l etat du bonus choisi et desactive les autres
          if(state[numBonus]) tabButt[numBonus].setBackground(Color.darkGray);//change la couleur de fond si selectionne
          if(!state[numBonus]) tabButt[numBonus].setBackground(null);//enleve la couleur de fond si deselectionner
        });
        
        oneBonus.add(new JLabel(b[i].getName()));
        oneBonus.add(tabButt[i]);
        this.add(oneBonus);
      }
    }
  }
}