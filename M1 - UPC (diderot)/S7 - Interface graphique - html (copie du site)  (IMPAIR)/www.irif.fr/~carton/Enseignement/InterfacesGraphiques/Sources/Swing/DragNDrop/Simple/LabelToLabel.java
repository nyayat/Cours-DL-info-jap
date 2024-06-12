// -*- coding: utf-8 -*-
// Time-stamp: <LabelToLabel.java  27 oct 2021 12:11:32>

// Inspiré de http://zetcode.com/javaswing/draganddrop/
// Ne marche pas car JLabel n'a pas de méthode setDragEnabled

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LabelToLabel extends JFrame {
    public LabelToLabel() {
        // Titre
        setTitle("Drag & Drop");
        // Action à faire lorsque la fenêtre est fermée.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Composants
        JLabel source = new JLabel("Texte de la source", SwingConstants.CENTER);
        source.setPreferredSize(new Dimension(150, 20));
        // source.setDragEnabled(true);
        JLabel target = new JLabel("Target", SwingConstants.CENTER);
        target.setPreferredSize(new Dimension(150, 20));
        // Activation du Drag & Drop entre les deux JLabel 
        TransferHandler handler = new TransferHandler("text");
        source.setTransferHandler(handler);
        target.setTransferHandler(handler);
        source.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent event) {
		    System.out.println("Clicked");
                    // Source de l'événement       
                    JComponent source = (JComponent) event.getSource();
                    // Handler de transfert
                    TransferHandler handler = source.getTransferHandler();
                    // Drag & Drop proprement dit
                    handler.exportAsDrag(source, event, TransferHandler.MOVE);
		    repaint();
                }
            });
        // Mise en place des éléments dans un panneau
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(source, BorderLayout.WEST);
        panel.add(target, BorderLayout.CENTER);
        // Mise en place du panneau principal
        setContentPane(panel);
        pack();
        //  Positionnement relatif au lancement
        setLocationRelativeTo(null);
    }
    // Main
    public static void main(String[] args) {
        LabelToLabel l2l = new LabelToLabel();
        l2l.setVisible(true);
    }
}
