// Time-stamp: <PlusOuMoins.java   4 Mar 2005 09:19:10>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Exemple PlusOuMoins d'après J. Bertel
 * @version 1.0
 */
class PlusOuMoins extends JFrame {
    final int width  = 150;	// Largeur de la fenêtre
    final int height = 20;	// Hauteur de la fenêtre
     public PlusOuMoins() {
	// Constructeur avec titre
	super("Plus ou moins");
	// Dimension de la fenêtre
	setSize(width, height);
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Création du label
	ListenerLabel label = new ListenerLabel();
	// Création des deux Boutons écoutés par label
	JButton plusButton = new IncrButton("+", +1);
	plusButton.addActionListener(label);
	JButton moinsButton = new IncrButton("-", -1);
	moinsButton.addActionListener(label);
	// Mise en place des éléments dans un panneau
	JPanel mainPanel = new JPanel(new BorderLayout());
	mainPanel.add(plusButton,BorderLayout.EAST );
	mainPanel.add(label,BorderLayout.CENTER );
	mainPanel.add(moinsButton, BorderLayout.WEST);
	// Mise en place du panneau comme contentPane de la fenêtre
	setContentPane(mainPanel);
    }

    public static void main(String [] args)
    {
	// Création de la fenêtre
	PlusOuMoins mainFrame = new PlusOuMoins();
	// Affichage de la fenêtre
	mainFrame.setVisible(true);
    }
}

/**
 * Bouton avec valeur incrémentale incorporée
 */
class IncrButton extends JButton {
    private int incr;		// Valeur de l'incrément
    IncrButton(String title, int incr) {
	// Constructeur avec titre
	super(title);
	// Initialisation de l'incrément
	this.incr = incr;
    }
    int getIncr() { return incr; }
}
    

/**
 * Étiquette écoutant les boutons 
 */
class ListenerLabel extends JLabel implements ActionListener {
    int value = 0;
    ListenerLabel () {
	// Constructeur avec titre
	// Il serait préférable d'écrire :
	// super(Integer.toString(value), JLabel.CENTER);
	// mais ce n'est pas possible car le champ value
	// ne peut pas être utilisé dans l'appel au 
	// constructeur de la classe mère.
	super("0", JLabel.CENTER);
    }
    public void actionPerformed(ActionEvent event) {
	IncrButton button = (IncrButton) event.getSource();
	value += button.getIncr();
	setText(Integer.toString(value));
    }
}
