// -*- Coding: utf-8 -*-
// Time-stamp: <FillCircle.java   3 oct 2019 08:20:42>

/**
 * Animation de la couleur d'un cercle
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.animation.FillTransition;
import javafx.util.Duration;

public class FillCircle extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
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
        // Liaison entre la valeur du slider et le rayon du cercle
        circle.radiusProperty().bind(slider.valueProperty());
        // Animation faisant varier la couleur du cercle
        FillTransition ft = new FillTransition(Duration.millis(10000), circle,
                                               Color.RED, Color.BLUE);
        ft.setCycleCount(50);   // Nombre de cycles à effectuer
        ft.setAutoReverse(true);
        ft.play();
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 210, 180));
        // Titre de la fenêtre de l'application
        stage.setTitle("Animation Fill-Circle");
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
