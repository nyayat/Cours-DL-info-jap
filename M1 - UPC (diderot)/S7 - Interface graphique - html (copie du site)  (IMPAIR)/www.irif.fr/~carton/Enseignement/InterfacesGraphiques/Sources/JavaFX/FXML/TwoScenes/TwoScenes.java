// -*- coding: utf-8 -*-
// Time-stamp: <TwoScenes.java   3 nov 2019 20:51:29>

/**
 * Exemple d'utilisation de deux fichiers FXML 
 */

import java.net.URL;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class TwoScenes extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) throws Exception {
        // Scène 1
        // Localisation du fichier FXML
        final URL url1 = getClass().getResource("layout1.fxml");
        // Création du loader
        final FXMLLoader fxmlLoader1 = new FXMLLoader(url1);
        // Chargement du FXML
        final VBox root1 = fxmlLoader1.load();
        // Création de la scène
        Scene scene1 = new Scene(root1);
        // Contrôleur
        Controller controller1 = (Controller) fxmlLoader1.getController();

        // Scène 2
        // Localisation du fichier FXML
        final URL url2 = getClass().getResource("layout2.fxml");
        // Création du loader
        final FXMLLoader fxmlLoader2 = new FXMLLoader(url2);
        // Chargement du FXML
        final HBox root2 = fxmlLoader2.load();
        // Création de la scène
        Scene scene2 = new Scene(root2);
        // Contrôleur
        Controller controller2 = (Controller) fxmlLoader2.getController();

        // Paramètrages des contrôleurs
        controller2.setStageScene(stage, scene1);
        controller1.setStageScene(stage, scene2);
        // Scène par défaut
        stage.setScene(scene1);
        // Titre de la fenêtre de l'application
        stage.setTitle("Changement de décor");
        // Affichage
        stage.show();
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args);
    }
}
