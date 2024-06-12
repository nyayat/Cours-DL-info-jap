// -*- Coding: utf-8 -*-
// Time-stamp: <Slider3Circles.java  27 sep 2019 07:37:14>

/**
 * Binding unidirectionnel entre un Slider et trois Cercles
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.beans.binding.Bindings;

public class Slider3Circles extends Application {
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage primaryStage) {
        // Panneau principal
        BorderPane root = new BorderPane();
        // Slider
        Slider slider = new Slider(0.0, 50.0, 20.0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        root.setTop(slider);
        // Cercle de gauche
        Circle lcircle = new Circle(30.0, Color.RED);
        root.setAlignment(lcircle, Pos.CENTER);
        root.setLeft(lcircle);
        // Valeur 50-value
        lcircle.radiusProperty().bind(slider.valueProperty().negate().add(50.0));
        // Cercle du centre
        Circle ccircle = new Circle(20.0, Color.RED);
        root.setCenter(ccircle);
        ccircle.radiusProperty().bind(slider.valueProperty());
        // Cercle de droite
        Circle rcircle = new Circle(20.0, Color.RED);
        root.setAlignment(rcircle, Pos.CENTER);
        root.setRight(rcircle);
        // Valeur 50-value
        // rcircle.radiusProperty().bind(slider.valueProperty().negate().add(50.0));
	rcircle.radiusProperty().bind(Bindings.add(Bindings.negate(slider.valueProperty()), 50));
        // Création de la scène avec des dimensions
        primaryStage.setScene(new Scene(root, 210, 180));
        // Titre de la fenêtre de l'application
        primaryStage.setTitle("Binding Slider-3Circles");
        // Fermeture propre de l'application
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        // Affichage de la fenêtre
        primaryStage.show();
    }
}
