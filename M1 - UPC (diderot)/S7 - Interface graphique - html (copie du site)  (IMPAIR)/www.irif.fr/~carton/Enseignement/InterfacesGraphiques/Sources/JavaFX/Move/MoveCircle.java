// -*- Coding: utf-8 -*-
// Time-stamp: <MoveCircle.java   5 nov 2019 19:17:45>

/**
 * Implémentation des déplacements
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class MoveCircle extends Application {
    double oldX;
    double oldY;
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        Pane canvas = new Pane();
        // Couleur du panneau avec CSS
        canvas.setStyle("-fx-background-color: black;");
        // Cercle
        Circle circle = new Circle(20.0, Color.RED);
        circle.setCenterX(20);
        circle.setCenterY(20);
        // Ajout du cercle dans le panneau principal
        canvas.getChildren().addAll(circle);
        // La méthode setOnMousePressed est héritée de la classe Node
        circle.setOnMousePressed(e -> {
                // Mémorisation la position de la souris
                oldX = e.getX();
                oldY = e.getY();
            });
        // La méthode addEventHandler est aussi héritée de la classe Node
        circle.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                               e -> {
                                   // Nouvelle position de la souris
                                   double newX = e.getX();
                                   double newY = e.getY();
                                   // Translation du centre de cercle
                                   circle.setCenterX(circle.getCenterX() + newX - oldX);
                                   circle.setCenterY(circle.getCenterY() + newY - oldY);
                                   // Mémorisation la nouvelle position de la souris
                                   oldX = newX;
                                   oldY = newY;
                               });
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(canvas, 300, 300));
        // Titre de la fenêtre de l'application
        stage.setTitle("Move circle");
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
