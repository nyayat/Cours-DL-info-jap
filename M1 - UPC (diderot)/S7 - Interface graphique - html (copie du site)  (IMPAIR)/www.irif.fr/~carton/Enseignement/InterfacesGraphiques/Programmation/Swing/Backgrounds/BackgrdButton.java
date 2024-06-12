// -*- Coding: utf-8 -*-
// Time-stamp: <BackgrdButton.java   5 déc 2013 13:37:02>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Fenêtre avec trois boutons pour changer la couleur de fond.
 * @author O. Carton
 * @version 1.0
 */
class BackgrdButton extends JFrame {
    final int width  = 300;	// Largeur de la fenêtre
    final int height = 200;	// Hauteur de la fenêtre
    public BackgrdButton () {
	// Dimension de la fenêtre
	setSize(width, height);
	// Titre de la fenêtre
	setTitle("Changement de fond par boutons");
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Panneau de contenu de la fenêtre
	Container contentPane = getContentPane();
	// Ajout du panel contenant les trois boutons
	contentPane.add(new ButtonPanel());
    }
    public static void main(String [] args)
    {
	// Création de la fenêtre
	BackgrdButton bg = new BackgrdButton();
	// Affichage de la fenêtre
	bg.setVisible(true);
    }
}

/**
 * Panneau contenant les trois boutons
 */
class ButtonPanel extends JPanel {
    // Ajout d'un bouton à partir du titre et de la couleur
    private void addButton(String name, final Color c) {
	// Création du bouton 
	JButton button = new JButton(name);
	// Action associée au bouton.
	// L'action est réalisée par un object qui implémente l'interface 
	// ActionListener.  Cette interface déclare une méthode 
	// actionPerformed qui est appelée lorsque le bouton est activé par 
	// l'utilisateur.  On utilise une classe anonyme qui implémente 
	// l'interface ActionListener.
	button.addActionListener(new ActionListener () {
		public void actionPerformed(ActionEvent event) {
		    // Changement de la couleur du fond
		    setBackground(c);
		    // Actualisation de la fenêtre
		    repaint();
		}
	    });
	// Ajout du bouton au panel.
	add(button);
    }
    public ButtonPanel () {
	// Création d'un bouton pour chaque couleur
	addButton("Rouge", Color.red);
	addButton("Vert", Color.green);
	addButton("Bleu", Color.blue);
   }
}
