package fr.uparis.dangers;
//exemple d'Aldric Degorre

//danger d'héritage
// l'exécution mène à StackOverflow

public class Personne2 extends Personne {

	public Personne2(String string, String string2) {
		super(string, string);
	}

	@Override
	public String getPrenom() {
		return getNomComplet().split(" ")[0];
	} // bizarre mais pourquoi pas

	@Override
	public String getNom() {
		return getNomComplet().split(" ")[1];
	}

	public static void main(String[] args) {
		var moi = new Personne2("Eugene", "Asarin");
		var s = moi.getNom(); // CATASTROPHE!!!
	}
}