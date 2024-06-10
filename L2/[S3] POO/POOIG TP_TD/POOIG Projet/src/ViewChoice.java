import java.awt.*;
import javax.swing.*;

public class ViewChoice extends View{
  private JButton modeIG=new JButton("Vue graphique");
  private JButton modeTerm=new JButton("Vue textuelle");
  
  ViewChoice(){
      super();
      this.setTitle("Choix de la vue");
      this.setLayout(new FlowLayout());

      //ajout des deux boutons et des Listeners
      this.add(modeIG);
      this.add(modeTerm);
      modeIG.addActionListener((event)->super.control.IG());
      modeTerm.addActionListener((event)->super.control.shell());
      
      this.pack();//recadrage
  }
}