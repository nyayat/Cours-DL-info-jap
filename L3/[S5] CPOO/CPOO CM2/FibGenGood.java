package fr.uparis.dangers;

//contrat appels successif à next 
//produisent la séquence de Fibonacci

//source support de cours d'Aldric Degorre

// cette classe est beucoup plus solide

public class FibGenG {
	private int a = 1, b = 1; //yes!

	public int next() {
		int ret = a;
		a = b;
		b += ret;
		return ret;
	}
}
