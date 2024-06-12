// -*- Coding: utf-8 -*-
// Time-stamp: <SliderCircle.java  22 sep 2019 21:35:26>

/**
 * Binding unidirectionnel entre un Slider et un Cercle (pour le rayon)
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
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

public class SliderCircle extends Application {
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
        // Cercle
        Circle circle = new Circle(20.0, Color.RED);
        root.setCenter(circle);
        // Laison entre la valeur du slider et le rayon du cercle
        circle.radiusProperty().bind(slider.valueProperty());
        // La ligne précédente remplace le bout de code suivant
        // slider.valueProperty().addListener(new ChangeListener<Number>() {
        //      public void changed(ObservableValue<? extends Number> ov,
        //                      Number oldVal, Number newVal) {
        //          circle.setRadius(newVal.doubleValue());
        //     }
        // });
        // Création de la scène avec des dimensions
        primaryStage.setScene(new Scene(root, 210, 180));
        // Titre de la fenêtre de l'application
        primaryStage.setTitle("Binding Slider-Circle");
        // Fermeture propre de l'application
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        // Affichage de la fenêtre
        primaryStage.show();
    }
}
