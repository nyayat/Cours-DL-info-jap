package fr.uparis.dangers;
//contrat: appels successif à next 
//produisent la séquence de Fibonacci

//source: support de cours d'Aldric Degorre

// cette classe est très fragile à cause des attributs publiques


public class FibGenB {
	public int a = 1, b = 1; // ICI!!!

	public int next() {
		int ret = a;
		a = b;
		b += ret;
		return ret;
	}
	
}

// Exemple de mauvais usage qui casse le contrat
// FibGenB gen = new FibGenB();
// x[0]=gen.next();    //cela produit 1 - OK
// x[1]=gen.next();    // produit 1 - OK
// gen.a=66;           //attaque
// x[2]=gen.next();    // produit 66 - contrat violé
