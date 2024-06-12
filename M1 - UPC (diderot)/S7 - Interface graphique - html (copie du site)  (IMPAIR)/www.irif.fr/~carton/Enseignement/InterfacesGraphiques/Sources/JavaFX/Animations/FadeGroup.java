// -*- Coding: utf-8 -*-
// Time-stamp: <FadeGroup.java   6 nov 2019 18:07:17>

/**
 * Animation de l'opacité d'un groupe
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class FadeGroup extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        BorderPane root = new BorderPane();
        // Slider
        Slider slider = new Slider(0.0, 5.0, 1.0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        root.setTop(slider);

        // Panneau de dessin
        Pane canvas = new Pane();
        root.setCenter(canvas);
        // Identifiant CSS
        canvas.getStyleClass().add("canvas");
        // Triangle
        Polygon triangle = new Polygon(105.0, 70.0,
                                       105.0, 160.0,
                                       175.0, 115.0);
        triangle.setFill(Color.BLUE);
        // Identifiant CSS
        triangle.getStyleClass().add("triangle");
        // Cercle
        Circle circle = new Circle(215.0, 115.0, 40.0);
        circle.setFill(Color.RED);
        // Group
        Group group = new Group(triangle, circle);
        // Ajout du groupe dans le panneau principal
        canvas.getChildren().add(group);

        // Animation faisant varier l'opacité du groupe
        FadeTransition ft = new FadeTransition(Duration.millis(5000), group);
        ft.setFromValue(1.0);   // Opacité initiale
        ft.setToValue(0.0);     // Opacité finale
        // Nombre de cycles à effectuer
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        // Liaison entre la valeur du slider et la vitesse d'animation
        ft.rateProperty().bind(slider.valueProperty());
        ft.play();
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 350, 250));
        // Titre de la fenêtre de l'application
        stage.setTitle("Animation Fade-Group");
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
