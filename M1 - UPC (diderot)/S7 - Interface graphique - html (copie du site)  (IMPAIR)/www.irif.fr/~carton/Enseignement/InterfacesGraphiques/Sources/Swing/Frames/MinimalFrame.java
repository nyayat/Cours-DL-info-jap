// -*- Coding: utf-8 -*-
// Time-stamp: <MinimalFrame.java  25 sep 2020 07:45:52>

import javax.swing.*;

/**
 * Programme minimal qui se contente d'ouvrir une vraie fenêtre
 * @author O. Carton
 * @version 1.0
 */
class MinimalFrame extends JFrame {
    public static void main(String [] args) {
        // Création de la fenêtre
        MinimalFrame mf = new MinimalFrame();
        // L'appel à cette méthode est nécessaire pour afficher la fenêtre.
        mf.setVisible(true);
    }
}
