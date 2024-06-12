// Time-stamp: <Mvc.java  18 sep 2019 17:31:27>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Exemple PlusOuMoins en Model/View/Controler d'après J. Berstel
 * @version 1.0
 */
class Mvc {
    public Mvc() {
	// Création du modèle
	Model model = new Model();
	// Création du contrôleur
	Controler controler = new Controler(model);
	// Création de la vue
	View view = new View(model, controler);
	controler.setView(view);
	// Affichage de la vue
	view.setVisible(true);
    }

    public static void main(String [] args)
    {
	// Création 
	Mvc mvc = new Mvc();
    }
}

/**
 * Bouton avec valeur incrémentale
 */
class IncrButton extends JButton {
    private int incr;		// Valeur de l'incrément
    IncrButton(String title, int incr) {
	// Constructeur avec titre
	super(title);
	// Initialisation de l'incrément
	this.incr = incr;
    }
    // Retourne la valeur d'incrément
    int getIncr() { return incr; }
}

/**
 * Modèle contenant les données
 * Les données sont constituées d'un seul entier.
 */
class Model {
    private int value;		// Données du modèle
    Model(int value) { this.value = value; }
    Model() { this(0); }
    void incrValue(int incr) { value += incr; }
    int getValue() { return value; }
}

/**
 * Vue affichant les données
 */
class View extends JFrame {
    final int width  = 150;	// Largeur de la fenêtre
    final int height = 20;	// Hauteur de la fenêtre
    Model model;		// Modèle contenant les données
    JLabel label;		// Étiquette d'affichage
    View(Model model, Controler controler) {
	// Constructeur avec titre
	super("Plus ou moins");
	// Initialisation du modèle
	this.model = model;
	// Dimension de la fenêtre
	setSize(width, height);
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Création du champ d'affichage de la valeur
	label = new JLabel(Integer.toString(model.getValue()), JLabel.CENTER);
	// Création des deux Boutons écoutés par le contrôleur
	JButton plusButton = new IncrButton("+", +1);
	plusButton.addActionListener(controler);
	JButton moinsButton = new IncrButton("-", -1);
	moinsButton.addActionListener(controler);
	// Mise en place des éléments dans un panneau
	JPanel mainPanel = (JPanel) getContentPane();
	mainPanel.setLayout(new BorderLayout());
	mainPanel.add(plusButton,BorderLayout.EAST );
	mainPanel.add(label,BorderLayout.CENTER );
	mainPanel.add(moinsButton, BorderLayout.WEST);
    }
    // Mise à jour de l'affichage à partir des données du modèle
    void update() {
	label.setText(Integer.toString(model.getValue()));
    }
}

/**
 * Contrôleur 
 */
class Controler implements ActionListener {
    Model model;		// Modèle contenant les données 
    View view;			// Vue des données
    Controler(Model model) { this.model = model; }
    void setView(View view) { this.view = view; }
    // Action sur reception d'un événement
    public void actionPerformed(ActionEvent event) {
	// Bouton émetteur de l'événement
	IncrButton button = (IncrButton) event.getSource();
	// Mise à jour des données
	model.incrValue(button.getIncr());
	// Force la vue à être conforme aux données
	view.update();
    }
}
