// Time-stamp: <ClassTree.java   6 Apr 2005 19:05:40>

// UTIL
import java.util.Enumeration;
// IO
import java.io.InputStream;
import java.io.File;
// AWT
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// SWING
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTree;
// SWING Trees
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Arbre de classes
 * @author Cay Horstmann puis modifié par J. Berstel et O. Carton
 * @version 2.0
 */
class ClassTree extends JFrame {
    private DefaultTreeModel model; // Modèle de l'arbre
    private JTree tree;		    // Arbre
    public ClassTree() {
	// Constructeur avec titre
	super("Arbre de classes");
	// Taille
	setSize(400, 200);
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Conteneur principal de la fenêtre
	Container contentPane = getContentPane();
	
	// La classe Object est la racine de l'arbre
	DefaultMutableTreeNode root = 
	    new DefaultMutableTreeNode(Object.class);	
	// Création du modèle et de l'arbre
	model = new DefaultTreeModel(root);
	tree = new JTree(model);
	// Ajout dans le panneau de l'arbre avec des ascenseurs
	contentPane.add(new JScrollPane(tree), BorderLayout.CENTER);

	// Ajout d'un champ de saisie pour entrer une classe
	final JTextField textField = new JTextField();
	contentPane.add(textField, BorderLayout.SOUTH);
	// Écoute du JTextField
	textField.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
		    try {
			// Nom de classe saisie
			String className = textField.getText();
			// Création de la classe
			addClass(Class.forName(className));
		    }
		    catch (ClassNotFoundException e) {  
			Toolkit.getDefaultToolkit().beep();
		    }
		}
	    });

	// Ajout de quelques classes
	addClass(getClass());
    }
    // Retourne le noeud contenant comme valeur object
    public DefaultMutableTreeNode findUserObject(Object object)
    {  
	// Racine de l'arbre
	DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
	// Parcours en largeur de l'arbre
	Enumeration e = root.breadthFirstEnumeration();
	while (e.hasMoreElements()) {  
	    DefaultMutableTreeNode node 
		= (DefaultMutableTreeNode)e.nextElement();
	    if (node.getUserObject().equals(object))
		return node;
	}
	return null;
    }

    // Ajout d'une classe et de ses super-classes à l'arbre
    // Cette méthode retourne le noeud de la classe
    public DefaultMutableTreeNode addClass(Class c) {

	// Pas d'interface et de classe primitive
	// Ce test garantit que la récurisvité s'arête toujours
	// puisque la classe Object est primitive.
	if (c.isInterface() || c.isPrimitive()) 
	    return null;

	// Si la classe est déjà dans l'arbre, on retourne son noeud
	DefaultMutableTreeNode node = findUserObject(c);
	if (node != null) 
	    // La classe est déjà dans l'arbre
	    return node;

	// Avant d'ajouter la classe proprement dite, il faut d'abord
	// ajouter sa super-classe.
	DefaultMutableTreeNode parent = addClass(c.getSuperclass());

	// Création du noeud pour la classe
	DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
	model.insertNodeInto(newNode, parent, parent.getChildCount());

	// On rend visible le chemin jusqu'à la classe
	TreePath path = new TreePath(model.getPathToRoot(newNode));
	tree.makeVisible(path);

	return newNode;
    }
    public static void main(String [] args)
    {
	// Vue
	ClassTree view = new ClassTree();
	// Mise en place 
	view.pack();
	// Affichage de la vue
	view.setVisible(true);
    }

}
