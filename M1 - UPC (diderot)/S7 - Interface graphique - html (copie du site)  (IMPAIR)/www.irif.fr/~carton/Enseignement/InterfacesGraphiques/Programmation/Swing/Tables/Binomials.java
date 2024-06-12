// Time-stamp: <Binomials.java  21 avr 2003 16:46:46>

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Table des coefficients binômiaux
 * @author O. Carton 
 * @version 1.0
 */
class Binomials extends JFrame {
    public Binomials() {
	// Constructeur avec titre
	super("Table des coefficients binômiaux");
	// Action à faire lorsque la fenêtre est fermée.
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Conteneur principal de la fenêtre
	Container contentPane = getContentPane();

	// Modèle de la table
	class BinomialsModel extends AbstractTableModel {
	    int size;
	    // Constructeurs
	    BinomialsModel(int size) { this.size = size; }
	    BinomialsModel() { this(10); }
	    // Méthode statique du calcul d'un coefficient binomial
	    private int binomial(int n, int p) {

		int binom;	// Résultat
		int k;		// Variable d'itération

		// On se ramène au cas où p <= n
		if (p > n) 
		    return 0;
		// On se ramène au cas où p <= n-p
		if (p > n-p) 
		    p = n-p;
		// k     prend les valeurs     1 ... p
		// n-p+k prend les valeurs n-p+1 ... n
		for (binom = 1, k = 1; k <= p; k++)
		    // Il est important de faire la mutiplication avant la
		    // division.  Le résultat intermédiaire binom/k n'est
		    // pas nécessairement un entier.
		    binom = (binom * (n-p+k)) / k;
		return binom;
	    }
	    // Méthodes à redéfinir dans AbstractTableModel
	    // Nombre de lignes
	    public int getRowCount() { return size; }
	    // Nombre de colonnes
	    public int getColumnCount() { return size; }
	    // Entêtes des colonnes
	    public String getColumnName(int column) { 
		return Integer.toString(column);
	    }
	    // Classe des objets : ceci force une justification à droite
	    public Class getColumnClass(int column) {
		return Number.class;
	    }
	    // Valeurs des cellules
	    public Object getValueAt(int row, int column) {
		return new Integer(binomial(row, column));
	    }
	}
	// Création de la table
	JTable table = new JTable(new BinomialsModel(20));
	// Ajout dans la vue de la table avec des ascenseurs
	contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
	// Ajout dans la vue de la table sans les ascenseurs
	// Dans ce cas, il faut ajouter les entêtes séparément.
	// contentPane.add(table.getTableHeader(), BorderLayout.NORTH);
	// contentPane.add(table, BorderLayout.CENTER);
    }
    public static void main(String [] args)
    {
	Binomials view = new Binomials();
	// Mise en place 
	view.pack();
	// Affichage de la vue
	view.setVisible(true);
    }
}

