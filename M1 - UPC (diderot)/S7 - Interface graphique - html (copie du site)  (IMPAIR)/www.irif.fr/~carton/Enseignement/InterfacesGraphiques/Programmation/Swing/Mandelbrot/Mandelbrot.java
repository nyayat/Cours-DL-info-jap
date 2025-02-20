// Time-stamp: <Mandelbrot.java  17 May 2004 13:00:31>

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Dessin de la fractale de Mandelbrot
 * @author O. Carton d'apr�s une id�e de J. Berstel
 * @version 1.1
 */
class Mandelbrot extends JFrame {
    // Z�ne de la fractale visualis�e
    Zone zone;
    // Panneau de l'image
    ImagePane imagePane;
    // Bouton de zoom
    JButton zoomButton;
    /** Constructeur avec une z�ne en param�tre */
    public Mandelbrot(Zone zone) {
	// Initialisation de la z�ne
	this.zone = zone;
	setTitle("Fractale de MandelBrot");
	// Action � faire lorsque la fen�tre est ferm�e.
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	// Barre de menus
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	// Bouton de sortie de l'application
	JButton quitButton = new JButton("Quit");
	// Contr�leur du bouton de sortie 
	quitButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
		    // Sortie de l'application
		    System.exit(0);
		}
	    });
	// Ajout du bouton � la barre de menus
	menuBar.add(quitButton);

	// Bouton de zoom d�sactiv� au d�part
	zoomButton = new JButton("Zoom");
	zoomButton.setEnabled(false);
	// Contr�leur du bouton de zoom
	zoomButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
		    // Cr�ation d'une nouvelle fen�tre
		    Mandelbrot view = new Mandelbrot(imagePane.getSelectedZone());
		    // Mise en place
		    view.pack();
		    // Affichage de la fen�tre
		    view.setVisible(true);
		}
	    });
	// Ajout du bouton � la barre de menus
	menuBar.add(zoomButton);
	// Panneau de l'image
	imagePane = new ImagePane();
	setContentPane(imagePane);
    }
    /** Constructeur par defaut */
    public Mandelbrot() {
	this(new Zone(-2.0, -2.0, 4.0, 4.0));
    }
    /** Panneau pour afficher l'image */
    class ImagePane extends JPanel {
	// Dimensions en pixels
	int width;		// Largeur
	int height;		// Hauteur
	// Z�ne selectionn�e pour le zoom
	Selection selection = new Selection();
	// Image 
	BufferedImage image; 
	/** Constructeur */
	ImagePane() {
	    // Calcul des dimensions
	    double ratio = zone.getHeight()/zone.getWidth();
	    if (ratio > 1) {
		width = (int) (500/ratio);
		height = 500;
	    } else {
		width = 500;
		height = (int) (500*ratio);
	    }
	    // Taille du panneau par d�faut
	    setPreferredSize(new Dimension(width, height));
	    // Cr�ation de l'image
	    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    // Calcul de l'image
	    compute(image);
	    // �coute des �v�nements de souris : clics et d�placements
	    addMouseListener(selection);
	    addMouseMotionListener(selection);
	}	    
	// Dessins du panneau
	public void paintComponent(Graphics g) {
	    // Appel � la m�thode de la super-classe
	    super.paintComponent(g);
	    // Affichage de l'image
	    g.drawImage(image, 0, 0, this);
	    // Affichage de la s�lection
	    ((Graphics2D) g).draw(selection.getRectangle());
	}
	// Retourne la z�ne s�lectionn�e dans les coordonn�es absolues
	Zone getSelectedZone() {
	    // Z�ne en coordonn�es du JPanel
	    Rectangle sel = selection.getRectangle();
	    // Rapport entre les coordonn�es absolues et les coordonn�es
	    // en pixels
	    double scale = zone.getWidth()/width;
	    // Conversion en coordonn�es absolues
	    double x = zone.getX() + sel.getX() * scale;
	    double y = zone.getY() + sel.getY() * scale;
	    double width = sel.getWidth() * scale;
	    double height = sel.getHeight() * scale;
	    // Cr�ation du Rectangle
	    return new Zone(x, y, width, height);
	}
	/** Z�ne s�lectionn�e */
	class Selection extends MouseAdapter implements MouseMotionListener {
	    // Premier point selectionn�
	    int selx1;
	    int sely1;
	    // Deuxi�me point selectionn�
	    int selx2;
	    int sely2;
	    // �coute des �v�nements de souris 
	    public void mousePressed(MouseEvent event) {
		selx1 = selx2 = event.getX();
		sely1 = sely2 = event.getY();
		zoomButton.setEnabled(false);
		repaint();
	    }
	    public void mouseDragged(MouseEvent event) {
		selx2 = event.getX();
		sely2 = event.getY();
		zoomButton.setEnabled(selx1 != selx2 && sely1 != sely2);
		repaint();
	    }
	    public void mouseMoved(MouseEvent event) {
	    }
	    // Retourne le rectangle qui d�limite la z�ne selectionn�e
	    // Ce rectangle utilise les coordonn�es du JPanel en pixels.
	    Rectangle getRectangle() {
		// Calcul du rectangle
		int x = Math.min(selx1, selx2);
		int y = Math.min(sely1, sely2);
		int width = Math.abs(selx1 - selx2);
		int height = Math.abs(sely1 - sely2);
		return new Rectangle(x, y, width, height);
	    }
	}
    }
    /** Calcul de l'image */
    void compute(BufferedImage image) {
	// Objet raster et mod�le de couleur de l'image
	WritableRaster raster = image.getRaster();
	ColorModel model = image.getColorModel();

	// Couleur de dessin
	Color color = Color.red;
	// Coordonn�es sRGB de cette couleur
	int argb = color.getRGB();
	// Conversion de cette couleur dans le mod�le de couleur
	Object colorData = model.getDataElements(argb, null);
	    
	// Dimensions de l'image
	int width = image.getWidth();
	int height = image.getHeight();

	// Calculs
	for (int i = 0; i < width; i++) {
	    for (int j = 0; j < height; j++) {
		double a = zone.getX() + i * zone.getWidth()/width;
		double b = zone.getY() + j * zone.getHeight()/height;
		if (converge(a, b)) {
		    raster.setDataElements(i, j, colorData);
		}
	    }
	}
    }
    /** Calcul s'il y a convergence en utilisant a et b comme offsets */
    static boolean converge(double a, double b) {
	// Point de d�part
	double x = 0.0;
	double y = 0.0;
	for (int i = 0; i < 30; i++) {
	    // Calcul du nouveau point dans des variables temporaires
	    double xnew = x*x - y*y + a;
	    double ynew = 2*x*y + b;
	    // Transfert 
	    x = xnew;
	    y = ynew;
	    // Il n'y a pas convergence si on sort du carr� 2x2
	    if (Math.abs(x) > 2 || Math.abs(y) > 2) 
		return false;
	}
	return true;	// Convergence
    }
    public static void main(String [] args)
    {
	// Cr�ation de la fen�tre
	Mandelbrot view = new Mandelbrot();
	// Mise en place
	view.pack();
	// Affichage de la fen�tre
	view.setVisible(true);
    }
    /**
     * Z�ne de travail 
     * Cette z�ne est d�finie par le coin sup�rieur gauche et par
     * des dimensions horizontale et verticales.
     */
    static class Zone {
	private double x;	// Absicsse du coin sup�rieur gauche
	private double y;	// Ordonn�e du coin sup�rieur gauche
	private double width;	// Largeur
	private double height;	// Hauteur
	Zone(double x, double y, double width, double height) {
	    this.x = x;
	    this.y = y;
	    this.width = width;
	    this.height = height;
	}
	double getX() { return x; }
	double getY() { return y; }
	double getWidth() { return width; }
	double getHeight() { return height; }
    }
}
