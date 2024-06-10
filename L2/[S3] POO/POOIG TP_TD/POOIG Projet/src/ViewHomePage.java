import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ViewHomePage extends View{
  private JButton help=new JButton("Regles du jeu");
  private JButton log=new JButton("Continuer une partie entammee");
  private JButton sub=new JButton("Commencer une nouvelle partie");
  private JPanel panelMain=new JPanel();
    
  ViewHomePage(){
    super();
    this.setTitle("Page d accueil");
    
    this.panelMain.setLayout(new GridBagLayout());
    
    //ajout des boutons avec presentation et des Listeners
    GridBagConstraints c=new GridBagConstraints();//contraintes d ajout
    
    c.insets=new Insets(20,10,0,0);//marges (haut, gauche, bas, droit)
    this.panelMain.add(log, c);
    log.addActionListener((event)->super.control.log());
    
    c.gridx=1;//à côte du bouton log
    c.insets=new Insets(20,20,0,10);
    this.panelMain.add(sub, c);
    sub.addActionListener((event)->super.control.sub());
    
    c.gridy=1;//en bas des deux autres boutons
    c.anchor=GridBagConstraints.LINE_END;//en fin de ligne
    c.insets=new Insets(50,0,10,10);
    this.panelMain.add(help, c);
    help.addActionListener((event)->super.control.help());
    
    this.setLayout(new BorderLayout());
    this.add(panelMain);
    this.pack();//recadrage
  }
  
  void printRule(){
    //le label qu on va afficher
    String text="<html><p align=\"justify\" style=\"width:"+(panelMain.getWidth()-30)+"\">"
      +"Le but du jeu est de sauver tous les animaux bloques en haut des blocs de cubes. "
      + "Pour cela, vous devez eliminer des blocs de meme couleur pour faire descendre les animaux, "
      + "jusqu à arriver à sauver tout le monde. Vous pouvez personnaliser des combos "
      + "et creer des resultats differents, à vous de jouer !</p><br>"
      +"<p style=\"font-size:7px\"><i><center>Cliquez n importe où sur la fenetre pour retourner au menu.</center></i></p>"
      +"</html>";
    JLabel rule=new JLabel(text);
    
    //le nouveau panneau qu on va afficher
    JPanel panelR=new JPanel();
    panelR.setLayout(new BorderLayout());
    panelR.add(rule);
    panelR.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//marges
    
    this.remove(panelMain);//on le remettra apres avoir clique sur la souris
    this.add(panelR);
    SwingUtilities.updateComponentTreeUI(ViewHomePage.this);//rafraichir la fenetre
    
    panelR.addMouseListener(new java.awt.event.MouseAdapter(){
      public void mouseClicked(MouseEvent e){
        ViewHomePage.this.remove(panelR);
        ViewHomePage.this.add(panelMain);
        SwingUtilities.updateComponentTreeUI(ViewHomePage.this);//rafraichir la fenetre
      }
    });
  }
}