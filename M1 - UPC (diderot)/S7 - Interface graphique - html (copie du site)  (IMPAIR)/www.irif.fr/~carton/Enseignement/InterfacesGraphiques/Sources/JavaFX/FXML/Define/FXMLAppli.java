// -*- coding: utf-8 -*-
// Time-stamp: <FXMLAppli.java   3 nov 2019 19:17:25>

/**
 * Exemple d'utilisation de FXML avec définitions
 */

import java.net.URL;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class FXMLAppli extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) throws Exception {
        // Localisation du fichier FXML
        final URL url = getClass().getResource("layout.fxml");
        // Création du loader
        final FXMLLoader fxmlLoader = new FXMLLoader(url);
        // Chargement du FXML
        final BorderPane root = fxmlLoader.load();
        // Création de la scène
        stage.setScene(new Scene(root));
        Controller mc = (Controller) fxmlLoader.getController();
        // Taille automatique
        stage.sizeToScene();
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
