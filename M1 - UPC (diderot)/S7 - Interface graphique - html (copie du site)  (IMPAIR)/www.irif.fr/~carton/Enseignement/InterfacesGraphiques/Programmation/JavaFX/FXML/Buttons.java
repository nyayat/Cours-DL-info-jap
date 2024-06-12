// -*- coding: utf-8 -*-
// Time-stamp: <Buttons.java  19 sep 2019 16:26:55>

/**
 * Exemple de scene vide
 * from the Web
 */
import java.lang.Exception;
import java.net.URL;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public final class Buttons extends Application {
    @Override
    public void start(final Stage primaryStage) {
	try {
	    // Localisation du fichier FXML.
	    final URL url = getClass().getResource("buttons.fxml");
	    // Création du loader
	    final FXMLLoader fxmlLoader = new FXMLLoader(url);
	    // Chargement du FXML
	    // Le cast (AnchorPane) n'est pas nécessaire grâce au type
	    // générique <T> T load() de la méthode load().
	    // Le cast est de toutes façons réalisé à l'exécution
	    // par le bytecode.
	    final AnchorPane root = (AnchorPane) fxmlLoader.load();
	    // Création de la scène
	    final Scene scene = new Scene(root, 300, 250);
	    primaryStage.setScene(scene);
	} catch (Exception exception) {
	    System.err.println("Erreur au chargement: " + exception);
	}
	// Titre
	primaryStage.setTitle("Test FXML");
	// Affichage
	primaryStage.show();
    }
    public static void main(String[] args) {
	launch(args);
    }
}
