package concur;
//basé sur le tutoriel d'Oracle
//boite aux lettres fait à la main 

public class Box {
	private String message;
	private boolean empty = true;

	public synchronized String take() {
		// Wait until message is available.
		while (empty) {
			try {
				wait(); // ne pas faire boucle vide, utiliser wait
			} catch (InterruptedException e) {}
		}
		empty = true;
		notifyAll(); // Notify producer that status has changed.
		return message;
	}

	public synchronized void put(String message) {
		// Wait until message has been retrieved.
		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		empty = false;
		this.message = message;
		notifyAll(); // Notify consumer that status has changed.
	}
}
