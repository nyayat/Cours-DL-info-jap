// -*- Coding: utf-8 -*-
// Time-stamp: <TextAnimation.java   6 nov 2019 17:33:20>

/**
 * Animation de texte
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TextAnimation extends Application {
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

        // Chaînes
        String content = "Il était une fois ...";
        int length = content.length();
        // Zone de texte
        Text text = new Text(10, 20, "");
        text.setFont(new Font(25));
        root.setCenter(text);

        // Animation faisant varier le texte
        Animation animation = new Transition() {
                {
                    setCycleDuration(Duration.millis(2000));
                }
                protected void interpolate(double frac) {
                    final int n = Math.round(length * (float) frac);
                    text.setText(content.substring(0, n));
                }
            };
        // Nombre de cycles à effectuer
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setAutoReverse(true);
        // Liaison de la vitesse de l'animation avec le curseur
        animation.rateProperty().bind(slider.valueProperty());
        animation.play();
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 250, 180));
        // Titre de la fenêtre de l'application
        stage.setTitle("Animation de texte");
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
