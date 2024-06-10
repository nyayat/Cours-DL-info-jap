import java.awt.*;
import javax.swing.*;

public class ViewEndGame extends View{
  private JButton sommaire=new JButton("Sommaire");
  private JLabel endMessage;
  private JPanel panBut=new JPanel();//panneau de boutons
    
  ViewEndGame(Player p, int n, int h, boolean victory){//n=numero du niveau debloque
    super(p);
    panBut.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));//marges horizontales et verticales
    panBut.add(sommaire);//bouton retour au sommaire
    sommaire.addActionListener((event)->super.control.sub(p));
    
    //autres boutons a ajouter et message de fin different
    if(victory) this.victory(p, n, h);
    else this.defeat(n, h);
    
    JPanel panEnd=new JPanel();
    panEnd.setBounds(42, 232, 400, 74);
    panEnd.setBorder(BorderFactory.createLineBorder(Color.black));//contour du message
    panEnd.add(endMessage, BorderLayout.BEFORE_FIRST_LINE);
    panEnd.add(panBut, BorderLayout.CENTER);
    this.add(panEnd);
  }
  
  void victory(Player p, int n, int h){
    if(n<6){//5 est le dernier niveau possible
      this.endMessage=new JLabel("<html><center>Felicitations, vous pouvez passer au niveau suivant !</center></html>");
      if(h>0){
        JButton next=new JButton("Continuer");
        panBut.add(next);//bouton niveau suivant
        next.addActionListener((event)->super.control.playLevel(n, p.getHeart()));
      }
    }
    else endMessage=new JLabel("<html><center>Felicitations, tous les niveaux sont termines ! La suite pour bientôt :D</center></html>");
  }
  
  void defeat(int n, int h){
    this.endMessage=new JLabel("<html><center>Oh ! Il ne reste plus aucun mouvement possible !</center></html>");
    if(h>0){
      JButton retry=new JButton("Reessayer");
      panBut.add(retry);
      retry.addActionListener((event)->super.control.playLevel(n, h));
    }
  }
}