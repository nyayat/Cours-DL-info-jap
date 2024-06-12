// -*- Coding: utf-8 -*-
// Time-stamp: <GBLDemo2.java  10 déc 2015 15:46:02>

import java.awt.*;
import javax.swing.*;

/**
 * Démo de GridBaglayout
 * @author O. Carton
 * @version 1.0
 */
class GBLDemo2 extends JFrame {
    final int width  = 150;	// Largeur de la fenêtre
    final int height = 150;	// Hauteur de la fenêtre
    public GBLDemo2 () {
	// Dimension de la fenêtre
	setSize(width, height);
	// Titre de la fenêtre
	setTitle("Demo GrigBagLayout 2");
	// Action à faire lorsque la fenêtre est fermée par 
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Panneau principal
	Container contentPane = getContentPane();

	// Ajout des éléments au panneau principal
	contentPane.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();

	// Pour tous les éléments
	gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.CENTER;
	gbc.weightx = 1;
	gbc.weighty = 1;

	//  La disposition recherchée est la suivante
	//    
	//         0   1   2  
	//        -----------
	//    0  |   A   | B |
	//       |-------|---|
	//    1  | C | D | E |
	//       -------------
	  
	// Les valeurs de gridx et gridy sont explicites.
	// Premiere ligne
	gbc.gridwidth = 2;	// Largeur 2
	contentPane.add(new JButton("A"), gbc);
	// Dernier de la ligne
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	contentPane.add(new JButton("B"), gbc);

 	// Deuxieme ligne
	gbc.gridwidth = 1;
	contentPane.add(new JButton("C"), gbc);
	contentPane.add(new JButton("D"), gbc);
	contentPane.add(new JButton("E"), gbc);
	//contentPane.add(new JButton("F"), gbc);
    }
    public static void main(String [] args)
    {
	// Création de la fenêtre
	GBLDemo2 view = new GBLDemo2();
	// Affichage de la fenêtre
	view.setVisible(true);
	
    }
}
