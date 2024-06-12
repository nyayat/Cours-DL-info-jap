// -*- Coding: utf-8 -*-
// Time-stamp: <Bonjour.java  25 sep 2020 07:45:11>

import javax.swing.*;

/**
 * Programme Bonjour Monde en Swing
 * @author O. Carton
 * @version 1.0
 */
class Bonjour {
    public static void main(String [] args) {
        // Comme le premier paramètre est null, le message 
        // s'affiche dans sa propre fenêtre.
        JOptionPane.showMessageDialog(null, "Bonjour Monde");
        System.exit(0);
    }
}
