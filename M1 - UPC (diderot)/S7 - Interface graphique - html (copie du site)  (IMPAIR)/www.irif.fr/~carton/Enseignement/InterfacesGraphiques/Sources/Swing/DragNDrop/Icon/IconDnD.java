// -*- coding: utf-8 -*-
// Time-stamp: <IconDnD.java  28 oct 2020 11:49:49>

// Inspiré de http://zetcode.com/javaswing/draganddrop/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IconDnD extends JFrame {
    public IconDnD() {
        // Titre
        setTitle("Icon Drag & Drop");
        // Action à faire lorsque la fenêtre est fermée.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Icones
        ImageIcon icon1 = new ImageIcon("sad.jpg");
        ImageIcon icon2 = new ImageIcon("neutral.jpg");
        ImageIcon icon3 = new ImageIcon("smile.jpg");
        // Composants
        JLabel label1 = new JLabel(icon1, JLabel.CENTER);
        JLabel label2 = new JLabel(icon2, JLabel.CENTER);
        JLabel label3 = new JLabel(icon3, JLabel.CENTER);
        JButton button = new JButton(icon2);
        button.setFocusable(false);
        // Écouteur 
        DragMouseAdapter listener = new DragMouseAdapter();
        label1.addMouseListener(listener);
        label2.addMouseListener(listener);
        label3.addMouseListener(listener);
        // Drag & Drop
        label1.setTransferHandler(new TransferHandler("icon"));
        label2.setTransferHandler(new TransferHandler("icon"));
        label3.setTransferHandler(new TransferHandler("icon"));
        button.setTransferHandler(new TransferHandler("icon"));
        // Mise en place des éléments dans un panneau
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        // Bords du panneau principal
        mainPanel.add(label1, BorderLayout.WEST);   // En haut à gauche
        mainPanel.add(label2, BorderLayout.CENTER); // En haut au centre
        mainPanel.add(label3, BorderLayout.EAST);   // En haut à droite
        mainPanel.add(button, BorderLayout.SOUTH);  // En bas
        // Mise en place du panneau principal
        setContentPane(mainPanel);
        pack();
        // Positionnement relatif au lancement
        setLocationRelativeTo(null);
    }
    // Mouse listener
    private class DragMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            JComponent widget = (JComponent) event.getSource();
            TransferHandler handler = widget.getTransferHandler();
            handler.exportAsDrag(widget, event, TransferHandler.COPY);
        }
    }
    // Fonction main
    public static void main(String[] args) {
        IconDnD idnd = new IconDnD();
        idnd.setVisible(true);
    }
}
