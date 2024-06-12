// -*- coding: utf-8 -*-
// Time-stamp: <BgdMenu.java  20 sep 2019 21:32:25>

/**
 * Changement de la couleur de fond avec un menu
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
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class BgdMenu extends Application {
    // Point d'entrée du programme
    public static void main(String[] args) {
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage primaryStage) {
        // Menu
        Menu menu = new Menu("Couleurs");
        // Menu bar
        MenuBar menuBar = new MenuBar(menu);
        // Paneau principal contenant la barre
        VBox root = new VBox(menuBar);
        // Scène 
        Scene scene = new Scene(root, 300, 200);
        // Ajout des entrées au menu
        addMenuItem(menu, "Rouge",   root, Color.RED);
        addMenuItem(menu, "Vert",    root, Color.GREEN);
        addMenuItem(menu, "Bleu",    root, Color.BLUE);
        addMenuItem(menu, "Magenta", root, Color.MAGENTA);
        addMenuItem(menu, "Jaune",   root, Color.YELLOW);
        addMenuItem(menu, "Orange",  root, Color.ORANGE);
        addMenuItem(menu, "Rose",    root, Color.PINK);
        addMenuItem(menu, "Cyan",    root, Color.CYAN);
        // Titre
        primaryStage.setTitle("Menu");
        // Fermeture propre de l'application
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        // Mise en place de la scène
        primaryStage.setScene(scene);
        // Affichage de la fenêtre
        primaryStage.show();
    }
    // Méthode d'ajout d'une entrée au menu
    // Les paramètres pane et Color doivent être déclarés 'final'
    // car ils sont utilisés dans la classe anonyme
    private void addMenuItem(Menu menu, String name,
                             final Pane pane, final Color color) {
        // Création de l'entrée
        MenuItem item = new MenuItem(name);
        // Action associée à l'entrée du menu.
        item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    BackgroundFill bfl = new BackgroundFill(color, null, null);
                    Background bgd = new Background(bfl);
                    // Mise en place de la couleur
                    pane.setBackground(bgd); 
                }
            });
        // Ajout de l'entrée dans le menu
        menu.getItems().add(item);
    }
}
