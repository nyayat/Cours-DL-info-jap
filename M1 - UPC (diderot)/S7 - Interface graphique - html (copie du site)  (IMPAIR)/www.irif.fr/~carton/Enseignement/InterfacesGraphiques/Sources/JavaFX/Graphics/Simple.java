// -*- Coding: utf-8 -*-
// Time-stamp: <Simple.java   6 nov 2019 22:37:26>

/**
 * Canvas
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Simple extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        BorderPane root = new BorderPane();

	// Canvas
	final Canvas canvas = new Canvas(350,250);
	root.setCenter(canvas);

	// Dessin dans le canvas
	GraphicsContext gc = canvas.getGraphicsContext2D();
 	gc.setFill(Color.RED);
	gc.fillRect(125,75,100,100);

	// Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 350, 250));
        // Titre de la fenêtre de l'application
        stage.setTitle("Canvas simplissime");
        // Fermeture propre de l'application
        stage.setOnCloseRequest(e -> Platform.exit());
        // Affichage de la fenêtre
        stage.show();
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
}
