// Time-stamp: <Target.java  20 Jun 2005 10:57:27>

// Awt
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
// Swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
// Swing.event
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Utilisation du GridBagLayout et de deux curseurs pour faire une cible
 * @author O. Carton
 * @version 1.0
 */
class Target extends JFrame {
    public Target() {
	setTitle("Exercice 4");
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	// Panneau cible
	final TargetPanel target = new TargetPanel();
	target.setPreferredSize(new Dimension(100, 100));
	// Curseur horizontal
	final JSlider cursorx = new JSlider(0,100);
	cursorx.setMajorTickSpacing(25);
	cursorx.setMinorTickSpacing(5);
	cursorx.setPaintTicks(true);
	cursorx.setPaintLabels(true);
	// Écouteur du curseur horizontal
	cursorx.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent event) {
		    target.setX(cursorx.getValue());
		}
	    });
	// Valeur initiale
	cursorx.setValue(50);
	target.setX(cursorx.getValue());
	// Curseur vertical
	final JSlider cursory = new JSlider(0,100);
	cursory.setOrientation(JSlider.VERTICAL);
	cursory.setInverted(true);
	cursory.setMajorTickSpacing(25);
	cursory.setMinorTickSpacing(5);
	cursory.setPaintTicks(true);
	cursory.setPaintLabels(true);
	// Écouteur du curseur vertical
	cursory.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent event) {
		    target.setY(cursory.getValue());
		}
	    });
	// Valeur initiale
	cursory.setValue(50);
	target.setY(cursory.getValue());
	
	// Mise en place
	Container contentPane = getContentPane();
	// Problème avec un BorderLayout
	// contentPane.add(target, BorderLayout.CENTER);
	// contentPane.add(cursorx, BorderLayout.SOUTH);
	// contentPane.add(cursory, BorderLayout.EAST);
	//  La disposition recherchée est la suivante
	//    
	//            0    1  
	//       ------------      
	//       |        | |
	//    0  |   T    |V|
	//       |        | |
	//       |--------|-|
	//    1  |   H    | |
	//       ------------
	//
	contentPane.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	// Pour tous les éléments
	gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.CENTER;
	gbc.weightx = 1;
	gbc.weighty = 1;
	gbc.gridwidth = 1;
	// Panneau cible
	gbc.gridx = 0;     
	contentPane.add(target, gbc);
	// Curseur vertical
	gbc.gridx = 1;     
	contentPane.add(cursory, gbc);
	// Curseur horizontal
	gbc.gridx = 0;    
	gbc.gridy = 1;
	contentPane.add(cursorx, gbc);
    }
    class TargetPanel extends JPanel {
	// Coordonnées absolues : 0..100
	int xabs;			// Abscisse
	int yabs;			// Ordonnée
	/* Changement de l'abscisse absolue */
	void setX(int xabs) {
	    this.xabs = xabs;
	    repaint();
	}
	/* Changement de l'ordonnée absolue */
	void setY(int yabs) { 
	    this.yabs = yabs;
	    repaint();
	}
	/* Dessin du panneau */
	public void paintComponent(Graphics g) {
	    // Appel de la méthode de la classe JPanel
	    super.paintComponent(g);
	    // Largeur et hauteur du panneau
	    int width = getWidth();	
	    int height = getHeight(); 
	    // Coordonnées relatives au panneau
	    int x = (xabs*width)/100;
	    int y = (yabs*height)/100;
	    // Dessin d'une ligne verticale en x
	    g.drawLine(x, 0, x, height);
	    // Dessin d'une ligne verticale en y
	    g.drawLine(0, y, width, y);
	}
    }
    public static void main(String [] args)
    {
	Target view = new Target();
	view.pack();
	view.setVisible(true);
    }
}
