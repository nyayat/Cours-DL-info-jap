// -*- coding: utf-8 -*-
// Time-stamp: <XMLTree.java   7 déc 2011 21:45:40>

// IO
import java.io.InputStream;
import java.io.FileInputStream;
// DOM
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
 * Arbre des éléments d'un document XML
 * @author O. Carton
 * @version 1.0
 */
class XMLTree extends JFrame {
    public XMLTree(Document doc) {
	// Constructeur avec titre
	super("Arbre d'un document XML");
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Conteneur principal de la fenêtre
	Container contentPane = getContentPane();
	
	// Construction de l'arbre
	JTree tree = new JTree(subTree(doc.getDocumentElement()));
	// Ajout dans le panneau de l'arbre avec des ascenseurs
	contentPane.add(new JScrollPane(tree), BorderLayout.CENTER);
    }
    // Construction récursive d'un sous-arbre
    // Cette méthode retourne la racine du sous-arbre construit.
    static DefaultMutableTreeNode subTree(Node xmlnode) {
	DefaultMutableTreeNode node = 
	    new DefaultMutableTreeNode(xmlnode.getNodeName());
	if (xmlnode.hasChildNodes()) {
	    // Liste des fichier contenus dans le répertoire
	    NodeList nodelist = xmlnode.getChildNodes();
	    for(int i = 0; i < nodelist.getLength(); i++) {
		node.add(subTree(nodelist.item(i)));
	    }
	}
	return node;
    }
    public static void main(String [] args)
	throws Exception
    {
	// Création de la fabrique de constructeur de documents
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	// Création du constructeur de documents
	DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
	// Flux d'entrée
	InputStream is = new FileInputStream(args[0]);
	// Construction du document
	Document doc = documentBuilder.parse(is);
	// Vue en arbre du document
	XMLTree view = new XMLTree(doc);
	// Mise en place 
	view.pack();
	// Affichage de la vue
	view.setVisible(true);
    }

}
