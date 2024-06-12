// -*- coding: utf-8 -*-
// Time-stamp: <FieldToButton.java   4 nov 2020 10:32:00>

// Inspiré de http://zetcode.com/javaswing/draganddrop/

import java.awt.*;
import javax.swing.*;

public class FieldToButton extends JFrame {
    public FieldToButton() {
        // Titre
        setTitle("Drag & Drop JTextField vers JButton");
        // Action à faire lorsque la fenêtre est fermée.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Composants
        JTextField field = new JTextField(15);
        JButton button = new JButton("Button");
        // Activation du drag & drop du champ de texte vers le bouton
        field.setDragEnabled(true);
        // Il est indispensable d'ajouter un handler au bouton
        button.setTransferHandler(new TransferHandler("text"));
        // Mise en place des éléments dans un panneau
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(field, BorderLayout.WEST);
        panel.add(button, BorderLayout.CENTER);
        // Mise en place du panneau principal
        setContentPane(panel);
        pack();
        //  Positionnement relatif au lancement
        setLocationRelativeTo(null);
    }
    // Main
    public static void main(String[] args) {
        FieldToButton f2b = new FieldToButton();
        f2b.setVisible(true);
    }
}
