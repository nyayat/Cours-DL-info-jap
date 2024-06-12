// -*- coding: utf-8 -*-
// Time-stamp: <FieldToField.java  29 oct 2020 10:22:37>

// Inspiré de http://zetcode.com/

import java.awt.*;
import javax.swing.*;

public class FieldToField extends JFrame {
    public FieldToField() {
        // Titre
        setTitle("Drag & Drop entre JTextField");
        // Action à faire lorsque la fenêtre est fermée.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Composants
        JTextField source = new JTextField("Source", 15);
        JTextField target = new JTextField("Target", 15);
        // Activation du drag & drop du champ de texte vers le bouton
        source.setDragEnabled(true);
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
        FieldToField f2f = new FieldToField();
        f2f.setVisible(true);
    }
}
