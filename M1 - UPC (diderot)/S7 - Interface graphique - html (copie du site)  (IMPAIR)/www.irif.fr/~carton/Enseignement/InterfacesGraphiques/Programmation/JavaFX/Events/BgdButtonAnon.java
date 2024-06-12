// -*- coding: utf-8 -*-
// Time-stamp: <BgdButtonAnon.java  23 sep 2019 08:27:25>

/**
 * Changement de la couleur de fond avec des boutons
 * Les contrôleurs somt implémentés par des classes anonymes et des lambdas
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class BgdButtonAnon extends Application {
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
        // Classe anonyme
        redButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    BackgroundFill bfl = new BackgroundFill(Color.RED,
                                                            null,
                                                            null);
                    Background bgd = new Background(bfl);
                    root.setBackground(bgd);
                }
            });
        // Lambda expression
	// Il est possible de remplacer un objet implémentant une
	// interface par une lambda car l'interface est fonctionnelle.
	// Elle ne définit qu'une seule methode en dehors des redéfinitions
	// de méthodes de Object et des méthodes statiques.
        blueButton.setOnAction((ActionEvent event) -> {
                    BackgroundFill bfl = new BackgroundFill(Color.BLUE,
                                                            null,
                                                            null);
                    Background bgd = new Background(bfl);
                    root.setBackground(bgd);
                });
        // Lambda expression sans le type du paramètre
        greenButton.setOnAction(event -> {
                    BackgroundFill bfl = new BackgroundFill(Color.GREEN,
                                                            null,
                                                            null);
                    Background bgd = new Background(bfl);
                    root.setBackground(bgd);
                });
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
