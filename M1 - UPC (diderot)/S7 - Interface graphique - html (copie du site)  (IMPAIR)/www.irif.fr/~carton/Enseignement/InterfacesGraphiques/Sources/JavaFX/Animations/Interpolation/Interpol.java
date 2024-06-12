// -*- Coding: utf-8 -*-
// Time-stamp: <Interpol.java   8 nov 2019 22:04:30>

/**
 * Variation sur les interpolators
 * @author O. Carton
 * @version 1.0
 */
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.CubicCurveTo;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.beans.binding.Bindings;

public class Interpol extends Application {
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
        startstop.setPrefWidth(90);
        control.getChildren().add(startstop);
        // Action du bouton : start ou stop suivant l'état de l'animation
        startstop.setOnAction(e-> {
                if (animation.getStatus() == Animation.Status.RUNNING)
                    animation.stop();
                else
                    animation.play();
            });

        // Liste des interpolators
        List<Interpolator> interpolators =
            Stream.of(Interpolator.EASE_BOTH,
                      Interpolator.EASE_IN,
                      Interpolator.EASE_OUT,
                      Interpolator.LINEAR,
                      Interpolator.DISCRETE,
		      Interpolator.SPLINE(0.5,0.0,0.5,1.0),
		      Interpolator.SPLINE(1.0,0.0,0.0,1.0),
		      new Quadratic(false),
		      new Quadratic(true)
		      ).collect(Collectors.toList());
        // Premier ComboBox
        ComboBox<Interpolator> combo1 =
	    new ComboBox<Interpolator>
	    (FXCollections.observableList(interpolators));
        combo1.getSelectionModel().selectFirst();
        control.getChildren().add(combo1);
        // Second ComboBox
        ComboBox<Interpolator> combo2 =
	    new ComboBox<Interpolator>
	    (FXCollections.observableList(interpolators));
        combo2.getSelectionModel().selectFirst();
        control.getChildren().add(combo2);

        // Panneau de dessin
        Pane canvas = new Pane();
        root.setCenter(canvas);
        // Cercle
        Circle circle = new Circle(5.0f, Color.RED);
        canvas.getChildren().add(circle);

        // Premier déplacement
        Path path1 = new Path();
        path1.getElements().add(new MoveTo(50, 100));
        path1.getElements().add(new LineTo(250.0,100.0));
        // Animation
        PathTransition pt1 = new PathTransition();
        pt1.setDuration(Duration.millis(1000));
        pt1.setPath(path1);
        pt1.setNode(circle);
        // Lien entre le ComboBox et l'animation
        pt1.interpolatorProperty().bind(combo1.valueProperty());

        // Courbe du second déplacement
        Path path2 = new Path();
        path2.getElements().add(new MoveTo(250.0, 100));
        path2.getElements().add(new LineTo(450.0,100.0));
        // Animation
        PathTransition pt2 = new PathTransition();
        pt2.setDuration(Duration.millis(1000));
        pt2.setPath(path2);
        pt2.setNode(circle);
        // Lien entre le ComboBox et l'animation
        pt2.interpolatorProperty().bind(combo2.valueProperty());

        // Transition combinée
        animation = new SequentialTransition(pt1, pt2);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();
        // Lien entre le texte du bouton start/stop et l'état de l'animation
        startstop.textProperty().bind(Bindings.when(animation.statusProperty().isEqualTo(Animation.Status.RUNNING)).then("Stop").otherwise("Start"));
        
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 500, 200));
        // Titre de la fenêtre de l'application
        stage.setTitle("Interpolators");
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

