// -*- Coding: utf-8 -*-
// Time-stamp: <FileTree.java   9 oct 2019 18:39:42>

/**
 * Arborescences de fichiers
 * @author O. Carton
 * @version 1.0
 */
import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;

public class FileTree extends Application {
    // Point d'entrée de l'application JavaFX
    @Override
    public void start(Stage stage) {
        // Panneau principal
        BorderPane root = new BorderPane();
	// Arbre au centre du panneau principal
	TreeView<String> tree =
	    new TreeView<String>(subTree(new File(rootname)));
	root.setCenter(tree);
        // Création de la scène avec des dimensions
        stage.setScene(new Scene(root, 450, 250));
        // Titre de la fenêtre de l'application
        stage.setTitle("TreeView en action");
        // Fermeture propre de l'application
        stage.setOnCloseRequest(e -> Platform.exit());
        // Affichage de la fenêtre
        stage.show();
    }
    // Construction récursive d'un sous-arbre
    // Cette méthode retourne la racine du sous-arbre construit.
    static TreeItem<String> subTree(File file) {
	TreeItem<String> node = new TreeItem<String>(shortName(file.getName()));
	if (file.isDirectory()) {
	    // Liste des fichier contenus dans le répertoire
	    String[] list = file.list();
	    for(int i = 0; i < list.length; i++) {
		node.getChildren().add(subTree(new File(file, list[i])));
	    }
	}
	return node;
    }
    // Retourne le nom court du fichier
    static String shortName(String fileName) {
	// Séparateur de fichiers : '/' sur Unix
	String separator = System.getProperty("file.separator");
	// Retourne la chaîne après le dernier séparateur
	return fileName.substring(1 + fileName.lastIndexOf(separator));
    }
    // Point d'entrée du programme
    public static void main(String[] args) {
	// Répertoire courant par défaut
	rootname = args.length > 0 ? args[0] : ".";
        // Appel du point d'entrée de l'application JavaFX 
        launch(args); 
    }
    // Name of the root
    private static String rootname;	
}
