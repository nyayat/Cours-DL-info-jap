// -*- Coding: utf-8 -*-
// Time-stamp: <ScaleCircle.java   8 nov 2019 19:04:58>

/**
 * Animation de la taille d'un cercle
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
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class ScaleCircle extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        BorderPane root = new BorderPane();
        // Slider
        Slider slider = new Slider(10.0, 40.0, 20.0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(false);
        root.setTop(slider);
        // Cercle
        Circle circle = new Circle(20.0, Color.RED);
        root.setCenter(circle);
        // Liaison entre la valeur du slider et le rayon du cercle
        circle.radiusProperty().bind(slider.valueProperty());
        // Animation faisant varier les dimensions du cercle
        ScaleTransition st = new ScaleTransition(Duration.millis(2000), circle);
        st.setByX(3.0f);        // Facteur horizontal
        st.setByY(1.5f);        // Facteur vertical
        st.setCycleCount(50);   // Nombre de cycles à effectuer
        st.setAutoReverse(true);
        st.play();
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 350, 250));
        // Titre de la fenêtre de l'application
        stage.setTitle("Animation Scale-Circle");
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
