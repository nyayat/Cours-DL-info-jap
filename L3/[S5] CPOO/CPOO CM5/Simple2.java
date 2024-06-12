package concur;
//on expérimente avec 2 threads (+ le thread principal)

//ici ils se font en ordre quelconque
//mais si on met le join (ligne 27) alors c'est d'abord les lettres 

public class Simple2 {
	void alphabet() {
		for (var i = 0; i < 26; i++)
			System.out.print((char) (i + 'a') + " ");
	};

	void nombres() {
		for (var i = 0; i < 26; i++)
			System.out.print(i + " ");
	};

	public static void main(String[] args) throws InterruptedException {

		Simple2 obj = new Simple2();

//exemple: l'ordre est imprévisible
		var t1 = new Thread(obj::alphabet);
//		var t1 = new Thread(()->obj.alphabet()); //équivalent à la ligne précédente
		var t2 = new Thread(obj::nombres);
		t1.start();
//		t1.join(); // si on veut terminer les lettres avant de commencer les chiffres
		t2.start();

	}

}
