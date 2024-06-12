// -*- Coding: utf-8 -*-
// Time-stamp: <SimpleFrame.java   5 déc 2013 13:38:49>

import javax.swing.*;

/**
 * Programme SWING qui ouvre une vraie fenêtre
 * @author O. Carton
 * @version 1.0
 */
class SimpleFrame extends JFrame {
    final int width  = 300;	// Largeur de la fenêtre
    final int height = 200;	// Hauteur de la fenêtre
    public SimpleFrame () {
	// Dimensions de la fenêtre
	setSize(width, height);
	// Titre de la fenêtre
	setTitle("Simple fenêtre");
	// Action à faire lorsque la fenêtre est fermée par 
	// l'utilisateur.  Les autres actions possibles sont 
	// - DO_NOTHING_ON_CLOSE	
	// - DISPOSE_ON_CLOSE
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String [] args)
    {
	// Création de la fenêtre
	SimpleFrame sf = new SimpleFrame();
	// Affichage de la fenêtre
	sf.setVisible(true);
    }
}
