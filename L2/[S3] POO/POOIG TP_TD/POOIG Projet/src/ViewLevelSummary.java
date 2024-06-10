import java.awt.*;
import javax.swing.*;

public class ViewLevelSummary extends View{
  private JPanel panelLevSum=new JPanel();
  
  ViewLevelSummary(Player p){
    super(p);
    this.setTitle("Sommaire");
    
    //affichage du nombre de coeurs
    JPanel panelHeart=new JPanel();
    panelHeart.setLayout(new GridLayout(1, 7));//7 colonnes
    JPanel heart=new JPanel();
    heart.add(new JLabel("<html><center>Coeur : "+p.getHeart()+"</center></html"));
    for(int i=0; i<7; i++) panelHeart.add(new JLabel(""));//pour la presentation
    panelHeart.add(heart);//tout a droite de la ligne
    
    //affichage question du choix du niveau
    JPanel panelQuestion=new JPanel();
    panelQuestion.setLayout(new FlowLayout());
    JLabel question=new JLabel("<html><br><center>Vos niveaux debloques : </center></html>");
    panelQuestion.add(question);
    
    //en-tete de l affichage
    JPanel panelHead=new JPanel();
    panelHead.setLayout(new GridLayout(2, 1));//2 lignes
    panelHead.add(panelHeart);
    panelHead.add(panelQuestion);
    
    //ajout des niveaux debloques dans le panneau des niveaux
    panelLevSum.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
    Level[]temp=p.getGame();
    int i=0;
    while(i<temp.length && temp[i].getUnlocked()){
      addButton(++i, p.getHeart());//decalage : temp[0] contient le niveau 1, ...
    }
    
    //bas de page
    JPanel endPage=new JPanel();
    endPage.setLayout(new GridLayout(2,1));
    JButton save=new JButton("Sauvegarder l avancee");
    save.addActionListener((event)->super.control.save());
    JButton ret=new JButton("Retourner a la page d accueil");
    ret.addActionListener((event)->super.control.IG());
    endPage.add(save);
    endPage.add(ret);
    
    this.setLayout(new BorderLayout());
    this.add(panelHead, BorderLayout.BEFORE_FIRST_LINE);
    this.add(panelLevSum, BorderLayout.CENTER);
    this.add(endPage, BorderLayout.SOUTH);
  }
  
  void addButton(int n, int h){
    JButton b=new JButton(Integer.toString(n));
    b.addActionListener((event)->super.control.playLevel(n, h));
    this.panelLevSum.add(b);
  }
}