// -*- coding: utf-8 -*-
// Time-stamp: <ImageWait.java  21 oct 2020 09:01:39>

import java.awt.*;
import javax.swing.*;

/**
 * Visualisation d'une image avec attente du chargement de l'image
 * @author O. Carton
 * @version 1.0
 */
class ImageWait extends JFrame {
    Image image;                // L'image
    public ImageWait () {
        // Titre de la fenêtre
        setTitle("Visualisation d'une image avec attente");
        // Action à faire lorsque la fenêtre est fermée.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Lecture de l'image par le tookit par défaut
        image = Toolkit.getDefaultToolkit().getImage("images/9personnes.jpg");
        // Place le composant dans la fenêtre
        setContentPane(new ImagePane());
    }
    /** Panneau pour afficher l'image */
    class ImagePane extends JPanel {
        ImagePane() {
            // Taille du panneau
            setPreferredSize(new Dimension(377,517));
        }           
        // Dessins de la fenêtre
        public void paintComponent(Graphics g) {
            // Appel à la méthode de la super-classe
            super.paintComponent(g);
            // Dessin de l'image
            g.drawImage(image, 0, 0, this);
        }
        // Redéfinition de la méthode imageUpdate de l'interface ImageObserver
        public boolean imageUpdate(Image image, int flags, int x, int y,
                                   int width, int height) {
            // Affichage de l'avancement du chargement de l'image
            System.out.println("imageUpdate() : x = " + x + ", y = " + y +
                               ", width = " + width + ", height = " + height);
            // Affichage de l'image lorsque l'image est totalement chargée
            if ((flags & ALLBITS) != 0)
                repaint();
            return true;
        }
    }
    public static void main(String [] args) {
        // Création de la fenêtre
        ImageWait view = new ImageWait();
        // Mise en place
        view.pack();
        // Affichage de la fenêtre
        view.setVisible(true);
    }
}
