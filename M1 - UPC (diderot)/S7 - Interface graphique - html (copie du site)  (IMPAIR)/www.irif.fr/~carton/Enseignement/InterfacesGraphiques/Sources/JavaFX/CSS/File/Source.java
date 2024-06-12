// -*- coding: utf-8 -*-
// Time-stamp: <Simple.java   5 nov 2019 19:27:26>

/**
 * CSS dans un fichier chargé par le source
 * Exemple pris sur Stackoverflow puis modifié
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Source extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        // Ajout direct des règles CSS au panneau
        // La méthode toExternalForm() transforme l'URL en chaîne
        // pane.getStylesheets().add(
        //         getClass().getResource("style.css").toExternalForm()
        // );
        // pane.getStylesheets().add("style.css");
        // Création du bouton et ajout dans le panneau
        Button button = new Button("Black");
        button.setPrefWidth(120);
        pane.setCenter(button);
        // Mise en place de la scène
        Scene scene = new Scene(pane, 150, 150);
        // Chargement du fichier de style
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.setTitle("CSS loaded by the source");
        stage.show();
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
}
