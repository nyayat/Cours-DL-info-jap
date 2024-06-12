// -*- coding: utf-8 -*-
// Time-stamp: <Simple.java  18 sep 2019 17:24:43>

/**
 * Exemple PlusOuMoins d'après J. Bertel
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Simple extends Application {
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage primaryStage) {
        // Titre de la fenêtre de l'application
        primaryStage.setTitle("Plus ou moins");
        // Fermeture propre de l'application
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        // Paneau principal contenant les boutons
        BorderPane root = new BorderPane();
        // Scène 
        Scene scene = new Scene(root, 25, 80);
        // Création du label
        HandlerLabel label = new HandlerLabel();
        // Création des deux Boutons écoutés par label
        Button plusButton = new IncrButton("+", +1);
        plusButton.setOnAction(label);
        Button moinsButton = new IncrButton("-", -1);
        moinsButton.setOnAction(label);
        // Mise en place des éléments dans un panneau
        root.setTop(plusButton);
        root.setCenter(label);
        root.setBottom(moinsButton);
        // Titre
        primaryStage.setTitle("Boutons");
        // Mise en place de la scène
        primaryStage.setScene(scene);
        // Affichage de la fenêtre
        primaryStage.show();
    }
}
    
/**
 * Étiquette écoutant les boutons 
 */
class HandlerLabel extends Label implements EventHandler<ActionEvent> {
    int value = 0;
    HandlerLabel () {
        // Constructeur avec titre
        // Il serait préférable d'écrire :
        // super(Integer.toString(value), JLabel.CENTER);
        // mais ce n'est pas possible car le champ value
        // ne peut pas être utilisé dans l'appel au 
        // constructeur de la classe mère.
        super("0");
    }
    public void handle(ActionEvent event) {
        IncrButton button = (IncrButton) event.getSource();
        value += button.getIncr();
        setText(Integer.toString(value));
    }
}
