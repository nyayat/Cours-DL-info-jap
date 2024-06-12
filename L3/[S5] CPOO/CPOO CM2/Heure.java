package fr.uparis.dangers;
// utilisé dans l'Intervalle

public class Heure {
	int hh;
	
	public Heure (int hh) {
		if (hh<0|| hh>24)
			throw new IllegalArgumentException();
		this.hh=hh;
	}
	
	public static boolean avant(Heure a, Heure b) {
		return a.hh<b.hh; 
	}

}
