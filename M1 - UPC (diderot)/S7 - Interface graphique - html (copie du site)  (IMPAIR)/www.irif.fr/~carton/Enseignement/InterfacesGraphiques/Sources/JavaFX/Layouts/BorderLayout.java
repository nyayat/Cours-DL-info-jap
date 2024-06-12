// -*- Coding: utf-8 -*-
// Time-stamp: <BorderLayout.java  20 sep 2019 22:35:54>

/**
 * Utilisation de BorderPane
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;

public class BorderLayout extends Application {
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }

    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage primaryStage) {
        // Panneau principal
        BorderPane root = new BorderPane();
        Button topButton = new Button("Top");
        root.setAlignment(topButton, Pos.CENTER);
        root.setTop(topButton);
        Button leftButton = new Button("Left");
        root.setAlignment(leftButton, Pos.CENTER);
        root.setLeft(leftButton);
        Button centerButton = new Button("Center");
        root.setAlignment(centerButton, Pos.CENTER);
        root.setCenter(centerButton);
        Button rightButton = new Button("Right");
        root.setAlignment(rightButton, Pos.CENTER);
        root.setRight(rightButton);
        Button bottomButton = new Button("Bottom");
        root.setAlignment(bottomButton, Pos.CENTER);
        root.setBottom(bottomButton);

        // Titre de la fenêtre de l'application
        primaryStage.setTitle("Utilisation de BorderPane");
        // Fermeture propre de l'application
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        // Création de la scène avec des dimensions
        primaryStage.setScene(new Scene(root, 210, 100));
        // Affichage de la fenêtre
        primaryStage.show();
    }
}
