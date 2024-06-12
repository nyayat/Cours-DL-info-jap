// -*- Coding: utf-8 -*-
// Time-stamp: <OneList.java   7 oct 2019 08:40:41>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Ajout et supression dans une JList
 * @author O. Carton
 * @version 1.0
 */
class OneList extends JFrame {
    public OneList () {
	// Titre de la fenêtre
	setTitle("OneList");
	// Action à faire lorsque la fenêtre est fermée par 
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Paneau principale
	Container contentPane = getContentPane();

	// Liste de selection
	// Tableau de prénoms féminins pour les valeurs de la liste
	String[] data = { "Agathe", "Alice", "Aude", "Barbara", "Clarisse", 
			  "Clémence", "Céline", "Hélia", "Isabelle", "Léa", 
			  "Marion", "Natacha", "Nathalie", "Sylvie", 
			  "Valérie", "Véronique" };
	// Modèle
	// On crée explicitement le modèle de la liste pour être sûr qu'il 
	// soit de type DefaultListModel.  Sinon, il est implémenté par 
	// une classe interne à JList
	DefaultListModel model = new DefaultListModel();
	// Ajout des éléments au modèle
	// La classe DefaultListModel n'a pas de constructeur qui prend 
	// directement un tableau en paramètre comme en a un la classe JList.
	for (int i = 0; i < data.length; i++)
	    model.addElement(data[i]);
	// Liste créée avec le modèle voulu
	final JList list = new JList(model);
	// avec une barre de défilement
	JScrollPane listScrollPane = new JScrollPane(list);
	// Ajout de la liste dans le panneau principal
	
	// Panneau d'affichage 
	final JTextArea textArea = new JTextArea(20, 10);
	// avec une barre de défilement
	JScrollPane textScrollPane = new JScrollPane(textArea);
	// Ajout de la liste dans le panneau principal

	// Panneau pour séparer la liste et l'affichage
	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
					      listScrollPane, textScrollPane);
	contentPane.add(splitPane, BorderLayout.CENTER);
					      
	// Tableau de contrôle
	JPanel controlPanel = new JPanel();
	// Utilisation d'un BoxLayout pour que les boutons et la zone de texte 
	// aient la même hauteur
	controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
	// Ajout du panneau de contrôle en haut du panneau principal
	contentPane.add(controlPanel, BorderLayout.NORTH);

	// Bouton d'affichage
	JButton printButton = new JButton("Print");
	// Ajout du bouton dans le panneau de contrôle
	controlPanel.add(printButton);
	// Contrôleur du bouton
	printButton.addActionListener(new ActionListener () {
		public void actionPerformed(ActionEvent e) {
		    // Liste des valeurs sélectionnées
		    Object[] selection = list.getSelectedValues();
		    // Affichage des valeurs selectionnées dans le
		    // panneau de texte
		    for(int i = 0; i < selection.length; i++)
			textArea.append(selection[i].toString() + "\n");
		    // Ajout d'une ligne de séparation
		    textArea.append("--------------\n");
		}
	    });

	// Bouton de suppression
	JButton removeButton = new JButton("Remove");
	// Ajout du bouton dans le panneau de contrôle
	controlPanel.add(removeButton);
	// Contrôleur du bouton
	removeButton.addActionListener(new ActionListener () {
		public void actionPerformed(ActionEvent e) {
		    // Indices des valeurs sélectionnées
		    // Il est ici préférable de travailler avec les indices
		    // dans le cas où un même élément aurait plusieurs 
		    // occurrences dans la liste
		    int[] indices = list.getSelectedIndices();
		    // Modèle des données de la liste
 		    DefaultListModel model = (DefaultListModel) 
 			list.getModel();
		    // Supression des valeurs sélectionnées
		    // Le "-i" prend en compte le fait que les premières 
		    // suppressions décalent les indices des éléments 
		    // qui suivent.
 		    for(int i = 0; i < indices.length; i++)
 			model.removeElementAt(indices[i]-i);
		}
	    });

	// Zone de texte pour la saisie de l'ajout
	final JTextField textField = new JTextField(10);
	// Bouton pour forcer l'ajout
	JButton addButton = new JButton("Add");
	// Ajout du bouton et de la zone de texte dans le panneau de contrôle
	controlPanel.add(addButton);
	controlPanel.add(textField);
	// Contrôleur commun au bouton et à la zone de texte
	ActionListener addAction = new ActionListener () {
		public void actionPerformed(ActionEvent e) {
		    // Valeur à ajouter
		    String value = textField.getText();
		    // Modèle des données de la liste
 		    DefaultListModel model = (DefaultListModel) 
 			list.getModel();
		    if (value.length() > 0)
			model.addElement(value);
		}
	    };
	// Ajout du contrôleur comme écouteur du bouton et de la zone
	addButton.addActionListener(addAction);
	textField.addActionListener(addAction);
    }
    public static void main(String [] args)
    {
	// Création de la fenêtre
	OneList view = new OneList();
	// Mise en place 
	view.pack();
	// Affichage de la fenêtre
	view.setVisible(true);
    }
}
