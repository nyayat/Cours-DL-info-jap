// -*- coding: utf-8 -*-
// Time-stamp: <ImageTrack.java  21 oct 2020 09:02:08>

import java.awt.*;
import javax.swing.*;

/**
 * Visualisation d'une image avec utilisation d'un MediaTracker
 * @author O. Carton
 * @version 1.0
 */
class ImageTrack extends JFrame {
    Image image;
    public ImageTrack () {
        // Titre de la fenêtre
        setTitle("Utilisation d'un MediaTracker");
        // Action à faire lorsque la fenêtre est fermée.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Lecture de l'image par le tookit par défaut
        image = Toolkit.getDefaultToolkit().getImage("images/9personnes.jpg");
        // Attente du chargement de l'image avec un MediaTracker
        // Création du MediaTracker
        MediaTracker tracker = new MediaTracker(this);
        // Ajout de l'image aux images suivies
        tracker.addImage(image, 0);
        // Attente proprement dite
        try { tracker.waitForID(0); }
        catch(InterruptedException e) {}
        // Place le composant dans la fenêtre
        setContentPane(new ImagePane());
    }
    /** Panneau pour afficher l'image */
    class ImagePane extends JPanel {
        ImagePane() {
            // Taille du panneau donnée par l'image
            setPreferredSize(new Dimension(image.getWidth(this),
                                           image.getHeight(this)));
        }           
        // Dessins de la fenêtre
        public void paintComponent(Graphics g) {
            // Appel à la méthode de la super-classe
            super.paintComponent(g);
            // Dessin de l'image
            g.drawImage(image, 0, 0, this);
        }
        // Redéfinition de la méthode imageUpdate de l'interface ImageObserver
        // Cette méthode ne sera pas appelée puisque l'image sera déjà chargée
        // au moment de l'appel à g.drawImage dans la méthode paint().
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
        ImageTrack view = new ImageTrack();
        // Mise en place
        view.pack();
        // Affichage de la fenêtre
        view.setVisible(true);
    }
}
