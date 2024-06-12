package concur;
//Oracle
//ce producteur envoie les phrases dans  la boite aux lettre Box

import java.util.List;
import java.util.Random;

public class Producer1 implements Runnable {
	private Box boite;
	private List<String> phrases;

	public Producer1(Box boite, List<String> phrases) {
		this.boite = boite;
		this.phrases = phrases;
	}

	public void run() {
		Random random = new Random();

		for (String s : phrases) {
			boite.put(s);
			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
			}
		}
		boite.put("DONE");
	}
}
