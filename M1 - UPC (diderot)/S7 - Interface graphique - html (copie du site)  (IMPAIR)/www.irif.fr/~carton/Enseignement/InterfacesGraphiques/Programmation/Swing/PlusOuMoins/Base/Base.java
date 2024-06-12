// Time-stamp: <Base.java   3 Mar 2005 10:45:00>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Exemple PlusOuMoins en Model/View/Controler avec le choix de la base
 * @version 1.0
 */
class Base {
    public Base() {
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
	Base base = new Base();
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
    int base = 10;		// Base d'affichage
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
	// Création d'une combobox pour le choix de la base
	JComboBox comboBox = new JComboBox();
	// Ajout des quatre bases 
	// Attention, l'ordre est implicitement utilisé par le contrôleur
	comboBox.addItem("Décimal");
	comboBox.addItem("Hexadécimal");
	comboBox.addItem("Octal");
	comboBox.addItem("Binaire");
	// Chaîne encoyée dans l'événement ActionEvent.
	// C'est ainsi que le contrôleur reconnaît que l'événement 
	// provient de la combobox
	comboBox.setActionCommand("Combo");
	// La combobox est écoutée par le contrôleur
	comboBox.addActionListener(controler);
	// Mise en place des éléments dans un panneau
	JPanel mainPanel = (JPanel) getContentPane();
	mainPanel.setLayout(new BorderLayout());
	mainPanel.add(comboBox, BorderLayout.NORTH);
	mainPanel.add(plusButton,BorderLayout.EAST );
	mainPanel.add(label,BorderLayout.CENTER );
	mainPanel.add(moinsButton, BorderLayout.WEST);
    }
    // Mise à jour de l'affichage à partir des données du modèle
    void update() {
	label.setText(Integer.toString(model.getValue(), base));
    }
    // Changement de la base d'affichage
    void setBase(int base) {
	this.base = base;
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
	// 
	if (event.getActionCommand().equals("Combo")) {
	    // ComboBox qui a émis l'événement
	    JComboBox comboBox = (JComboBox) event.getSource();
	    // On utlise l'ordre implicite des bases dans la combobox :
	    // 0 décimal, 1 hexadécimal, 2 octal, 3 binaire.
	    switch(comboBox.getSelectedIndex()) {
	    case 1:
		view.setBase(16);
		break;
	    case 2:
		view.setBase(8);
		break;
	    case 3:
		view.setBase(2);
		break;
	    default:
		view.setBase(10);
		break;
	    }   
	} else {
	    // Bouton émetteur de l'événement
	    IncrButton button = (IncrButton) event.getSource();
	    // Mise à jour des données
	    model.incrValue(button.getIncr());
	    // Force la vue à être conforme aux données
	}
	view.update();
    }
}
