package concur;
//Oracle
//ce producteur envoie les phrases dans  la boite aux lettre BlockingQueue

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer2 implements Runnable {
	private BlockingQueue<String> boite;
	private List<String> phrases;

	public Producer2(BlockingQueue<String> boite, List<String> phrases) {
		this.boite = boite;
		this.phrases = phrases;
	}

	public void run() {
		Random random = new Random();

		try {
			for (String s : phrases) {
				boite.put(s);
				Thread.sleep(random.nextInt(5000));
			}
			boite.put("DONE");
		} catch (InterruptedException e) {
		}

	}
}
