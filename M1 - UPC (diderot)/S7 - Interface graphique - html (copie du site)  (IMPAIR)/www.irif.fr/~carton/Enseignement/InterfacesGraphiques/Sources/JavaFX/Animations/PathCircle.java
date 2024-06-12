// -*- Coding: utf-8 -*-
// Time-stamp: <PathCircle.java   4 oct 2019 14:46:46>

/**
 * Animation d'un cercle le long d'un chemin
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.CubicCurveTo;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class PathCircle extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        BorderPane root = new BorderPane();
        // Slider
        Slider slider = new Slider(0.0, 2.0, 1.0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        root.setTop(slider);
        // Cercle
        Circle circle = new Circle(20.0f, Color.RED);
        // Panneau de dessin
        Pane canvas = new Pane();
        canvas.getChildren().add(circle);
        root.setCenter(canvas);
        // Courbe du déplacement
        Path path = new Path();
        // Point de départ
        path.getElements().add(new MoveTo(200, 100));
        // Boucle à droite
        path.getElements().add(new CubicCurveTo(400.0,300.0,400.0,-100.0,200.0,100.0));
        // Boucle à gauche
        path.getElements().add(new CubicCurveTo(0.0,300.0,0.0,-100.0,200.0,100.0));
        // Animation déplaçant le cercle
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(2000));
        pt.setPath(path);
        pt.setNode(circle);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(false);
        // Liaison entre la valeur du slider et la vitesse d'animation
        pt.rateProperty().bind(slider.valueProperty());
        pt.play();
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 400, 250));
        // Titre de la fenêtre de l'application
        stage.setTitle("Animation Path-Circle");
        // Fermeture propre de l'application
        stage.setOnCloseRequest(e -> Platform.exit());
        // Affichage de la fenêtre
        stage.show();
     }
    // Point d'entrée du programme
    public static void main(String[] args) {
        launch(args);
    }
}

