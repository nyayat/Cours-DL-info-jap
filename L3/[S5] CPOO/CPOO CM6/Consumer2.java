package concur;
//Oracle
//ce consomatteur récupère les messages de la boite aux lettres BlockingQueue

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer2 implements Runnable {
	private BlockingQueue<String> boite;

	public Consumer2(BlockingQueue<String> boite) {
		this.boite = boite;
	}

	public void run() {
		Random random = new Random();
		// vous comprenez ce for?
		try {
			for (String message = boite.take(); 
					!message.equals("DONE"); 
					message = boite.take()) {
				System.out.format("MESSAGE RECEIVED: %s%n", message);

				Thread.sleep(random.nextInt(5000));
			}
		} catch (InterruptedException e) {}
	}
}
