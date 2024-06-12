package concur;

// une classe pour tester la primalité de i et 
// mettre le résultat dans result[i]
//
// cette classe devrait être utilisé dans un Thread ou un Executor

public class PrimeTester implements Runnable{
	int i;
	boolean results[];
	
	private static boolean isPrime(int x) {
		if(x<2)return false;
		for (int div=2;div<Math.sqrt(x)+0.1; div++)
			if (x%div==0)return false;
		return true;
	}

	public PrimeTester(int i, boolean[] results) {
		this.i = i;
		this.results = results;
	}

	@Override
	public void run() {
		results[i]=isPrime(i);		
	}
}
