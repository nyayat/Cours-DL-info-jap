package concur;

public class SauvageCorrige {
	// concurrence sauvage donne des résultats imprévisibles
	//mais on peut le corriger avec les verrous intrinsèque (moniteurs)
	int val = 0;// variable partagée!



// celle-ci est thread-safe
	void  augment(int num) {
		for(var i=0;i<num; i++) 
			synchronized(this){val++;} //on protège le bloc dangereux
	}

//// on peut protéger toute la méthode, mais comme elle contient
////	une grosse boucle ça tuera la concurrence
//	synchronized void  augment(int num) {
//		for(var i=0;i<num; i++) 
//			{val++;}
//	}

	public static void main(String[] args) throws InterruptedException {
		SauvageCorrige obj = new SauvageCorrige();

//le résultat sera correct
		var t1 = new Thread(() -> obj.augment(1000000));
		var t2 = new Thread(() -> obj.augment(1000000));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(obj.val);
	}
}