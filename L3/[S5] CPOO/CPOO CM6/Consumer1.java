package concur;
//Oracle
//ce consomatteur récupère les messages de la boite aux lettres Box

import java.util.Random;

public class Consumer1 implements Runnable {
	private Box boite;

	public Consumer1(Box boite) {
		this.boite = boite;
	}

	public void run() {
		Random random = new Random();
		// vous comprenez ce for?
		for (String message = boite.take(); 
				!message.equals("DONE"); 
				message = boite.take()) {
			System.out.format("MESSAGE RECEIVED: %s%n", message);
			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {}
		}
	}
}
