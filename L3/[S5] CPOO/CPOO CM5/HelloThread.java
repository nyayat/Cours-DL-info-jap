package concur;
//Oracle
// une autre technique pour lancer un thread: hériter de la classe Thread,
// programmer le travail à faire dans run()
// et lancer un objet avec start

public class HelloThread extends Thread {
	public void run() {
		System.out.println("Hello from a thread!");
	}

	public static void main(String args[]) {
		(new HelloThread()).start();
	}
}
