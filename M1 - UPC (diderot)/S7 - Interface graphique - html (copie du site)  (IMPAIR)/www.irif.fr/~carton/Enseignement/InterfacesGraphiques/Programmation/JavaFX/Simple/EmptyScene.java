// -*- coding: utf-8 -*-
// Time-stamp: <EmptyScene.java  13 sep 2019 18:46:36>

/**
 * Exemple de scene vide
 * From W. Zielonka
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class EmptyScene extends Application {
    // Point d'entrée du programme
    public static void main(String[] args) {
	// Appel du point d'entrée de l'application JavaFX (voir ci-dessous)
        launch(args); 
    }

    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage primaryStage) {
	// Groupe Vide
	Group root = new Group();
	// Scène contenant le groupe vide
	Scene scene = new Scene(root, 300, 200);
	// Couleur de background : la propriété fill 
	scene.setFill(Color.DEEPSKYBLUE);
	// Titre
	primaryStage.setTitle("Scène vide");
	// mettre Scene sur Stage
	primaryStage.setScene(scene);
        // Affichage de la fenêtre
        primaryStage.show();
    }
}
