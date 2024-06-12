// -*- Coding: utf-8 -*-
// Time-stamp: <SimpleLine.java  25 sep 2020 07:47:17>

import java.awt.*;
import javax.swing.*;

/**
 * Programme SWING qui dessine une ligne en diagonal
 * @author O. Carton
 * @version 1.0
 */
class SimpleLine extends JFrame {
    final int width  = 300;     // Largeur de la fenêtre
    final int height = 200;     // Hauteur de la fenêtre
    public SimpleLine () {
        // Dimensions de la fenêtre
        setSize(width, height);
        // Titre de la fenêtre
        setTitle("Dessin d'une ligne");
        // Action à faire lorsque la fenêtre est fermée 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Création et mise en place du panneau de dessin
        setContentPane(new Drawing());
    }
    public static void main(String [] args) {
        // Création de la fenêtre
        SimpleLine sl = new SimpleLine();
        // Affichage de la fenêtre
        sl.setVisible(true);
    }
}

/** Panneau de dessin */
class Drawing extends JPanel {
    // Constructeur
    Drawing() {
        // Fond blanc
        setBackground(Color.white);
    }
    // Dessin proprement dit 
    public void paintComponent(Graphics g) {
        // Largeur et hauteur du panneau
        int width = getWidth(); 
        int height = getHeight(); 
        // Appel de la méthode de la classe JPanel
        super.paintComponent(g);
        // Dessin d'une ligne en diagonal
        g.drawLine(width/4, height/4, 3*width/4, 3*height/4);
    }
}
