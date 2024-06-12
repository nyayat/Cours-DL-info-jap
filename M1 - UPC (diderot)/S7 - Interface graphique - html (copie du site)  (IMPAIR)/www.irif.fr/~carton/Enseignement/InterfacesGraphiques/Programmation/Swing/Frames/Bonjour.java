// -*- Coding: utf-8 -*-
// Time-stamp: <Bonjour.java   5 déc 2013 13:40:09>

import javax.swing.*;

/**
 * Programme Bonjour Monde en Swing
 * @author O. Carton
 * @version 1.0
 */
class Bonjour {
    public static void main(String [] args)
    {
	// Comme le premier paramètre est null, le message 
	// s'affiche dans sa propre fenêtre.
	JOptionPane.showMessageDialog(null, "Bonjour Monde");
	System.exit(0);
    }
}
