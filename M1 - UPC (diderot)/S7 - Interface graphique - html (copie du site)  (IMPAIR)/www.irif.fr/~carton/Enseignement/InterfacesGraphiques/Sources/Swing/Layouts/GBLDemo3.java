// -*- Coding: utf-8 -*-
// Time-stamp: <GBLDemo3.java  14 oct 2020 09:29:11>

import java.awt.*;
import javax.swing.*;

/**
 * Démo de GridBaglayout tiré du manuel de Java
 * @author O. Carton
 * @version 1.0
 */
public class GBLDemo3 extends JFrame {
    public GBLDemo3() {
        // Titre de la fenêtre
        setTitle("Demo GrigBagLayout 3");
        // Action à faire lorsque la fenêtre est fermée par 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Panneau principal
        Container contentPane = getContentPane();

        // Ajout des éléments au panneau principal
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        // gbc.weightx = 1.0;
        contentPane.add(new JButton("Bouton 1"), gbc);
        contentPane.add(new JButton("Bouton 2"), gbc);
        contentPane.add(new JButton("Bouton 3"), gbc);
        // Dernier de la ligne
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        contentPane.add(new JButton("Bouton 4"), gbc);

        // Nouvelle ligne
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        contentPane.add(new JButton("Bouton 6"), gbc);

        // Dernier de la ligne
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        contentPane.add(new JButton("Bouton 7"), gbc);
    }
    public static void main(String args[]) {
        // Création de la fenêtre
        GBLDemo3 view = new GBLDemo3();
        // Mise en place des éléments
        view.pack();
        // Affichage de la fenêtre
        view.setVisible(true);
    }
}
