// -*- coding: utf-8 -*-
// Time-stamp: <HelloWorld.java  22 sep 2019 22:15:40>

/**
 * Exemple adapté de la page Wikipedia
 * https://fr.wikipedia.org/wiki/JavaFX
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class HelloWorld extends Application {
    // Point d'entrée du programme
    public static void main(String[] args) {
	// Appel du point d'entrée de l'application JavaFX 
	launch(args); 
    }
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage primaryStage) {
	// Label pour l'affichage
        String javaVersion   = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
	Label label = new Label("Hello JavaFX " + javafxVersion +
				" running on Java " + javaVersion);
	// Création de la scène avec des dimensions
	primaryStage.setScene(new Scene(new StackPane(label), 420, 200));
	// Titre de la fenêtre de l'application
	primaryStage.setTitle("Hello JavaFX");
	// Fermeture propre de l'application
	primaryStage.setOnCloseRequest(e -> Platform.exit());
	// Affichage de la fenêtre
	primaryStage.show();
    }
}
