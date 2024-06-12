// Time-stamp: <SortedPlanetes.java  21 avr 2003 18:21:10>

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

/**
 * Table des planètes avec tri selon la colonne sélectionnée
 * @author O. Carton d'après une idée de J. Berstel
 * @version 1.0
 */
class SortedPlanetes extends JFrame {
    public SortedPlanetes() {
	// Constructeur avec titre
	super("Table des planetes avec tri");
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Conteneur principal de la fenêtre
	Container contentPane = getContentPane();

	// Données de la table : planètes du système solaire
	Object[][] cellules = { 
	    // Nom       Rayon (en km)      Nombre de lunes  Gazeuse
	    { "Mercure", new Double(2440),  new Integer(0),  "non"},
	    { "Vénus",   new Double(6052),  new Integer(0),  "non"},
	    { "Terre",   new Double(6378),  new Integer(1),  "non"},
	    { "Mars",    new Double(3397),  new Integer(2),  "non"},
	    { "Jupiter", new Double(71492), new Integer(16), "oui"},
	    { "Saturne", new Double(60268), new Integer(18), "oui"},
	    { "Uranus",  new Double(25559), new Integer(17), "oui"},
	    { "Neptune", new Double(24766), new Integer(8),  "oui"},
	    { "Pluton",  new Double(1137),  new Integer(1),  "non"}
	};
	// Noms de colonnes
	String[] columnNames =
	    { "Planète", "Rayon", "Lunes", "Gazeuse"};

	// Création des modèles
	TableModel baseModel = new DefaultTableModel(cellules, columnNames);
	SortedTableModel sortedModel = new SortedTableModel(baseModel);
	// Création de la table
	JTable table = new JTable(sortedModel);
	// Selection uniquement des colonnes
	table.setRowSelectionAllowed(false);
	table.setColumnSelectionAllowed(true);
	// Écoute des changements de sélection
	table.getColumnModel().getSelectionModel().addListSelectionListener(sortedModel);
	// Ajout dans la vue de la table avec des ascenseurs
	contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
    }
    public static void main(String [] args)
    {
	SortedPlanetes view = new SortedPlanetes();
	// Mise en place 
	view.pack();
	// Affichage de la vue
	view.setVisible(true);
    }
    // Modèle de table avec tri
    // Ce modèle gère uniquement le tri des lignes et 
    // délègue le reste à un autre modèle
    class SortedTableModel extends AbstractTableModel 
	                   implements ListSelectionListener {
	// Colonne sur laquelle s'effectue le tri
	int keyColumn = 0;
	// Modèle qui gère vraiment les données
	private TableModel model;
	// Correspondance entre la vue et le modèle :
	// modelRows[i] donne quelle ligne du modèle doit afficher
	// la ligne n°i de la vue.	    
	private Row[] modelRows;  
	// Chaque objet Row contient un numéro 
	class Row implements Comparable {
	    private int row; // n° de la ligne dans le modèle
	    // Constructeur
	    Row(int row) { this.row = row; }
	    // Retourne n° de la ligne dans le modèle
	    int getRow() { return row; }
	    // Comparaison de deux lignes
	    // On compare les contenus des cellules de la colonne
	    // keyColumn
	    public int compareTo(Object other) {
		Row r = (Row) other;
		Object cellule1 = model.getValueAt(row, keyColumn);
		Object cellule2 = model.getValueAt(r.row, keyColumn);
		return ((Comparable) cellule1).compareTo(cellule2);
	    }
	}
	// Constructeur
	SortedTableModel(TableModel model) {
	    this.model = model;
	    modelRows = new Row[model.getRowCount()];
	    // Au départ les lignes ne sont pas triées.
	    // La ligne modelRows[i] pointe donc sur la ligne n° i.
	    for (int i = 0; i < modelRows.length; i++) 
		modelRows[i] = new Row(i);
	}
	// Accès à une cellule
	public Object getValueAt(int row, int column) {
	    return model.getValueAt(modelRows[row].getRow(), column);
	}
	// Nombre de lignes : délégation 
	public int getRowCount() { return model.getRowCount(); }
	// Nombre de colonnes : délégation 
	public int getColumnCount() { return model.getColumnCount(); }
	// Entêtes des colonnes : délégation 
	public String getColumnName(int column) { 
	    return model.getColumnName(column);
	}
	// Classe des objets
	public Class getColumnClass(int column) {
	    return model.getColumnClass(column);
	}
	// Écoute des événements de sélection
	public void valueChanged(ListSelectionEvent e) {
	    // Modèle de sélection de la table qui a émis l'événement
	    ListSelectionModel lsm = (ListSelectionModel) e.getSource();
	    // Tri selon la colonne sélectionnée
	    if (!e.getValueIsAdjusting() && !lsm.isSelectionEmpty()) {
		// Colonne sélectionnée
		keyColumn = lsm.getMinSelectionIndex();
		// Tri des lignes suivant cette colonne
		Arrays.sort(modelRows);
		// Avertissement de la vue
		fireTableDataChanged();
	    }
	}
    } 
}
