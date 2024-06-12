// -*- coding: utf-8 -*-
// Time-stamp: <Simple.java   5 nov 2019 18:23:46>

/**
 * CSS dans un fichier de style référencé par le fichier FXML
 */
import java.lang.Exception;
import java.net.URL;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public final class Source extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(final Stage stage) {
        try {
            // Localisation du fichier FXML.
            final URL url = getClass().getResource("layout.fxml");
            // Création du loader
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            // Chargement du FXML
            final VBox root = fxmlLoader.load();
            // Création de la scène
            final Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception exception) {
            System.err.println("Erreur au chargement: " + exception);
        }
        // Titre
        stage.setTitle("CSS file in FXML");
        // Affichage
        stage.show();
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
}
