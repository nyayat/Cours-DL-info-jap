// Time-stamp: <SelectionMode.java  10 Aug 2005 10:17:55>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Modes de sélection d'une liste
 * @author O. Carton
 * @version 1.0
 */
class SelectionMode extends JFrame {
    public SelectionMode () {
	// Titre de la fenêtre
	setTitle("Essais des modes de selection");
	// Action à faire lorsque la fenêtre est fermée par 
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Paneau principal
	Container contentPane = getContentPane();

	// Liste de selection
	// Tableau de prénoms féminins pour les valeurs de la liste
	String[] data = { "Agathe", "Alice", "Aude", "Barbara", "Clarisse", 
			  "Clémence", "Céline", "Isabelle", "Léa", "Marion", 
			  "Natacha", "Nathalie", "Sylvie", "Valérie", 
			  "Véronique" };
	                    
	// Liste 
	final JList list = new JList(data);
	// avec une barre de défilement
	JScrollPane listScrollPane = new JScrollPane(list);

	// Panneau d'affichage 
	final JTextArea textArea = new JTextArea(20, 10);
	// avec une barre de défilement
	JScrollPane textScrollPane = new JScrollPane(textArea);

	// Panneau pour séparer la liste et l'affichage
	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
					      listScrollPane, textScrollPane);
	// Ajout du tout dans le panneau principal
	contentPane.add(splitPane, BorderLayout.CENTER);
					      
	// Tableau de contrôle
	JPanel controlPanel = new JPanel();
	// Ajout du panneau de contrôle en haut du panneau principal
	contentPane.add(controlPanel, BorderLayout.NORTH);
	// Bouton d'ajout
	final JButton printButton = new JButton("Print");
	// Bouton inactif au départ
	printButton.setEnabled(false);
	// Ajout du bouton dans le panneau de contrôle
	controlPanel.add(printButton);
	// Partie contrôleur du bouton
	printButton.addActionListener(new ActionListener () {
		public void actionPerformed(ActionEvent e) {
		    // Valeurs sélectionnées
		    Object[] values = list.getSelectedValues();
		    // Affichage dans le  panneau de texte 
		    // des valeurs selectionnées 
		    for(int i = 0; i < values.length; i++)
			textArea.append(values[i].toString() + "\n");
		    // Ajout d'une ligne de séparation
		    textArea.append("--------------\n");
		}
	    });
	
	// Écoute des changements de sélection pour changer l'activation
	// du bouton print
	ListSelectionModel lsm = list.getSelectionModel();
	lsm.addListSelectionListener(new ListSelectionListener () {
		public void valueChanged(ListSelectionEvent e) {
		    // Modèle de sélection de la table qui a émis l'événement
		    ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		    printButton.setEnabled(!lsm.isSelectionEmpty());
		}
	    });

	// Menu pour choisir le type de sélection
	// Tableau de chaînes pour le menu
	String[] selectionStrings = {"Simple selection", 
				      "Interval selection", 
				     "Multiple selection" }; 
	// Tableau des modes dans le même ordre que les chaînes
	final int[] selectionModes = {
	    ListSelectionModel.SINGLE_SELECTION,
	    ListSelectionModel.SINGLE_INTERVAL_SELECTION,
	    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION };

	// Implémentation du menu par une Combo-Box
	final JComboBox comboBox = new JComboBox(selectionStrings);
	comboBox.setSelectedIndex(2); // Multiple selection
	// Ajout du menu dans le panneau de contrôle
	controlPanel.add(comboBox);
	// Partie contrôleur du menu
	comboBox.addActionListener(new ActionListener () {
		public void actionPerformed(ActionEvent event) {
		    // Changement du type sélection
		    int mode = selectionModes[comboBox.getSelectedIndex()];
		    list.setSelectionMode(mode);
		}    
	    });
    }
    public static void main(String [] args)
    {
	// Création de la fenêtre
	SelectionMode view = new SelectionMode();
	// Mise en place 
	view.pack();
	// Affichage de la fenêtre
	view.setVisible(true);
    }
}
