package lambdaexemples;

//Fonction de première classe (FPC) en écriture traditionnelle (déconseillée)
//bien évidemment la notation lambda est beaucoup plus légère

public class Print4Stars implements Runnable {

	@Override
	public void run() {
		System.out.println("****");

	}
}
