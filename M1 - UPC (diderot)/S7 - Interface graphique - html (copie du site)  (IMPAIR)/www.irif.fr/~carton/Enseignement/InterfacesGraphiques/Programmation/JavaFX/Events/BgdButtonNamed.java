// -*- coding: utf-8 -*-
// Time-stamp: <BgdButtonNamed.java  20 sep 2019 21:30:56>

/**
 * Changement de la couleur de fond avec des boutons
 * Les contrôleurs somt implémentés par une classe 
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

// Classe pour gérer un des trois boutons
class ButtonHandler implements EventHandler<ActionEvent> {
    // Constructeur
    public ButtonHandler(Pane pane, Color color) {
        this.pane = pane;                       
        this.color = color;
    }
    // Callback
    public void handle(ActionEvent event) {
        BackgroundFill bfl = new BackgroundFill(color, null, null);
        Background bgd = new Background(bfl);
        pane.setBackground(bgd); // Mise en place de la couleur
    }
    private Pane pane;          // Panneau à changer de couleur
    private Color color;        // Couleur à utiliser
}

public class BgdButtonNamed extends Application {
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage primaryStage) {
        // Buttons pour l'affichage
        Button redButton   = new Button("Rouge");
        Button blueButton  = new Button("Bleu");
        Button greenButton = new Button("Vert");
        // Paneau principal contenant les boutons
        HBox root = new HBox(redButton, blueButton, greenButton);
        // Alignement en haut au centre des boutons
        root.setAlignment(Pos.TOP_CENTER);
        // Scène 
        Scene scene = new Scene(root, 300, 200);
        // Actions pour chacun des boutons
        redButton.setOnAction(new ButtonHandler(root, Color.RED));
        blueButton.setOnAction(new ButtonHandler(root, Color.BLUE));
        greenButton.setOnAction(new ButtonHandler(root, Color.GREEN));
        // Titre
        primaryStage.setTitle("Boutons");
        // Fermeture propre de l'application
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        // Mise en place de la scène
        primaryStage.setScene(scene);
        // Affichage de la fenêtre
        primaryStage.show();
    }
}
