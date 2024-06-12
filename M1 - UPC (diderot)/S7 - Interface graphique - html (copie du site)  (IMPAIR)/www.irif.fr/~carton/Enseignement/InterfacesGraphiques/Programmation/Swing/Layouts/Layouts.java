// Time-stamp: <Layouts.java  24 mar 2003 15:19:48>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Application pour tester les gestionnaires de disposition
 * @author O. Carton
 * @version 1.0
 */
class Layouts extends JFrame {
    public Layouts () {
	// Titre
	setTitle("Gestionnaires de disposition");
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Panneau principal
	JPanel mainPanel = new JPanel(new BorderLayout());

	// Panneau pour les boutons et le menu
	JPanel buttonPanel = new JPanel();
	// Ajout du panneau de boutons en haut du panneau principal
	mainPanel.add(buttonPanel, BorderLayout.NORTH);

	// Panneau dynamique
	// Cette variable de classe est finale car elle est utilisée
	// dans les classes anonymes implémentant l'interface 
	// ActionListener.
	final DynamicPanel dynamicPanel = new DynamicPanel();
	// Ajout d'un bord au panneau dynamique
	dynamicPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	// Panneau vertical
	JPanel columnPanel = new JPanel();
	// Postionnement vertical
	columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));
	// Glue pour le haut
	columnPanel.add(Box.createVerticalGlue());
	// Panneau horizontal
	JPanel linePanel = new JPanel();
	linePanel.setLayout(new BoxLayout(linePanel, BoxLayout.X_AXIS));
	// Glue pour la gauche
	linePanel.add(Box.createHorizontalGlue());
	// Ajout du panneau dynamique au panneau horizontal 
	linePanel.add(dynamicPanel);
	// Glue pour la droite
	linePanel.add(Box.createHorizontalGlue());
	// Ajout du panneau horizontal au panneau vertical
	columnPanel.add(linePanel);
	// Glue pour le bas
	columnPanel.add(Box.createVerticalGlue());
	// Ajout du panneau vertical au panneau principal
	mainPanel.add(columnPanel, BorderLayout.CENTER);

	// Bouton d'ajout
	JButton addButton = new JButton("Add");
	// Ajout du bouton dans le panneau de boutons
	buttonPanel.add(addButton);
	// Partie contrôleur du bouton
	addButton.addActionListener(new ActionListener () {
		public void actionPerformed(ActionEvent e) {
		    dynamicPanel.add();
		}
	    });

	// Bouton de Reset
	JButton resetButton = new JButton("Reset");
	// Ajout du bouton dans le panneau de boutons
	buttonPanel.add(resetButton);
	// Partie contrôleur du bouton
	resetButton.addActionListener(new ActionListener () {
		public void actionPerformed(ActionEvent e) {
		    dynamicPanel.reset();
		}
	    });

	// Menu pour choisir le gestionnaire de disposition
	// Chaînes pour le menu
	String[] layoutStrings = {"FlowLayout (left)", 
				  "FlowLayout (center)", 
				  "FlowLayout (right)", 
				  "BorderLayout", 
				  "GridLayout (3 lines)",
				  "GridLayout (3 columns)",
				  "BoxLayout (line)",
                                  "BoxLayout (column)" };
	// Implémentation du menu par une Combo-Box
	final JComboBox comboBox = new JComboBox(layoutStrings);
	comboBox.setSelectedIndex(1); // FlowLayout (centre)
	// Ajout du menu dans le panneau de boutons
	buttonPanel.add(comboBox);
	// Partie contrôleur du menu
	comboBox.addActionListener(new ActionListener () {
		// Cette méthode change le gestionnaire de disposition du 
		// panneau dynamique en fonction du choix de l'utilisateur 
		// dans le menu.
		// Un nouveau gestionnaire est créé à chaque fois que 
		// le menu est utilisé. 
		public void actionPerformed(ActionEvent event) {
		    LayoutManager layout = null; // Layout sélectionné
		    switch (comboBox.getSelectedIndex()) {
		    case 0: 	// FlowLayout (gauche)
			layout = new FlowLayout(FlowLayout.LEFT);
			break;
			
		    case 1:	// FlowLayout (centre)
			layout = new FlowLayout(FlowLayout.CENTER);
			break;
			
		    case 2:	// FlowLayout (droite)
			layout = new FlowLayout(FlowLayout.RIGHT);
			break;
			
		    case 3:	// BorderLayout
			layout = new BorderLayout();
			break;
			
		    case 4:	// GridLayout (3 lignes)
			layout = new GridLayout(3,0,5,3);
			break;

		    case 5:	// GridLayout (3 colonnes)
			layout = new GridLayout(0,3,5,3);
			break;
			
		    case 6:	// BoxLayout (ligne)
			layout = new BoxLayout(dynamicPanel, BoxLayout.X_AXIS);
			break;

		    case 7:	// BoxLayout (colonne)
			layout = new BoxLayout(dynamicPanel, BoxLayout.Y_AXIS);
			break;
		    }
		    // Force le panneau à repositionner les boutons.
		    dynamicPanel.setLayout(layout);
		    dynamicPanel.doLayout();
		    dynamicPanel.revalidate();
		}
	    });
			       
	// Mise en place du panneau principal dans la fenêtre
	setContentPane(mainPanel);

    }
    public static void main(String [] args)
    {
	// Création de la fenêtre
	Layouts ly = new Layouts();
	// Affichage de la fenêtre
	ly.pack();
	ly.setVisible(true);
    }
}

