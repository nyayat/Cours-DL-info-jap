package concur;

//on remplit une table de Boolean
//results[i] est true si i est premier
//
//Technique 3 : parallel Streams


import java.util.stream.IntStream;

public class TryParallel3 {

	public static boolean isPrime(int x) {
		if (x < 2)
			return false;
		for (int div = 2; div < Math.sqrt(x) + 0.1; div++)
			if (x % div == 0)
				return false;
		return true;
	}

	public static void main(String[] args) throws InterruptedException {

//		long startTime = System.nanoTime();
		var results = IntStream.range(0, 1000000)
				.parallel()
				.mapToObj(x -> isPrime(x))
				.toArray(Boolean[]::new);
		
//		long endTime = System.nanoTime();
//		System.out.println((endTime - startTime) / 1000000);

		System.out.println(results[4643]);
		System.out.println(results[4645]);
	}
}
