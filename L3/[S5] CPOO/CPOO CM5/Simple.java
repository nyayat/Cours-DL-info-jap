package concur;
//basé sur l'exemple d'Oracle

// 1. Comment lancer un Thread

//pour lancer un  thread qui fait un certain  travail 
//on peut implémenter Runnable, avec méthode run() 
//qui représente ce travail à faire

//et puis appeler le constructeur Thred(Runnable)

//Si le code du travail à faire est court, l'écriture avec lambda 
// est équivalente mais  beaucoup plus légère

public class Simple {

	// on implémente Runnable
	public static class HelloRunnable implements Runnable {
		public void run() {
			System.out.println("Hello from a thread class!");
		}
	}

	public static void main(String[] args) {
		// on crée un Thread et on le lance avec
		Thread t = new Thread(new HelloRunnable());
		t.start();

		// Même chose avec lambda est plus légère
		new Thread(() -> System.out.println("Ciao du thread lambda!")).start();
	}
}
