// -*- coding: utf-8; c-basic-offset: 2 -*-
// Time-stamp: <BezierCurve.java  13 nov 2019 11:17:14>

/**
 * Dessins de courbes de Bezier
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;

public class BezierCurve extends Application {
  // Tableau pour les quatre derniers points
  Circle[] points = new Circle[4];
  // Nombre de points
  int nbrpoints = 0;
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
          double lastX = event.getX();
          double lastY = event.getY();
          Circle last = new Circle(lastX, lastY, 5);
          pane.getChildren().add(last);
          makeDraggable(last);
          // Décalage des trois points précédents
          for(int i = 0; i < 3; i++)
            points[i] = points[i+1];
          // Ajout du dernier point
          points[3] = last;
          nbrpoints++;
          if (nbrpoints >= 4) {
            // Bezier curve
            CubicCurve curve = new CubicCurve();
            curve.startXProperty().bind(points[0].centerXProperty().multiply(0.166).add(points[1].centerXProperty().multiply(0.666)).add(points[2].centerXProperty().multiply(0.166)));
            curve.startYProperty().bind(points[0].centerYProperty().multiply(0.166).add(points[1].centerYProperty().multiply(0.666)).add(points[2].centerYProperty().multiply(0.166)));
            curve.controlX1Property().bind(points[1].centerXProperty().multiply(0.666).add(points[2].centerXProperty().multiply(0.333)));
            curve.controlY1Property().bind(points[1].centerYProperty().multiply(0.666).add(points[2].centerYProperty().multiply(0.333)));
            curve.controlX2Property().bind(points[1].centerXProperty().multiply(0.333).add(points[2].centerXProperty().multiply(0.666)));
            curve.controlY2Property().bind(points[1].centerYProperty().multiply(0.333).add(points[2].centerYProperty().multiply(0.666)));
            curve.endXProperty().bind(points[1].centerXProperty().multiply(0.166).add(points[2].centerXProperty().multiply(0.666)).add(points[3].centerXProperty().multiply(0.166)));
            curve.endYProperty().bind(points[1].centerYProperty().multiply(0.166).add(points[2].centerYProperty().multiply(0.666)).add(points[3].centerYProperty().multiply(0.166)));
            // Ajout au panneau
            pane.getChildren().addAll(curve);
          }
        }
      });

    // Mise en place de la scène
    Scene scene = new Scene(root, 500, 400);
    // Chargement du fichier de style
    scene.getStylesheets().add("style.css");
    stage.setScene(scene);
    stage.setTitle("Bézier");
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
