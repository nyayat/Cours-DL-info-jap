package concur;
//on expérimente avec 2 threads (+ le thread principal)
// et le sommeil

public class Simple3 {
	void alphabet() {// une lettre par seconde
		for (var i = 0; i < 26; i++) {
			System.out.print((char) (i + 'a') + " ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	};

	void nombres() { // un nombre toutes les 2 secondes
		for (var i = 0; i < 26; i++) {
			System.out.print(i + " ");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return;
			}
		}
	};

	public static void main(String[] args) throws InterruptedException {

		Simple3 obj = new Simple3();

//exemple: l'ordre est imprévisible
		var t1 = new Thread(obj::alphabet);
		var t2 = new Thread(obj::nombres);
// on lance les 2 threads
		t1.start();
		t2.start();

//		dans 10 secondes on arrête les lettres
		Thread.sleep(10000);
		t1.interrupt();
	}
}
