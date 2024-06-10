//2.1
import javax.swing.*;
import java.awt.*;

public class Vue extends JFrame{
    //3.1
    protected JPanel panneauColore=new JPanel();
    //4.
    protected JLabel etiqCouleur=new JLabel();
    //6.
    protected JSlider rougeS=initialiseSlider();
    protected JSlider vertS=initialiseSlider();
    protected JSlider bleuS=initialiseSlider();
    protected JButton mem=new JButton("Mémoriser");
    protected JButton rap=new JButton("S'en rappeler");
    protected JButton comp=new JButton("Complémentaire");
    //8.
    protected Modele modele;
    //9.
    protected Controleur contr;
    
    Vue(){
        //2.
        this.setTitle("Palette");
        this.setSize(800, 600);
        //3.
        this.panneauColore.setBackground(Color.green);
        this.getContentPane().add(this.panneauColore);
        this.vertS.setValue(255);  //juste pour l'affichage au début
        //4.
        this.etiqCouleur.setText("Vert");
        //this.panneauColore.add(this.etiqCouleur);
        //5.
        this.panneauColore.setLayout(new BorderLayout());
        this.panneauColore.add(this.etiqCouleur, BorderLayout.CENTER);
        this.etiqCouleur.setHorizontalAlignment(JLabel.CENTER);
        //6.
        this.ex6();
        //8.
        modele=new Modele(0, 255, 0);  //avec getter pour être décent, mais long à écrire
        //9.
        contr=new Controleur(this);
        //11.
        rougeS.addChangeListener((event)->contr.sliderMoved());
        vertS.addChangeListener((event)->contr.sliderMoved());
        bleuS.addChangeListener((event)->contr.sliderMoved());
        //12.
        mem.addActionListener((event)->contr.memoriser());
        rap.addActionListener((event)->contr.rappel());
        comp.addActionListener((event)->contr.complementer());
        //2.4
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    //6.
    JSlider initialiseSlider(){
        JSlider res=new JSlider(0, 255, 0);
        res.setMinorTickSpacing(5);
        res.setMajorTickSpacing(50);
        res.setPaintTicks(true);
        res.setPaintLabels(true);
        return res;
    }
    
    void ex6(){
        //6.1
        JPanel panneauChoix=new JPanel();
        this.getContentPane().add(panneauChoix);
        //6.2
        this.getContentPane().setLayout(new GridLayout());
        //6.3
        panneauChoix.setLayout(new BorderLayout());
        JPanel slid=new JPanel();
        slid.setLayout(new GridLayout(3, 1));
        slid.add(rougeS);
        slid.add(vertS);
        slid.add(bleuS);
        panneauChoix.add(slid);
        
        JPanel butt=new JPanel();
        butt.setLayout(new GridLayout(1, 3));
        butt.add(mem);
        butt.add(rap);
        butt.add(comp);
        panneauChoix.add(butt, BorderLayout.SOUTH);
    }
    
    //8.
    void miseAJour(){
        Color color=new Color(modele.r, modele.v, modele.b);
        this.panneauColore.setBackground(color);
        //this.etiqCouleur.setText(color.toString());
        
        //Bonus
        String hex=toHex(modele.r, modele.v, modele.b);
        this.etiqCouleur.setText(hex);
    }
    
    //Bonus
    String toHex(int r, int v, int b){
        String res="#";
        res+=(Integer.toHexString(r).length()<2)?"0":"";  //pour un affiche plus décent
        res+=Integer.toHexString(r);
        res+=(Integer.toHexString(v).length()<2)?"0":"";
        res+=Integer.toHexString(v);
        res+=(Integer.toHexString(b).length()<2)?"0":"";
        res+=Integer.toHexString(b);
        return res;
    }
}