// -*- coding: utf-8 -*-
// Time-stamp: <View.java  18 sep 2019 17:58:07>

/**
 * Exemple PlusOuMoins en Model/View/Controler d'après J. Berstel
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

/**
 * Vue
 */
public class View extends Application {
    Model model;                // Modèle des données (un entier)
    Controler controler;        // Contrôleur
    Label label;
    // Point d'entrée de la vue
    @Override
    public void start(Stage primaryStage) {
        // Création du modèle
        model = new Model();
        // Création du contrôleur
        controler = new Controler(model, this);
        // Paneau principal contenant les boutons
        BorderPane root = new BorderPane();
        // Scène 
        Scene scene = new Scene(root, 25, 80);
        // Création du label et mise à jour de l'affichage
        label = new Label();
        update();
        // Création des deux Boutons écoutés par label
        Button plusButton = new IncrButton("+", +1);
        plusButton.setOnAction(controler);
        Button moinsButton = new IncrButton("-", -1);
        moinsButton.setOnAction(controler);
        // Mise en place des éléments dans un panneau
        root.setTop(plusButton);
        root.setCenter(label);
        root.setBottom(moinsButton);
        // Titre de la fenêtre de l'application
        primaryStage.setTitle("Plus ou moins en MVC");
        // Fermeture propre de l'application
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        // Mise en place de la scène
        primaryStage.setScene(scene);
        // Affichage de la fenêtre
        primaryStage.show();
    }
    // Mise à jour de la vue à partir des données du modèle
    void update() {
        label.setText(Integer.toString(model.getValue()));
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * Contrôleur 
 */
class Controler implements EventHandler<ActionEvent> {
    Model model;                // Modèle contenant les données 
    View view;                  // Vue des données
    Controler(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    // Action sur reception d'un événement
    public void handle(ActionEvent event) {
        // Bouton émetteur de l'événement
        IncrButton button = (IncrButton) event.getSource();
        // Mise à jour des données
        model.incrValue(button.getIncr());
        // Force la vue à être conforme aux données
        view.update();
    }
    public void actionPerformed(ActionEvent event) {
        IncrButton button = (IncrButton) event.getSource();
        // Mise à jour des données
        model.incrValue(button.getIncr());
        // Force la vue à être conforme aux données
        view.update();
    }
}

/**
 * Modèle contenant les données
 * Les données sont constituées d'un seul entier.
 */
class Model {
    private int value;          // Données du modèle
    Model(int value) { this.value = value; }
    Model() { this(0); }
    void incrValue(int incr) { value += incr; }
    int getValue() { return value; }
}
