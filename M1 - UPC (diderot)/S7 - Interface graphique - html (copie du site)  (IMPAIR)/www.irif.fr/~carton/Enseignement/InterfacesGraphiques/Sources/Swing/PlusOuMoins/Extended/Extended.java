// -*- Coding: utf-8 -*-
// Time-stamp: <Extended.java  23 sep 2020 18:07:45>

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Exemple PlusOuMoins en Modèle/Vue/Contrôleur d'après J. Berstel
 * Ajout d'un bouton "Remise à zéro" et d'un champ de saisie 
 * @version 2.0
 */
class Extended {
    // Commandes possibles
    final String incrAction = "INCR"; // Incrémentation de la donnée
    final String decrAction = "DECR"; // Décrémentation de la donnée
    final String zeroAction = "ZERO"; // Mise à zéro de la donnée
    final String setoAction = "SETO"; // Nouvelle valeur pour la donnée

    // Architecture Modèle/Vue/Contrôleur
    // ATTENTION : le contrôleur doit être contruit avant la vue
    // pour qu'il puisse être écouteur des composants de la vue.
    private Model model = new Model();   
    private Controller controller = new Controller(); 
    private View view = new View();
    
    public Extended() {
        // Affichage de la vue
        view.setVisible(true);
    }

    public static void main(String [] args)
    {
        // Création 
        Extended ext = new Extended();
    }

    /** Vue affichant les données */
    class View extends JFrame {
        final int width  = 150; // Largeur de la fenêtre
        final int height = 100; // Hauteur de la fenêtre
        JLabel label;           // Affichage de la donnée
        View() {
            // Constructeur avec titre
            super("Plus ou moins étendu");
            // Dimension de la fenêtre
            setSize(width, height);
            // Action à faire lorsque la fenêtre est fermée.
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            // Création du champ d'affichage de la valeur
            label = new JLabel(Integer.toString(model.getValue()), 
                               JLabel.CENTER);
            // Création des boutons
            JButton resetButton = new JButton("Remise à zéro");
            resetButton.setActionCommand(zeroAction);
            resetButton.addActionListener(controller);
            JButton plusButton = new JButton("+");
            plusButton.setActionCommand(incrAction);
            plusButton.addActionListener(controller);
            JButton moinsButton = new JButton("-");
            moinsButton.setActionCommand(decrAction);
            moinsButton.addActionListener(controller);
            // Création du champ de saisie
            JTextField textField = new JTextField(10);
            textField.setActionCommand(setoAction);
            textField.addActionListener(controller);
            // Mise en place des éléments dans un panneau
            JPanel mainPanel = (JPanel) getContentPane();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(resetButton, "North");
            mainPanel.add(plusButton, "East");
            mainPanel.add(label, "Center");
            mainPanel.add(moinsButton, "West");
            mainPanel.add(textField, "South");
        }
        // Mise à jour de l'affichage à partir des données du modèle
        void update() {
            label.setText(Integer.toString(model.getValue()));
        }
    }

    /** Contrôleur */
    class Controller implements ActionListener {
        // Action sur reception d'un événement
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            // Switch pas très esthétique mais assez inévitable avec
            // l'architecture MVC.
            if (command == incrAction) 
                model.incrValue(+1);
            else if (command == decrAction) 
                model.incrValue(-1);
            else if (command == zeroAction) 
                model.setValue(0);
            else if (command == setoAction) {
                JTextField textField = (JTextField) event.getSource();
                // Texte saisi par l'utilisateur
                String text = textField.getText();
                // Tentative d'extraire un entier du texte
                try {
                    model.setValue(Integer.parseInt(text));
                }
                // En cas d'échec, pas d'action
                catch (NumberFormatException e) {}
            }
            // Force la vue à être conforme aux données
            view.update();
        }
    }
}

/** Modèle des données */
class Model {
    // Les données sont constituées d'un seul entier.
    private int value;          // Données du modèle
    Model(int value) { this.value = value; }
    Model() { this(0); }
    void setValue(int value) { this.value = value; }
    void incrValue(int incr) { value += incr; }
    int getValue() { return value; }
}


