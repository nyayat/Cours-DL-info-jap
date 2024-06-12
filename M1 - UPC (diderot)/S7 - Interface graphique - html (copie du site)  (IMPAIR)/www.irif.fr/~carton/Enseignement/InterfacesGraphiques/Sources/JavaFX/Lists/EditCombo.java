// -*- Coding: utf-8 -*-
// Time-stamp: <EditCombo.java   8 oct 2019 17:40:40>

/**
 * ComboBox éditable
 * @see https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/combo-box.htm
 **/
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 
public class EditCombo extends Application {
    // Ingrédients principaux de l'interface
    final Button button = new Button ("Send");
    final Label notification = new Label();
    final TextField subject = new TextField("");
    final TextArea text = new TextArea("");
    String address = "";
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        final ComboBox<String> emailComboBox = new ComboBox<String>();
        emailComboBox.getItems().addAll(
            "jacob.smith@example.com",
            "isabella.johnson@example.com",
            "ethan.williams@example.com",
            "emma.jones@example.com",
            "michael.brown@example.com"  
        );
        emailComboBox.setPromptText("Email address");
        emailComboBox.setEditable(true);        
        emailComboBox.setOnAction(e -> {
	    // Adresse du destinataire
            address = emailComboBox.getSelectionModel().getSelectedItem();
        });
        // Combox pour la priorité
        final ComboBox<String> priorityComboBox = new ComboBox<String>();
        priorityComboBox.getItems().addAll(
            "Highest",
            "High",
            "Normal",
            "Low",
            "Lowest" 
        );   
        priorityComboBox.setValue("Normal");
        // Action sur le bouton send
        button.setOnAction(e -> {
            if (emailComboBox.getValue() != null && 
                !emailComboBox.getValue().isEmpty()) {
                notification.setText("Your message was successfully sent"
                                     + " to " + address);
                emailComboBox.setValue(null);
                if (priorityComboBox.getValue() != null &&
                    !priorityComboBox.getValue().toString().isEmpty()) {
                    priorityComboBox.setValue(null);
                }
                subject.clear();
                text.clear();
            } else {
                notification.setText("You have not selected a recipient!");
            }
        });
        // Mise en place des éléments de l'interface dans une grille 4x4
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("To: "), 0, 0);
        grid.add(emailComboBox, 1, 0);
        grid.add(new Label("Priority: "), 2, 0);
        grid.add(priorityComboBox, 3, 0);
        grid.add(new Label("Subject: "), 0, 1);
        grid.add(subject, 1, 1, 3, 1);            
        grid.add(text, 0, 2, 4, 1);
        grid.add(button, 0, 3);
        grid.add(notification, 1, 3, 3, 1);
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(grid, 470, 270));
        // Titre de la fenêtre de l'application
        stage.setTitle("Sending mail");
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
