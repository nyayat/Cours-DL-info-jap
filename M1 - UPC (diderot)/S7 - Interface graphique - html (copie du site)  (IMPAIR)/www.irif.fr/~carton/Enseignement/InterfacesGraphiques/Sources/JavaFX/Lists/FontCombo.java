// -*- Coding: utf-8 -*-
// Time-stamp: <FontCombo.java  10 oct 2019 13:55:56>

/**
 * ComboBox avec des fontes
 * Idée de W. Zielonka
 */
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;

public class FontCombo extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        BorderPane root = new BorderPane();
        // Listes des noms de fontes
        List<String> families = Font.getFamilies();
	// Listes de fontes
	// Utilisation du paradigme map-reduce offert par java.util.stream
        List<Font> fonts = families.stream()
                                   .map(name -> Font.font(name, 30))
                                   .collect(Collectors.toList());
        // ComboBox en haut du panneau principal
        ComboBox<Font> combo = new ComboBox<Font>(FXCollections.observableList(fonts));
	combo.getSelectionModel().selectFirst();
	// Équivalent à
	// combo.getSelectionModel().select(0);
        root.setTop(combo);
        // Label au milieu du panneau pour afficher du texte
        Label text = new Label("Du texte pour voir la fonte");
        root.setCenter(text);
        // Listener 
        // combo.getSelectionModel().selectedItemProperty().addListener
        //  ((ov,o,n) -> {
        //      text.setFont(n);
        //  });
        // Listener
        // combo.valueProperty().addListener
        //     ((ov,o,n) -> {
        //         text.setFont(n);
        //     });
        // Le binding provoque NullPointerException si
	// aucun élément est sélectionné
        text.fontProperty().bind(combo.valueProperty());
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 450, 120));
        // Titre de la fenêtre de l'application
        stage.setTitle("ComboBox full of fonts");
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

