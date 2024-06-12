// -*- coding: utf-8 -*-
// Time-stamp: <Splines.java  23 sep 2020 10:40:54>

/**
 * Expériences sur les splines
 * @author O. Carton
 * @version 1.0
 */

// Awt
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
// Awt.event
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
// Awt.geom
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.CubicCurve2D;
// Swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

class Splines extends JFrame {
    public Splines () {
        // Dimensions de la fenêtre
        setSize(500, 400);
        // Titre de la fenêtre
        setTitle("Splines");
        // Action à faire lorsque la fenêtre est fermée.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Panneau de contenu de la fenêtre
        Container contentPane = getContentPane();

        // Mise en place des panneaux
        contentPane.add(new SplinePanel(), BorderLayout.CENTER);
    }
    /** Panneau contenant la spline */
    class SplinePanel extends JPanel 
        implements MouseListener, MouseMotionListener {
        // Points de contrôle de la spline
        private Point2D[] points = new Point2D[4];
        // Point sélectionné (null si aucun)
        private Point2D selectedPoint = null;
        // Les trois objets suivants sont utilisés pour les dessins.
        // Pour éviter la création d'objets temporaires, les mêmes 
        // objets sont systématiquement réutilisés.
        // Ligne
        Line2D line = new Line2D.Double();
        // Rectangle 
        Rectangle2D square = new Rectangle2D.Double();
        // Spline
        CubicCurve2D spline = new CubicCurve2D.Double();
        /* Contructeur */
        SplinePanel() {
            // Points de départ
            points[0] = new Point2D.Double(50, 100);
            points[1] = new Point2D.Double(100, 150);
            points[2] = new Point2D.Double(300, 50);
            points[3] = new Point2D.Double(350, 100);
            // Écouteur des déplacements de souris
            addMouseListener(this);
            addMouseMotionListener(this);
            // Couleur de fond
            setBackground(Color.yellow);
        }
        
        public void paintComponent(Graphics g) {
            // Appel de la méthode paintComponent de JPanel
            super.paintComponent(g);
            // Conversion en un contexte 2D
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                                RenderingHints.VALUE_RENDER_QUALITY);
            // Taille du trait
            g2.setStroke(new BasicStroke(4));
            
            // Dessins des tangentes
            g2.setPaint(Color.gray);
            line.setLine(points[0], points[1]);
            g2.draw(line);
            line.setLine(points[2], points[3]);
            g2.draw(line);

            // Dessin de la spline
            g2.setPaint(Color.black);
            spline.setCurve(points, 0);
            g2.draw(spline);

            // Dessin des points de contrôle
            for (int i = 0; i < points.length; i++) {
                if (points[i] == selectedPoint)
                    // Couleur du point sélectionnné
                    g2.setPaint(Color.red);
                else
                    // Couleur des autres points
                    g2.setPaint(Color.blue);
                // Draw the point.
                g2.fill(getSquare(points[i]));
            }
        }
        /** Retoune un carré autour d'un point */
        private Shape getSquare(Point2D p) {
            int side = 10;
            square.setRect(p.getX() - side / 2, p.getY() - side / 2, 
                           side, side);
            return square;
        }
        public void mouseClicked(MouseEvent event) {}
        public void mousePressed(MouseEvent event) {
            selectedPoint = null;
            for (int i = 0; i < points.length; i++) {
                Shape square = getSquare(points[i]);
                if (square.contains(event.getPoint())) {
                    selectedPoint = points[i];
                    break;
                }
            }
            repaint();
        }
        public void mouseReleased(MouseEvent event) { 
            selectedPoint = null; 
            repaint();
        }
        public void mouseMoved(MouseEvent event) {}
        public void mouseDragged(MouseEvent event) {
            if (selectedPoint != null) {
                selectedPoint.setLocation(event.getPoint());
                repaint();
            }
        }
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}

    }
    public static void main(String [] args) {
        // Création de la fenêtre
        Splines view = new Splines();
        // Affichage de la fenêtre
        view.setVisible(true);
    }
}
