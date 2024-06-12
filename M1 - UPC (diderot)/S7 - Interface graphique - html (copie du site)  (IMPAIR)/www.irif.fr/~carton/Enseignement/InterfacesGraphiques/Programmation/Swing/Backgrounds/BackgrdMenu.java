// -*- Coding: utf-8 -*-
// Time-stamp: <BackgrdMenu.java   5 déc 2013 13:37:14>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Fenêtre avec un menu pour changer la couleur de fond.
 * @author O. Carton
 * @version 1.0
 */
class BackgrdMenu extends JFrame {
    final int width  = 300;	// Largeur de la fenêtre
    final int height = 200;	// Hauteur de la fenêtre
    // Ajout d'une entrée dans un menu
    private void addMenuItem(JMenu menu, String name, final Color c) {
	// Création de l'entrée
	JMenuItem item = new JMenuItem(name);
	// Action associée à l'entrée du menu.
	// L'action est réalisée par un object qui implémente l'interface
	// ActionListener.  Cette interface déclare une méthode
	// actionPerformed qui est appelée lorsque l'entrée est choisie par
	// par l'utilisateur.  On utilise une classe anonyme qui implémente
	// l'interface ActionListener.
	item.addActionListener(new ActionListener () {
		public void actionPerformed(ActionEvent event) {
		    // Changement de la couleur du fond
		    getContentPane().setBackground(c);
		    // Actualisation de la fenêtre
		    repaint();
		}
	    });
	// Ajout de l'entrée dans le menu
	menu.add(item);
    }
    public BackgrdMenu () {
	// Dimension de la fenêtre
	setSize(width, height);
	// Titre de la fenêtre
	setTitle("Changement de fond par menu");
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Barre de menu
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	// Ajout du menu
	JMenu colorMenu = new JMenu("Couleur");
	menuBar.add(colorMenu);
	// Choix du menu
 	addMenuItem(colorMenu, "Rouge", Color.red);
 	addMenuItem(colorMenu, "Vert", Color.green);
 	addMenuItem(colorMenu, "Bleu", Color.blue);
	addMenuItem(colorMenu, "Magenta", Color.magenta);
	addMenuItem(colorMenu, "Jaune", Color.yellow);
	addMenuItem(colorMenu, "Orange", Color.orange);
	addMenuItem(colorMenu, "Rose", Color.pink);
	addMenuItem(colorMenu, "Cyan", Color.cyan);
    }
    public static void main(String [] args)
    {
	// Création de la fenêtre
	BackgrdMenu bg = new BackgrdMenu();
	// Affichage de la fenêtre
	bg.setVisible(true);
    }
}
