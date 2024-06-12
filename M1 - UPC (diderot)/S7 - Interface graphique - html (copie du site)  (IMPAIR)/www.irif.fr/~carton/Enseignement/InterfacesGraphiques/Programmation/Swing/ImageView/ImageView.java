// Time-stamp: <ImageView.java  25 Apr 2005 15:31:17>

import java.awt.*;
import javax.swing.*;

/**
 * Visualisation d'une image
 * @author O. Carton
 * @version 1.0
 */
class ImageView extends JFrame {
    Image image;		// L'image
    public ImageView () {
	// Titre de la fenêtre
	setTitle("Visualisation d'une image");
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Lecture de l'image par le tookit par défaut
	image = Toolkit.getDefaultToolkit().getImage("images/9personnes.jpg");
	// Affichage du type réel de l'image
	System.out.println(image.getClass());
	// Affichage des dimensions de l'image
	// Ces deux lignes affichent "Image width = -1, height = -1"
	// car l'image n'est pas encore chargée
	System.out.print("Image : width = " + image.getWidth(this));
	System.out.println(",  height = " + image.getHeight(this));
	// Place le composant
	setContentPane(new ImagePane());
    }
    /** Panneau pour afficher l'image */
    // Une méthode naïve consiterait à dessiner l'image directement dans
    // l'objet JFrame en redéfinissant la méthode paint dans la classe
    // ImageView.   On peut remarquer que le haut de l'image n'est
    // pas visible avec un appel g.drawImage(image, 0, 0, this);
    // Pour éviter ce problème, l'image est dessiner dans un panneau
    // qui devient le contentPane de la fenêtre.
    class ImagePane extends JPanel {
	ImagePane() {
	    // Taille du panneau égale à la taille de l'image
	    setPreferredSize(new Dimension(377,517));
	}	    
	// Dessins de la fenêtre
	public void paintComponent(Graphics g) {
	    // Appel à la méthode de la super-classe
	    super.paintComponent(g);
	    // Dessin de l'image
	    g.drawImage(image, 0, 0, this);
	}
    }
    public static void main(String [] args)
    {
	// Création de la fenêtre
	ImageView view = new ImageView();
	// Mise en place
	view.pack();
	// Affichage de la fenêtre
	view.setVisible(true);
    }
}
