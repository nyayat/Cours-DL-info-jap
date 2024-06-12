// -*- Coding: utf-8 -*-
// Time-stamp: <Controller.java  25 oct 2019 16:55:51>

// Nom du package qui doit être le nom du répertoire
package Controller;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {
    @FXML private Label label;
    
    // Méthode appelée par le click sur le bouton
    @FXML
    protected void handleEvent(ActionEvent event) {
        label.setText("Merci !!");
    }
}
