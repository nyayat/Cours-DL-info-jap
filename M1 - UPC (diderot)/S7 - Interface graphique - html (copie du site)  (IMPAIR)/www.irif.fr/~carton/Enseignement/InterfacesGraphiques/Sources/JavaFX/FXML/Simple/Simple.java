// -*- coding: utf-8 -*-
// Time-stamp: <Simple.java   5 nov 2019 16:53:09> 

/**
 * Exemple de layout FXML sans action
 */
import java.lang.Exception;
import java.net.URL;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public final class Simple extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(final Stage stage) {
        try {
            // Localisation du fichier FXML.
            final URL url = getClass().getResource("layout.fxml");
            // Création du loader
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            // Chargement du FXML
            // Le cast (AnchorPane) n'est pas nécessaire grâce au type
            // générique <T> T load() de la méthode load().
            // Le cast est de toutes façons réalisé à l'exécution
            // par le bytecode.
            final AnchorPane root = (AnchorPane) fxmlLoader.load();
            // Création de la scène
            final Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception exception) {
            System.err.println("Erreur au chargement: " + exception);
        }
        // Titre
        stage.setTitle("FXML Simple");
        // Affichage
        stage.show();
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
}
