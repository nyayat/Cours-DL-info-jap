import javax.swing.JFrame;

public class View extends JFrame{
  protected Model model;
  protected Control control;
    
  View(Player p){//pour LevelSummary et PlayingLevel
    this.setVisible(true);
    this.setSize(490, 600);//taille de toutes les fenetres par defaut
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    model=new Model(this, p);
    control=new Control(this);
    this.setLayout(null);//on personnalisera le layout pour chaque sous-classe
    this.setResizable(false);//on ne pourra pas changer la taille de la fenetre
  }
  
  View(){//pour ChoiceView et HomePage
    this(null);
  }
}