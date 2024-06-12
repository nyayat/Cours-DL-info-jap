// -*- Coding: utf-8 -*-
// Time-stamp: <TwoLists.java   9 oct 2019 18:22:54>

/**
 * Deux listes de noms de couleurs
 * @author O. Carton
 * @version 1.0
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Region;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TwoLists extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        BorderPane root = new BorderPane();
        // Liste de gauche
        ObservableList<String> llist =
            FXCollections.observableArrayList
            ("Noir", "Blanc", "Rouge", "Vert", "Bleu");
        // Widget pour visualiser la liste
        ListView<String> lview = new ListView<String>(llist);
        // Mode de sélection
        lview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Ajout du widget dans le panneau principal
        root.setLeft(lview);

        // Liste de droite
        ObservableList<String> rlist =
            FXCollections.observableArrayList
            ("Cyan", "Yellow", "Magenta");
        // Widget pour visualiser la liste
        ListView<String> rview = new ListView<String>(rlist);
        // Mode de sélection
        rview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        // Ajout du widget dans le panneau principal
        root.setRight(rview);

        // Panneau de contrôle
        VBox control = new VBox();
        root.setCenter(control);
        // Boutons ->
        Image rimage = new Image("rightarrow.png");
        ImageView ricon = new ImageView(rimage);
        Button rbutton = new Button("", ricon);
        // Boutons <-
        Image limage = new Image("leftarrow.png");
        ImageView licon = new ImageView(limage);
        Button lbutton = new Button("", licon);
        // Mise en place des boutons
        Region tspacer = new Region(); // Remplissage au dessus des boutons
        VBox.setVgrow(tspacer, Priority.ALWAYS);
        Region bspacer = new Region(); // Remplissage au dessous des boutons
        VBox.setVgrow(bspacer, Priority.ALWAYS);
        // Mise en place des éléments dans le panneau
        control.getChildren().addAll(tspacer, rbutton, lbutton, bspacer);

        // Action du bouton ->
        rbutton.setOnAction(e -> {
                // Éléments sélectionnés dans la liste de gauche
                ObservableList<String> selected =
                    lview.getSelectionModel().getSelectedItems();
                // Ajout à la liste de droite
                rview.getItems().addAll(selected);
                // Suppression de la liste de gauche
                lview.getItems().removeAll(selected);
            });
        // Action du bouton <-
        lbutton.setOnAction(e -> {
                // Éléments sélectionnés dans la liste de droite
                ObservableList<String> selected =
                    rview.getSelectionModel().getSelectedItems();
                // Ajout à la liste de gauche
                llist.addAll(selected);
                // Suppression de la liste de droite
                rlist.removeAll(selected);
            });

        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 450, 250));
        // Titre de la fenêtre de l'application
        stage.setTitle("ListView en action");
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
