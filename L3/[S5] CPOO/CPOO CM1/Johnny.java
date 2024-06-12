package fr.uparis.exemples;
// Adapté de "EffEffective  Java"
// Singleton avec une fabrique statique

public class Johnny {
	private static final Johnny INSTANCE = new Johnny();

	private Johnny() { ... }

	public static Johnny getInstance() {
		return INSTANCE;
	}

	public void chante() {... }
}



//Exemple 
// Johnny h=Johnny.getInstance();
// h.chante();