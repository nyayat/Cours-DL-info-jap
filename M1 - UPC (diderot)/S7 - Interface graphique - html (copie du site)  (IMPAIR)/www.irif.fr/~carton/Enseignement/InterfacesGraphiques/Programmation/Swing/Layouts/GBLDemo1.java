// -*- Coding: utf-8 -*-
// Time-stamp: <GBLDemo1.java  10 déc 2015 15:45:10>

import java.awt.*;
import javax.swing.*;

/**
 * Démo de GridBaglayout
 * @author O. Carton
 * @version 1.0
 */
class GBLDemo1 extends JFrame {
    final int width  = 150;	// Largeur de la fenêtre
    final int height = 150;	// Hauteur de la fenêtre
    public GBLDemo1 () {
	// Dimension de la fenêtre
	setSize(width, height);
	// Titre de la fenêtre
	setTitle("Demo GrigBagLayout 1");
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
	//    0  |   A   |   |
	//       |-------| B |
	//    1  |   | D |   |
	//       | C |-------|
	//    2  |   |   E   |
	//       -------------
	  
	// Les valeurs de gridx et gridy sont explicites.
	// Premiere ligne
	gbc.gridx = 0;     
	gbc.gridwidth = 2;
	contentPane.add(new JButton("A"), gbc);
	gbc.gridx = 2;     
	gbc.gridwidth = 1;   gbc.gridheight = 2;
	contentPane.add(new JButton("B"), gbc);

 	// Deuxieme ligne
	gbc.gridx = 0;     gbc.gridy = 1;
	contentPane.add(new JButton("C"), gbc);
	gbc.gridx = 1;
	gbc.gridheight = 1;
	contentPane.add(new JButton("D"), gbc);
	
	// Troisieme Ligne
	gbc.gridx = 1;     gbc.gridy = 2;
	gbc.gridwidth = 2;
	contentPane.add(new JButton("E"), gbc);
    }
    public static void main(String [] args)
    {
	// Création de la fenêtre
	GBLDemo1 view = new GBLDemo1();
	// Affichage de la fenêtre
	view.setVisible(true);
	
    }
}
