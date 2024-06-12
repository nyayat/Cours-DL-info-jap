package fr.uparis.dangers;
//Exemple d'Aldric Degorre
//Danger d'héritage, voir Personne2

class Personne {
	private String prenom, nom;

	public Personne(String prenom, String nom) {
		this.prenom = prenom;
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	} // il faudrait final

	public String getNom() {
		return nom;
	} // là aussi

	public String getNomComplet() { // un accesseur alternatif - c'est sympa
		return getPrenom() + " " + getNom(); // appel à méthodes redéfinissables −> danger !!! }
	}
}