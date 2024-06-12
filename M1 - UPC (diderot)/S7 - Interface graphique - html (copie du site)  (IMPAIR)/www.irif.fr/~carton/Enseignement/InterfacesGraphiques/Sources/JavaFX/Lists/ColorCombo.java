// -*- Coding: utf-8 -*-
// Time-stamp: <ColorCombo.java   8 oct 2019 16:28:23>

/**
 * ComboBox avec des rectangles de couleur
 * @see http://www.java2s.com/Tutorials/Java/JavaFX/0590__JavaFX_ComboBox.htm
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ColorCombo extends Application {
  // Point d'entrée de l'application JavaFX
  @Override
  public void start(final Stage stage) {
    // Panneau principal
    BorderPane root = new BorderPane();
    // ComboBox au milieu du panneau principal
    ComboBox<Color> combo = new ComboBox<Color>();
    root.setCenter(combo);
    // Ajout de couleurs dans le combo
    combo.getItems().addAll(Color.RED, Color.GREEN, Color.BLUE);
    // Mise en place d'une factory pour les cellules du combo
    combo.setCellFactory(new Callback<ListView<Color>, ListCell<Color>>() {
        @Override
        public ListCell<Color> call(ListView<Color> p) {
          return new ListCell<Color>() {
            private final Rectangle rectangle;
            // Instance initializer in the anonymous class
            {
              setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
              rectangle = new Rectangle(30, 15);
            }
            @Override
            protected void updateItem(Color item, boolean empty) {
              // Indispensable
              super.updateItem(item, empty);
                            
              if (item == null || empty) {
                setGraphic(null);
              } else {
                rectangle.setFill(item);
                setGraphic(rectangle);
              }
            }
          };
        }
      });
    // Création de la scène avec des dimensions
    // Problème avec la couleur de fond ?
    stage.setScene(new Scene(root, 120, 60, Color.WHITE));
    // Titre de la fenêtre de l'application
    stage.setTitle("ComboBox full of colors");
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

