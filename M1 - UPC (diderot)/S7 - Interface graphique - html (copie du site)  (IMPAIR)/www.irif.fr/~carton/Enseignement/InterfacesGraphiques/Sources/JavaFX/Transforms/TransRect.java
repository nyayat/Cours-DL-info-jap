// -*- Coding: utf-8 -*-
// Time-stamp: <TransRect.java   6 nov 2019 08:55:40>

/**
 * Transformations d'un rectangle
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;

public class TransRect extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        // Panneau de dessin
        Pane canvas = new Pane();
        root.setCenter(canvas);
        // Identifiant CSS
        canvas.getStyleClass().add("canvas");
        // Rectangle
        Rectangle rectangle = new Rectangle(175.0, 135.0, 100.0, 80.0);
        // Identifiant CSS
        rectangle.getStyleClass().add("rectangle");
        // Ajout du rectangle dans le panneau principal
        canvas.getChildren().add(rectangle);

        // Boutons de contrôle
        // Bouton de translation
        Button traButton = new Button("Translate");
        traButton.setOnAction(event -> {
                rectangle.getTransforms().add(new Translate(5, 5));
            });
        // Bouton de rotation
        Button rotButton = new Button("Rotate");
        rotButton.setOnAction(event -> {
                rectangle.getTransforms().add(new Rotate(5, 225, 175));
            });
        // Bouton de dilatation
        Button scaButton = new Button("Scale");
        scaButton.setOnAction(event -> {
                rectangle.getTransforms().add(new Scale(1.1, 1.2, 225, 175));
            });
        // Bouton de cisaillement
        Button sheButton = new Button("Shear");
        sheButton.setOnAction(event -> {
                rectangle.getTransforms().add(new Shear(0.1, 0.2, 225, 175));
            });
        // Bouton de reset
        Button resButton = new Button("Reset");
        resButton.setOnAction(event -> {
                rectangle.getTransforms().clear();
            });
        // Panneau de contrôle des boutons
        HBox control = new HBox(traButton, rotButton, scaButton,
                                sheButton, resButton);
        root.setTop(control);
        
        // Création de la scène avec des dimensions
        Scene scene = new Scene(root, 450, 350);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        // Titre de la fenêtre de l'application
        stage.setTitle("Transform rectangle");
        // Fermeture propre de l'application
        stage.setOnCloseRequest(e -> Platform.exit());
        // Affichage de la fenêtre
        stage.show();
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
}
