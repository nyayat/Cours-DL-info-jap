// -*- Coding: utf-8 -*-
// Time-stamp: <GBLDemo4.java  10 déc 2015 15:46:27>

import java.awt.*;
import javax.swing.*;

/**
 * Démo de GridBaglayout tiré du manuel de Java
 * @author O. Carton
 * @version 1.0
 */
public class GBLDemo4 extends JFrame {
    public GBLDemo4() {
	// Titre de la fenêtre
	setTitle("Demo GrigBagLayout 4");
	// Action à faire lorsque la fenêtre est fermée par 
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Panneau principal
	Container contentPane = getContentPane();

	// Ajout des éléments au panneau principal
	contentPane.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();

	// Les boutons occupent tout l'espace de leur case
	gbc.fill = GridBagConstraints.BOTH;
	// Les quatres boutons de la première ligne se répartissent
	// l'espace.
	gbc.weightx = 1.0;
	contentPane.add(new JButton("Bouton 1"), gbc);
	contentPane.add(new JButton("Bouton 2"), gbc);
	contentPane.add(new JButton("Bouton 3"), gbc);
	// Dernier de la ligne
	gbc.gridwidth = GridBagConstraints.REMAINDER; 
	contentPane.add(new JButton("Bouton 4"), gbc);

	// Nouvelle ligne
	gbc.weightx = 0.0;
	contentPane.add(new JButton("Bouton 5"), gbc); 

	// Nouvelle ligne
	gbc.gridwidth = GridBagConstraints.RELATIVE;
	contentPane.add(new JButton("Bouton 6"), gbc);
	// Si ce bouton est ajouté, il rmeplace le 
	// bouton 7 : comportement inxepliqué ?
	// Pour que ce composant apparaissent, il faut mettre
	// - gbc.gridwidth = 1;   
	//   avant le bouton 6
	// - gbc.gridwidth = GridBagConstraints.RELATIVE;
	//   avant ce bouton 
	// contentPane.add(new JButton("Bouton Z"), gbc);
	// Dernier de la ligne
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	contentPane.add(new JButton("Bouton 7"), gbc);

	// Nouvelle ligne
	gbc.gridwidth = 1;                
	gbc.gridheight = 2;
	gbc.weighty = 1.0;
	contentPane.add(new JButton("Bouton 8"), gbc);

	gbc.weighty = 0.0;
	// Les boutons 9 et 10 terminent leur ligne.
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.gridheight = 1;              
	contentPane.add(new JButton("Bouton 9"), gbc);
	contentPane.add(new JButton("Bouton 10"), gbc);

    }
    public static void main(String args[]) {
	// Création de la fenêtre
	GBLDemo4 view = new GBLDemo4();
	// Mise en place des éléments
	view.pack();
	// Affichage de la fenêtre
	view.setVisible(true);
    }
}
