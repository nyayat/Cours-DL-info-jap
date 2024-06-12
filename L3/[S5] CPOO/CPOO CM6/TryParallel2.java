package concur;

//on remplit une table de booleéns
//results[i] est true si i est premier

//technique 1: on utilise un ForkJoinPool pour gérer le paralelisme, 
//et on exécute le test sur une plage de i 
//PrimeTester2 qui est une Recursive Action - test "diviser-pour-régner"


import java.util.concurrent.ForkJoinPool;

public class TryParallel2 {

	public static void main(String[] args) throws InterruptedException {
		var results = new boolean[1000000];
//		long startTime = System.nanoTime();
		PrimeTester2 work = new PrimeTester2(0, 1000000, results);
		ForkJoinPool pool = new ForkJoinPool();
		// on peut mettre le degré de parallelisme , p.ex. ForkJoinPool(16);
		pool.invoke(work);
//		long endTime = System.nanoTime();
//		System.out.println((endTime - startTime) / 1000000);

		System.out.println(results[4643]);
		System.out.println(results[4645]);
	}

}
