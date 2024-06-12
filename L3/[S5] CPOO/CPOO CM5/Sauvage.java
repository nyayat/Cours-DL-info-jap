package concur;

public class Sauvage {
	// concurrence sauvage donne des résultats imprévisibles
	int val = 0;

/// cette version n'est pas thread-safe
	void augment(int num) {
		for (var i = 0; i < num; i++) {
			val++;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Sauvage obj = new Sauvage();

//sans synchronization le résultat est imprévisible et souvent faux.
		var t1 = new Thread(() -> obj.augment(1000000));
		var t2 = new Thread(() -> obj.augment(1000000));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(obj.val);
	}
}