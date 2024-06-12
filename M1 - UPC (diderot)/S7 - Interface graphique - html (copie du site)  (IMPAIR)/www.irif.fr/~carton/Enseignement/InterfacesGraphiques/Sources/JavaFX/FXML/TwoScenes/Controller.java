// -*- Coding: utf-8 -*-
// Time-stamp: <Controller.java   3 nov 2019 20:51:02>

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {
    private Stage stage;
    private Scene scene;
    // Paramétrage des attributs stage et scene
    public void setStageScene(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }
    // Méthode appelée par le click un bouton
    @FXML
    protected void handleEvent(ActionEvent event) {
        stage.setScene(scene);
    }
}
