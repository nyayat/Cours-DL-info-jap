// -*- coding: utf-8 -*-
// Time-stamp: <TwoScenes.java  25 oct 2019 19:07:35>

/**
 * Changement de scène
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;

public class TwoScenes extends Application {
    private Scene scene1;
    private Scene scene2;
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Scène 1
        VBox root1 = new VBox();
        root1.setAlignment(Pos.CENTER);
        Button button11 = new Button("Ici scène 1");
        button11.setPrefSize(120, 25);
        Button button12 = new Button("Vers scène 2");
        button12.setPrefSize(120, 25);
        button12.setOnAction(event -> {
                stage.setScene(scene2);
            });
        root1.getChildren().addAll(button11, button12);
        scene1 = new Scene(root1, 120, 50);
        
        // Scène 2
        HBox root2 = new HBox();
        root2.setAlignment(Pos.CENTER);
        Button button21 = new Button("Vers scène 1");
        button21.setPrefSize(120, 25);
        Button button22 = new Button("Ici scène 2");
        button22.setPrefSize(120, 25);
        root2.getChildren().addAll(button21, button22);
        scene2 = new Scene(root2, 240, 25);
        button21.setOnAction(event -> {
                stage.setScene(scene1);
            });
	
        // Mise en place de la scène 1 par défaut
        stage.setScene(scene1);
        // Titre de la fenêtre de l'application
        stage.setTitle("Changement de décor");
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
