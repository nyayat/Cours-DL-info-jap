// -*- coding: utf-8 -*-
// Time-stamp: <CSSButton.java  18 sep 2019 11:59:03>

/**
 * Exemple pris sur Stackoverflow puis modifié
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class CSSButton extends Application
{
    public static void main(String[] args) 
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        BorderPane pane = new BorderPane();
        // Ajout des règles CSS au panneau
	// La méthode toExternalForm() transforme l'URL en chaîne
        pane.getStylesheets().add(
                getClass().getResource("button.css").toExternalForm()
        );
        // Création du bouton et ajout dans le panneau
        Button button = new Button("Back");
        button.setPrefWidth(120);
        pane.setCenter(button);
        // Mise en place de la scène
        Scene scene = new Scene(pane, 150, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Test CSS");
        primaryStage.show();
    }
}
