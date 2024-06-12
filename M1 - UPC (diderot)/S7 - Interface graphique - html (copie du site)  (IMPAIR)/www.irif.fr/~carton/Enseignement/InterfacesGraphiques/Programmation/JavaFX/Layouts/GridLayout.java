// -*- Coding: utf-8 -*-
// Time-stamp: <GridLayout.java  20 sep 2019 22:21:05>

/**
 * Utilisation de GridPane
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class GridLayout extends Application {
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
        // Panneau pour les ajouts
        GridPane pane = new GridPane();
        root.setCenter(pane);
        // Bouton pour les ajouts
        Button button = new Button("Add");
        root.setTop(button);
        // Action du bouton
        button.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    pane.add(new Button(Integer.toString(nbrlabels)), nbrlabels/3, nbrlabels % 3);
		    nbrlabels++;
                }
            });
        // Titre de la fenêtre de l'application
        primaryStage.setTitle("Utilisation de GridPane");
        // Fermeture propre de l'application
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        // Création de la scène avec des dimensions
        primaryStage.setScene(new Scene(root, 210, 100));
        // Affichage de la fenêtre
        primaryStage.show();
    }
    // Nombre de bouton ajoutés
    private int nbrlabels = 0;
}
