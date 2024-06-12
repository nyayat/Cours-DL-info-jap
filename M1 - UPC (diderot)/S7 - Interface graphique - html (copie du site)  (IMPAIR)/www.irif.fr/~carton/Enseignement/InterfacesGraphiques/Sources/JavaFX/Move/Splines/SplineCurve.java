// -*- coding: utf-8; c-basic-offset: 2 -*-
// Time-stamp: <SplineCurve.java  13 nov 2019 11:15:49>

/**
 * Dessins de Splines
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;

public class SplineCurve extends Application {
  Circle start = null;          // Pas encore de point de départ
  // Point d'entrée de l'application JavaFX
  @Override
  public void start(Stage stage) {
    BorderPane root = new BorderPane();
    // Panneau de dessin
    Pane pane = new Pane();
    root.setCenter(pane);

    // Événements dans le anneau de dessin
    pane.setOnMouseClicked(event -> {
        // Bouton droit de la souris
        if (event.getButton() == MouseButton.SECONDARY) {
          // Le nouveau point est le point d'arrivée
          double endX = event.getX();
          double endY = event.getY();
          Circle end  = new Circle(endX, endY, 5);
          makeDraggable(end);
          if (start == null) {
            // Cas du premier point
            // Ajout au panneau
            pane.getChildren().add(end);
            // Le nouveau point devient le dernier ajouté
            start = end;
          } else {
            // Cas des autres points
            // Point de départ
            double startX = start.getCenterX();
            double startY = start.getCenterY();
            // Premier point de contrôle
            double controlX1 = 0.8*startX + 0.2*endX;
            double controlY1 = 0.8*startY + 0.2*endY;
            Circle control1 = new Circle(controlX1, controlY1, 5);
            makeDraggable(control1);
            // Première poignée
            Line handle1 = new Line(startX, startY, controlX1, controlY1);
            handle1.startXProperty().bind(start.centerXProperty());
            handle1.startYProperty().bind(start.centerYProperty());
            handle1.endXProperty().bind(control1.centerXProperty());
            handle1.endYProperty().bind(control1.centerYProperty());
            // Second point de contrôle
            double controlX2 = 0.2*startX + 0.8*endX;
            double controlY2 = 0.2*startY + 0.8*endY;
            Circle control2 = new Circle(controlX2, controlY2, 5);
            makeDraggable(control2);
            // Second poignée
            Line handle2 = new Line(controlX2, controlY2, endX, endY);
            handle2.startXProperty().bind(control2.centerXProperty());
            handle2.startYProperty().bind(control2.centerYProperty());
            handle2.endXProperty().bind(end.centerXProperty());
            handle2.endYProperty().bind(end.centerYProperty());
            // Spline
            CubicCurve curve = new CubicCurve();
            curve.startXProperty().bind(start.centerXProperty());
            curve.startYProperty().bind(start.centerYProperty());
            curve.controlX1Property().bind(control1.centerXProperty());
            curve.controlY1Property().bind(control1.centerYProperty());
            curve.controlX2Property().bind(control2.centerXProperty());
            curve.controlY2Property().bind(control2.centerYProperty());
            curve.endXProperty().bind(end.centerXProperty());
            curve.endYProperty().bind(end.centerYProperty());
            // Ajout au panneau
            pane.getChildren().addAll(curve, handle1, handle2, control1, control2, end);
            // Le nouveau point devient le dernier ajouté
            start = end;
          }
        }
      });

    // Mise en place de la scène
    Scene scene = new Scene(root, 500, 400);
    // Chargement du fichier de style
    scene.getStylesheets().add("style.css");
    stage.setScene(scene);
    stage.setTitle("Splines");
    stage.show();
  }
  // Point d'entrée du programme
  public static void main(String[] args) {
    // Appel du point d'entrée de l'application JavaFX 
    launch(args); 
  }
  // Cette fonction ajoute des écouteurs au cercle pour 
  // qu'il puisse être déplacé à la souris.
  private static void makeDraggable(Circle circle) {
    // Classe pour mémoriser le début du déplacement
    class Start {
      double X;
      double Y;
    }
    final Start start = new Start();
    circle.setOnMousePressed(event -> {
        // Mémorisation la position de la souris
        start.X = event.getX();
        start.Y = event.getY();
        event.consume();
      });
    circle.setOnMouseDragged(event -> {
        // Nouvelle position de la souris
        double endX = event.getX();
        double endY = event.getY();
        // Translation du centre de cercle
        circle.setCenterX(circle.getCenterX() + endX - start.X);
        circle.setCenterY(circle.getCenterY() + endY - start.Y);
        // Mémorisation la nouvelle position de la souris
        start.X = endX;
        start.Y = endY;
        event.consume();
     });
    circle.setOnMouseReleased(event -> {
        event.consume();
      });
  }
}