/** Panneau dynamique */
class DynamicPanel extends JPanel implements ActionListener {
    final int width  = 300;	// Largeur souhaitée du panneau
    final int height = 200;	// Hauteur souhaitée du panneau
    int buttonsNumber = 0;	// Compteur de boutons dans le panneau
    JPopupMenu popup;		// Popup Menu des boutons
    // Actions possibles sur les boutons du panneau dynamique
    final String ENLARGE = "ENLARGE"; // Augmenter la taille d'un bouton
    final String REDUCE  = "REDUCE";  // Diminuer la taille d'un bouton
    final String REMOVE  = "REMOVE";  // Supprimer un bouton
    // Constructeur
    public DynamicPanel() {
	// Dimensions souhaitées du panneau
	setPreferredSize(new Dimension(width, height));
	// Création du popup menu et des entrées
	popup = new JPopupMenu();
	JMenuItem enlargeItem = new JMenuItem("Enlarge");
	enlargeItem.setActionCommand(ENLARGE);
	enlargeItem.addActionListener(this);
	popup.add(enlargeItem);
	JMenuItem reduceItem = new JMenuItem("Reduce");
	reduceItem.setActionCommand(REDUCE);
	reduceItem.addActionListener(this);
	popup.add(reduceItem);
	JMenuItem removeItem = new JMenuItem("Remove");
	removeItem.setActionCommand(REMOVE);
	removeItem.addActionListener(this);
	popup.add(removeItem);
    }
    // Ajout d'un nouveau bouton au panneau
    void add() {
	// Création d'un bouton
	JButton button = new JButton(Integer.toString(buttonsNumber));
	// Le panneau dynamique reçoit les événements de ce bouton
	// button.addActionListener(this);
	button.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    popup.show(e.getComponent(), e.getX(), e.getY());
		}
		
		public void mouseReleased(MouseEvent e) {
		    popup.show(e.getComponent(), e.getX(), e.getY());
		}
	    });

	// Ajout du bouton au panneau dynamique
	// Les cinq premiers boutons sont ajoutés avec les contraintes
	// NORTH, WEST, CENTER, EAST et SOUTH pour qu'ils se placent
	// correctement lorsque le gestionnaire est un BorderLayout.
	// Sinon, ces contraintes n'ont aucun effet.
	switch(buttonsNumber) {
	case 0:
	    add(button, BorderLayout.NORTH);
	    break;
	case 1:
	    add(button, BorderLayout.WEST);
	    break;
	case 2:
	    add(button, BorderLayout.CENTER);
	    break;
	case 3:
	    add(button, BorderLayout.EAST);
	    break;
	case 4:
	    add(button, BorderLayout.SOUTH);
	    break;
	default:
	    // Ajout sans contrainte par défaut
	    add(button);
	}
	// Incrémentation du compteur de boutons
	buttonsNumber++;
	// Redessin du panneau
	revalidate();
	repaint();
    }
    /* Multiplication par 2 des dimensions */
    private void multiplyBy2(Dimension from, Dimension to) {
	    to.height = from.height * 2;
	    to.width  = from.width  * 2;
    }
    /* Division par 2 des dimensions */
    private void divideBy2(Dimension from, Dimension to) {
	    to.height = from.height / 2;
	    to.width  = from.width  / 2;
    }
    // Dimensions multipliées par 2
    private void enlarge(JComponent c) {
	// Dimension actuelle
	Dimension dim = c.getPreferredSize();
	// Dimension minimum
	Dimension min = c.isMinimumSizeSet() ? 
	    c.getMinimumSize() : new Dimension();
	// Dimension preférée
	Dimension pre = c.isPreferredSizeSet() ? 
	    c.getPreferredSize() : new Dimension();
	// Dimension maximum
	Dimension max = c.isMaximumSizeSet() ? 
	    c.getMaximumSize() : new Dimension();
	// Calcul
	min.setSize(dim);
	multiplyBy2(dim, pre);
	multiplyBy2(pre, max);
	// Mise en place
	c.setPreferredSize(pre);
	c.setMinimumSize(min);
	c.setMaximumSize(max);
    }
    // Dimensions divisées par 2
    private void reduce(JComponent c) {
	// Dimension actuelle
	Dimension dim = c.getPreferredSize();
	// Dimension preférée
	Dimension pre = c.isPreferredSizeSet() ? 
	    c.getPreferredSize() : new Dimension();
	// Dimension minimum
	Dimension min = c.isMinimumSizeSet() ? 
	    c.getMinimumSize() : new Dimension();
	// Dimension maximum
	Dimension max = c.isMaximumSizeSet() ? 
	    c.getMaximumSize() : new Dimension();
	// Calculs
	max.setSize(dim);
	divideBy2(dim, pre);
	divideBy2(pre, min);
	// Mise en place
	c.setPreferredSize(pre);
	c.setMinimumSize(min);
	c.setMaximumSize(max);
    }
    // Supression de tous les boutons du panneau
    public void reset() {
	// Suppression de tous les boutons
	removeAll();
	buttonsNumber = 0;
	// Redessin du panneau
	revalidate();
	repaint();
    }
    // Écoute des entrées du popup menu
    public void actionPerformed(ActionEvent event) {
	// Bouton source du popup menu
	JButton source = (JButton) popup.getInvoker();
	// Action à effectuer sur le bouton
	String action = event.getActionCommand();
	// Action effectuée
	if (action == ENLARGE) 
	    enlarge(source);
	else if (action == REDUCE)
	    reduce(source);
	else if (action == REMOVE)
	    remove(source);
	// Redessin du panneau
	revalidate();
	repaint();
    }
}
