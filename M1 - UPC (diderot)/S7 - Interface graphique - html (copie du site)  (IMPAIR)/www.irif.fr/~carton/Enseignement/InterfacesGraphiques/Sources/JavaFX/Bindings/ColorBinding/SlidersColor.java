// -*- Coding: utf-8 -*-
// Time-stamp: <SlidersColor.java  11 nov 2019 15:44:33>

/**
 * Binding unidirectionnel entre trois Sliders et une couleur
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.beans.binding.Bindings;

public class SlidersColor extends Application {
    // Circle dont la couleur change
    Circle circle = new Circle(50.0, Color.RED);
    // Sliders
    Slider[] sliders = new Slider[3];
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        BorderPane root = new BorderPane();
        // Panneau pour les curseurs
        VBox controlPane = new VBox();
        root.setAlignment(controlPane, Pos.CENTER);
        root.setTop(controlPane);
        // Mise en place des trois curseurs
        for (int i = 0; i < 3; i++) {
            // Curseur de 0 à 1 avec 0.5 comme valeur initiale
            sliders[i] = new Slider(0, 1, 0.5);
            // Affichage des graduations
            sliders[i].setMajorTickUnit(0.25);
            sliders[i].setShowTickMarks(true);
            sliders[i].setShowTickLabels(true);
            // Ajout du curseur au panneau de contrôle
            controlPane.getChildren().add(sliders[i]);
        }
        root.setAlignment(circle, Pos.CENTER);
        root.setCenter(circle);
        // Lien entre la couleur et les valeurs des sliders
        // Il faut absolument que ColorBinding soit de type
        // ObjectBinding<Paint> car sinon la conversion entre
        // ObjectBinding<Color> et ObjectBinding<Paint> échoue
        // à l'exécution;
        circle.fillProperty().bind
            (new ColorBinding(sliders[0].valueProperty(),
                              sliders[1].valueProperty(),
                              sliders[2].valueProperty()));
        // Solution alternative avec Bindings.createObjectBinding
        // circle.fillProperty().bind(Bindings.createObjectBinding
        //    (() -> {
        //        return Color.color(sliders[0].getValue(),
        //                        sliders[1].getValue(),
        //                        sliders[2].getValue());
        //    }, sliders[0].valueProperty(),
        //        sliders[0].valueProperty(),
        //        sliders[2].valueProperty()));

        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 210, 250));
        // Titre de la fenêtre de l'application
        stage.setTitle("Binding Sliders-Color");
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
