// Time-stamp: <FileTree.java   6 Apr 2005 19:32:04>

// IO
import java.io.InputStream;
import java.io.File;
// AWT
import java.awt.Container;
import java.awt.BorderLayout;
// SWING
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
// SWING Trees
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Arbre de fichiers
 * @author O. Carton
 * @version 1.0
 */
class FileTree extends JFrame {
    public FileTree(String fileName) {
	// Constructeur avec titre
	super("Arbre des fichiers");
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Conteneur principal de la fenêtre
	Container contentPane = getContentPane();
	
	// Construction de l'arbre
	JTree tree = new JTree(subTree(new File(fileName)));
	// Ajout dans le panneau de l'arbre avec des ascenseurs
	contentPane.add(new JScrollPane(tree), BorderLayout.CENTER);
    }
    // Construction récursive d'un sous-arbre
    // Cette méthode retourne la racine du sous-arbre construit.
    static DefaultMutableTreeNode subTree(File file) {
	DefaultMutableTreeNode node = 
	    new DefaultMutableTreeNode(shortName(file.getName()));
	if (file.isDirectory()) {
	    // Liste des fichier contenus dans le répertoire
	    String[] list = file.list();
	    for(int i = 0; i < list.length; i++) {
		node.add(subTree(new File(file, list[i])));
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
    public static void main(String [] args)
    {
	// Répertoire courant par défaut
	String fileName = args.length > 0 ? args[0] : ".";
	FileTree view = new FileTree(fileName);
	// Mise en place 
	view.pack();
	// Affichage de la vue
	view.setVisible(true);
    }

}
