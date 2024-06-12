package fr.uparis.exemples;

public class Personne { // attributs
	public static int derNumINSEE = 0;

	// fabrique statique générée
	public static Personne createPersonne(String titre, String nom, String prenom) {
		return new Personne(titre, nom, prenom);
	}

	// une autre
	public static Personne createFemme(String nom, String prenom) {
		return new Personne("Mme", nom, prenom);
	}

	// encore une avec la même signature
	public static Personne createHomme(String nom, String prenom) {
		return new Personne("M", nom, prenom);
	}

	public final String titre;
	public final NomComplet nom;
	public final int numInsee;

	// constructeur privé
	private Personne(String titre, String nom, String prenom) {
		this.titre = titre;
		this.nom = new NomComplet(nom, prenom);
		this.numInsee = ++derNumINSEE;
	}

	// et même... classe imbriquée !
	public static final class NomComplet {
		public final String nom;
		public final String prenom;

		private NomComplet(String nom, String prenom) {
			this.nom = nom;
			this.prenom = prenom;
		}
	}

	// méthode
	public String toString() {
		return String.format("%s %s %s (%d)", titre, nom.nom, nom.prenom, numInsee);
	}
}

// exemples d'utilisation
// 
// Personne Pierre=createPersonne("M","Dupont", "Pierre");
// Personne Mimi=createFemme("Dubois", "Marie");
// Personne Nico=createHomme("Delarochefoucaud","Nicolas");
//
//
