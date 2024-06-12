package concur;
import java.util.concurrent.RecursiveAction;

//cette RecursiveAcction teste  la primalité des entiers 
// dans [from,to)  et met les résultats dans la table results
// le calcul organisé en diviser-pour-régner, récursivement
//
//On utilisewra cette classe avec ForkJoinPool

public class PrimeTester2 extends RecursiveAction {
	int from, to;
	boolean results[];

	public static boolean isPrime(int x) {
		if (x < 2)
			return false;
		for (int div = 2; div < Math.sqrt(x) + 0.1; div++)
			if (x % div == 0)
				return false;
		return true;
	}

	public PrimeTester2(int from, int to, boolean[] results) {
		this.from = from;
		this.to = to;
		this.results = results;
	}

	@Override
	protected void compute() {
		if (to - from < 100) {//petite plage explorée directement
			computeDirectly();
			return;
		}
		// grande plage coupée en deux,
		int middle = (from + to) / 2;
		invokeAll(new PrimeTester2(from, middle, results), 
				new PrimeTester2(middle, to, results));
	}

	private void computeDirectly() {
		for (int x = from; x < to; x++)
			results[x] = isPrime(x);
	}
}
