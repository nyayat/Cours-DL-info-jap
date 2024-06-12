// -*- Coding: utf-8 -*-
// Time-stamp: <ParallelCircle.java   3 oct 2019 15:33:21>

/**
 * Animation multiple d'un cercle 
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.CubicCurveTo;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.binding.Bindings;

public class ParallelCircle extends Application {
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
        Circle circle = new Circle(20.0f, Color.RED);
        circle.setCenterX(50.0f);
        circle.setCenterY(20.0f);
        // Panneau de dessin
        Pane canvas = new Pane();
        canvas.getChildren().add(circle);
        root.setCenter(canvas);
        // Courbe du déplacement
        Path path = new Path();
        path.getElements().add(new MoveTo(50, 20));
        path.getElements().add(new CubicCurveTo(0.0,300.0,350.0,300.0,350.0,20.0));
        // Animation déplaçant le cercle
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(1000));
        pt.setPath(path);
        pt.setNode(circle);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        // Liaison entre la valeur du slider et la vitesse d'animation
        // Exception :
        //    java.lang.IllegalArgumentException:
        //      Cannot set rate of embedded animation while running.
        pt.rateProperty().bind(slider.valueProperty());
        // Animation faisant varier la couleur du cercle
        FillTransition ft = new FillTransition(Duration.millis(1000), circle,
                                               Color.RED, Color.BLUE);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        // Animation multiple
        animation = new ParallelTransition(ft, pt);
        // Liaison entre la valeur du slider et la vitesse d'animation globale
        // animation.rateProperty().bind(slider.valueProperty());
        // Changement du texte du bouton start/stop en fonction de l'état de l'animation
        startstop.textProperty().bind(Bindings.when(animation.statusProperty().isEqualTo(Animation.Status.RUNNING)).then("Stop").otherwise("Start"));
        // Le binding précédent est équivalent au code suivant
        // animation.statusProperty().addListener(new ChangeListener<Animation.Status>() {
        //         public void changed(ObservableValue<? extends Animation.Status> ov,
        //                             Animation.Status oldVal,
        //                             Animation.Status newVal) {
        //             if (newVal == Animation.Status.RUNNING)
        //                 startstop.setText("Stop");
        //             else
        //                 startstop.setText("Start");
        //         }
        //     });
        // Liaison de l'activation du bouton pause avec l'état de l'animation
        pause.disableProperty().bind(animation.statusProperty().isNotEqualTo(Animation.Status.RUNNING));
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 400, 300));
        // Titre de la fenêtre de l'application
        stage.setTitle("Animation multiple Circle");
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

