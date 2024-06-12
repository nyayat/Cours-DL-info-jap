// -*- Coding: utf-8 -*-
// Time-stamp: <RotateTriangle.java   2 oct 2019 17:57:03>

/**
 * Rotation d'un triangle
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.animation.RotateTransition;
import javafx.util.Duration;

public class RotateTriangle extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        BorderPane root = new BorderPane();
        // Triangle
        Polygon triangle = new Polygon(-50.0, 0.0, 50.0, 0.0, 0.0, -70.7);
        // Alternative pour créer le triangle
        // triangle.getPoints().addAll(new Double[]{
        //      -50.0, 0.0,
        //       50.0, 0.0,
        //      0.0, -70.7 });
        triangle.setFill(Color.RED); // Couleur du triangle
        root.setCenter(triangle);
        // Animation faisant varier l'angle du triangle
        RotateTransition rt = new RotateTransition(Duration.millis(3000), triangle);
        rt.setByAngle(360);     // Angle de la rotation
        rt.setCycleCount(50);   // Nombre de cycles à effectuer
        rt.setAutoReverse(true);
        rt.play();
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 210, 200));
        // Titre de la fenêtre de l'application
        stage.setTitle("Animation Rotate-Triangle");
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
