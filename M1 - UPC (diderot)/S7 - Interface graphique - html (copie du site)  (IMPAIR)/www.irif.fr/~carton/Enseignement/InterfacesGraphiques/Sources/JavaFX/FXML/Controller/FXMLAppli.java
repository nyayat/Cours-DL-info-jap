// -*- coding: utf-8 -*-
// Time-stamp: <FXMLAppli.java  24 oct 2019 20:27:21>

/**
 * Exemple d'utilisation de FXML avec contrôleur
 */

// Nom du package qui doit être le nom du répertoire
package Controller;

import java.lang.Exception;
import java.net.URL;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class FXMLAppli extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) throws Exception {
        try {
            // Localisation du fichier FXML
            final URL url = getClass().getResource("layout.fxml");
            // Création du loader
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            // Chargement du FXML
            final Parent root = fxmlLoader.load();
            // Création de la scène
            stage.setScene(new Scene(root));
        } catch (Exception exception) {
            System.err.println("Erreur au chargement: " + exception);
        }
        // Titre
        stage.setTitle("Application FXML");
        // Affichage
        stage.show();
    }
     // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args);
    }
}
