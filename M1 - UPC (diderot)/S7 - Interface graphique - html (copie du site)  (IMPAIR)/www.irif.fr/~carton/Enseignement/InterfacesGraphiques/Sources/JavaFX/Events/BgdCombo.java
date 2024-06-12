// -*- coding: utf-8 -*-
// Time-stamp: <BgdCombo.java   8 oct 2019 09:25:53>

/**
 * Changement de la couleur de fond avec ComboBox
 * Les contrôleurs somt implémentés par des classes anonymes
 * @author O. Carton
 * @version 1.0
 */
import java.util.List;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class BgdCombo extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Combo box
        ComboBox<String> combo = new ComboBox<String>();
        // Ajout des noms des couleurs dans la combo box
        combo.getItems().addAll("Rouge", "Bleu", "Vert");
        // Tableau des couleurs dans le même ordre
        Color[] colors = new Color[] {Color.RED, Color.BLUE, Color.GREEN};
        // Panneau principal 
        HBox root = new HBox(combo);
        // Alignement en haut au centre des boutons
        root.setAlignment(Pos.TOP_CENTER);
        // Action sur le changement de sélection dans la combo box
        combo.valueProperty().addListener(new InvalidationListener() {
                public void invalidated(Observable observable) {
                    // Récupération de la couleur à partir du numéro
                    Color color = colors[combo.getSelectionModel().getSelectedIndex()];
                    BackgroundFill bfl = new BackgroundFill(color, null, null);
                    Background bgd = new Background(bfl);
                    // Mise en place de la couleur
                    root.setBackground(bgd); 
                }
            }); 
        // Scène 
        Scene scene = new Scene(root, 300, 200);
        // Titre
        stage.setTitle("Combo box");
        // Fermeture propre de l'application
        stage.setOnCloseRequest(event -> Platform.exit());
        // Mise en place de la scène
        stage.setScene(scene);
        // Affichage de la fenêtre
        stage.show();
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
}
