// Time-stamp: <Bases.java   6 Jul 2003 13:08:47>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Convertisseur entre bases 
 * @author O. Carton
 * @version 1.0
 */
class Bases {
    // Architecture Modèle/Vue/Contrôleur
    // ATTENTION : le contrôleur doit être construit avant la vue
    // pour qu'il puisse être écouteur des composants de la vue.
    private Model model = new Model();	 
    private Controller controller = new Controller(); 
    private View view = new View();
    
    public Bases() {
	// Mise en place 
	view.pack();
	// Affichage de la vue
	view.setVisible(true);
    }

    public static void main(String [] args)
    {
	// Création 
	Bases bases = new Bases();
    }

    /** Vue regroupant plusieurs vues partielles */
    class View extends JFrame {
	// Vues partielles dans différentes bases
	BaseView binView = new BaseView(2);
	BaseView octView = new BaseView(8);
	BaseView decView = new BaseView(10);
	BaseView hexView = new BaseView(16);
	// Vue partielle avec des curseurs
	SliderView sliderView = new SliderView();
	// Vue partielle avec des boutons à cocher
	CheckBoxView checkBoxView = new CheckBoxView();

	// Construction de la vue globale
	View() {
	    // Constructeur avec titre
	    super("Conversion de bases");
	    // Action à faire lorsque la fenêtre est fermée.
	    setDefaultCloseOperation(EXIT_ON_CLOSE);

	    // Paneau des étiquettes
	    JPanel labelPanel = new JPanel(new GridLayout(0,1));
	    labelPanel.add(new JLabel("Binaire :", JLabel.LEFT));
	    labelPanel.add(new JLabel("Octal :", JLabel.LEFT));
	    labelPanel.add(new JLabel("Décimal :", JLabel.LEFT));
	    labelPanel.add(new JLabel("Hexadécimal :", JLabel.LEFT));
		
	    // Paneau des zones de saisies  
	    JPanel fieldPanel = new JPanel(new GridLayout(0,1));
	    fieldPanel.add(binView);
	    fieldPanel.add(octView);
	    fieldPanel.add(decView);
	    fieldPanel.add(hexView);

	    // Panneau avec les vues par curseurs et boutons à cocher
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setLayout(new BoxLayout(bottomPanel, 
						BoxLayout.Y_AXIS));
	    bottomPanel.add(checkBoxView);
	    // Espace vertical entre les deux vues
	    bottomPanel.add(Box.createVerticalStrut(10));
	    bottomPanel.add(sliderView);
		
	    // Mise en place des éléments dans un panneau
	    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
	    // Bords du panneau principal
	    mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    mainPanel.add(labelPanel, BorderLayout.WEST);
	    mainPanel.add(fieldPanel, BorderLayout.CENTER);
	    mainPanel.add(bottomPanel, BorderLayout.SOUTH);
	    
	    // Mise en place du panneau principal
	    setContentPane(mainPanel);
	}
	// Mise à jour de la vue
	void update() {
	    // Mise à jour des différentes vues partielles
	    binView.update();
	    octView.update();
	    decView.update();
	    hexView.update();
	    sliderView.update();
	    checkBoxView.update();
	}
	/** Vue partielle dans une base */
	class BaseView extends JTextField implements ActionListener {
	    int base;		// Base d'affichage et de saisie
	    BaseView(int base) {
		// 16 colonnes au départ
		super(16);	
		this.base = base;
		// Ce composant s'écoute lui-même
		addActionListener(this);
		// Mise à jour de l'affichage
		update();
	    }
	    public void actionPerformed(ActionEvent event) {
		// Tentative de décoder la saisie
		try {
		    // Notification au contrôleur de la saisie
		    controller.notify(Integer.parseInt(getText(), base));
		}
		// Erreur de format
		catch (NumberFormatException exception) {
		    // Signal sonore
		    Toolkit.getDefaultToolkit().beep(); 
		}
	    }
	    // Mise à jour de la vue
	    void update() {
		// Mise à jour de l'affichage
		setText(Integer.toString(model.getValue(), base));
	    }
	}
	/** Vue partielle avec deux curseurs */
	class SliderView extends JPanel implements ChangeListener {
	    // Ce booléen bloque l'envoie des événements lorsque
	    // ces événements sont dûs à une mise à jour des curseurs
	    private boolean eventEnable = false;
	    private JSlider lowByteSlider; // Poids faible
	    private JSlider higByteSlider; // Poids fort
	    // Construction d'un curseur pour un octet
	    private JSlider getSlider() {
		// Construction du curseur
		JSlider slider = new JSlider(0, 255);
		// Affichage du 0 et du 255
		slider.setMajorTickSpacing(255);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		// Écoute du curseur par la vue partielle
		slider.addChangeListener(this);
		return slider;
	    }
	    SliderView() {
		// Gestionnaire de disposition
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Curseur de l'octet de poids faible
		lowByteSlider = getSlider();
		// Bord avec un titre
		lowByteSlider.setBorder(
		  BorderFactory.createTitledBorder("Octet de poids faible"));
		// Mise en place du curseur dans le panneau
		add(lowByteSlider);
		
		// Curseur de l'octet de poids fort
		higByteSlider = getSlider();
		// Bord avec un titre
		higByteSlider.setBorder(
		  BorderFactory.createTitledBorder("Octet de poids fort"));
		// Mise en place du curseur dans le panneau
		add(higByteSlider);

		// Mise à jour de l'affichage
		update();
	    }
	    // Écoute des deux curseurs
	    public void stateChanged(ChangeEvent event) {
		if (eventEnable) {
		    // La valeur calculée à partir des deux curseurs
		    // est envoyée au contrôleur
		    controller.notify(lowByteSlider.getValue() +
				      higByteSlider.getValue()*256
				      );
		}
	    }
	    // Mise à jour des deux curseurs
	    void update() {
		// Valeur à visualiser
		int value = model.getValue();
		eventEnable = false; // Bloquage des événements
		lowByteSlider.setValue(value%256); // Poids faible
		higByteSlider.setValue(value/256); // Poids fort
		eventEnable = true;  // Débloquage des événements
	    }
	}
	/** Vue partielle avec des boutons à cocher */
	class CheckBoxView extends JPanel implements ActionListener {
	    // Nombre de boutons dans la vue
	    final int checkBoxNbr = 16;	
	    // Création du tableau de boutons
	    // Le bouton à la position i dans le tableau correspond
	    // au bit i de l'entier, c'est-à-dire celui de valeur 2^i.
	    JCheckBox[] checkBoxList = new JCheckBox[checkBoxNbr];
	    CheckBoxView() {
		// Gestionnaire de disposition : 2 lignes
		// 1 ligne pour chaque octet
		setLayout(new GridLayout(2,0));

		// Panneau contenant les boutons de l'octet de poids faible
		// Boutons sur une seule ligne
		JPanel lowBytePanel = new JPanel(new GridLayout(1,0));
		add(lowBytePanel);
		// Bord avec titre
		lowBytePanel.setBorder(BorderFactory.createTitledBorder("Octet de poids faible"));

		// Panneau contenant les boutons de l'octet de poids fort
		// Boutons sur une seule ligne
		JPanel higBytePanel = new JPanel(new GridLayout(1,0));
		add(higBytePanel);
		// Bord avec titre
		higBytePanel.setBorder(BorderFactory.createTitledBorder("Octet de poids fort"));

		// Création des boutons en partant des bits de poids forts
		for(int i = checkBoxNbr-1; i >= 0; i--) {
		    // Création du bouton
		    checkBoxList[i] = new JCheckBox();
		    // La vue partielle écoute les boutons
		    checkBoxList[i].addActionListener(this);
		    // Ajout du bouton dans le panneau correspondant
		    (i < 8 ? lowBytePanel : higBytePanel).add(checkBoxList[i]);
		}
		// Mise à jour de la vue
		update();
	    }
	    public void actionPerformed(ActionEvent event) {
		// Calcul de la valeur
		// Parcours des boutons en partant des poids forts
		int value = 0;
		for(int i = checkBoxNbr-1; i >= 0; i--) {
		    value <<= 1;
		    if (checkBoxList[i].isSelected())
			value++;
		}
		// Envoi de la valeur au contrôleur
		controller.notify(value);
	    }
	    // Mise à jour de la vue
	    void update() {
		// Valeur à visualiser
		int value = model.getValue();
		// Parcours des boutons en partant des poids faibles
		for(int i = 0; i < checkBoxNbr; i++) {
		    // Bouton activé si le bit i vaut 1
		    checkBoxList[i].setSelected((value%2) == 1);
		    // Décalage des bits
		    value >>= 1;
		}
	    }
	}
    }

    /** Contrôleur */
    class Controller {
	void notify(int value) {
	    // Mise en place de la nouvelle valeur
	    model.setValue(value);
	    // Force la vue à se mettre à jour
	    view.update();
	}
    }
}

/** Modèle des données */
class Model {
    // Les données sont constituées d'un seul entier.
    private int value;		// Données du modèle
    Model(int value) { this.value = value; }
    Model() { this(0); }
    void setValue(int value) { this.value = value; }
    int getValue() { return value; }
}


