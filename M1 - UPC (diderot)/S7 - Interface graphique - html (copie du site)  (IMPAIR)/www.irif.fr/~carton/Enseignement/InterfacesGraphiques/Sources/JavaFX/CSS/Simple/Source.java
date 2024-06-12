// -*- coding: utf-8 -*-
// Time-stamp: <Simple.java   5 nov 2019 18:18:46>

/**
 * CSS directement dans le source java
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Source extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        Pane pane = new Pane();
        // Style
        pane.setStyle("-fx-border-width: 3;" +
                      "-fx-border-color: black;" +
                      "-fx-border-insets: 4;");

        // Mise en place de la scène
        Scene scene = new Scene(pane, 150, 150);
        stage.setScene(scene);
        stage.setTitle("CSS in source");
        stage.show();
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
}
