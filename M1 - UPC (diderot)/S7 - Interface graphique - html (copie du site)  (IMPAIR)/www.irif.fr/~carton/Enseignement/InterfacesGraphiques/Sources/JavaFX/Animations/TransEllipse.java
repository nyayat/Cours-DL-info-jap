// -*- Coding: utf-8 -*-
// Time-stamp: <TransEllipse.java  15 nov 2019 16:46:51>

/**
 * Animation multiple d'une ellipse avec Timeline
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.CubicCurveTo;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.binding.Bindings;

public class TransEllipse extends Application {
    Animation animation;        // Animation globale
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        BorderPane root = new BorderPane();
        // Panneau de contrôle
        HBox control = new HBox();
        root.setTop(control);
        // Boutons start/stop 
        Button startstop = new Button("Start");
        control.getChildren().add(startstop);
        // Action du bouton : start ou stop suivant l'état de l'animation
        startstop.setOnAction(e-> {
                if (animation.getStatus() == Animation.Status.RUNNING)
                    animation.stop();
                else
                    animation.play();
            });
        // Bouton pause
        Button pause = new Button("Pause");
        control.getChildren().add(pause);
        pause.setOnAction(e->animation.pause());
        // Slider
        Slider slider = new Slider(0.0, 2.0, 1.0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        control.getChildren().add(slider);
        // Pour que le slider utilise l'espace restant
        HBox.setHgrow(slider, Priority.ALWAYS);
        // Cercle
        Ellipse ellipse = new Ellipse(200, 120, 5, 5); 
        // Panneau de dessin
        Pane canvas = new Pane();
        canvas.getChildren().add(ellipse);
        root.setCenter(canvas);
        // KeyFrame pour les valeurs initiales des propriétés
        KeyFrame keyFrameStart =
            new KeyFrame(Duration.ZERO,
                         new KeyValue(ellipse.strokeWidthProperty(), 1),
                         new KeyValue(ellipse.strokeProperty(), Color.AQUA),
                         new KeyValue(ellipse.fillProperty(), Color.RED),
                         new KeyValue(ellipse.radiusXProperty(), 50),
                         new KeyValue(ellipse.radiusYProperty(), 120));
        // KeyFrame pour les valeurs finales des propriétés
        KeyFrame keyFrameStop =
            new KeyFrame(Duration.millis(4000),
                         new KeyValue(ellipse.strokeWidthProperty(), 30),
                         new KeyValue(ellipse.strokeProperty(), Color.RED),
                         new KeyValue(ellipse.fillProperty(), Color.AQUA),
                         new KeyValue(ellipse.radiusXProperty(), 150),
                         new KeyValue(ellipse.radiusYProperty(), 50));

        // TimeLine
        animation = new Timeline(keyFrameStart, keyFrameStop);
        animation.setAutoReverse(true);
        animation.setCycleCount(Animation.INDEFINITE);
        // Liaison entre la valeur du slider et la vitesse d'animation
        animation.rateProperty().bind(slider.valueProperty());
        // Liaison entre le texte du bouton et l'état de l'animation
        startstop.textProperty().bind(Bindings.when(animation.statusProperty().isEqualTo(Animation.Status.RUNNING)).then("Stop").otherwise("Start"));

        
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 400, 300));
        // Titre de la fenêtre de l'application
        stage.setTitle("Animation multiple");
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
