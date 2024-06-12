package streamexemples;

import static java.util.stream.Stream.iterate;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Autres {
	//divers exemples de Streams (avec des nombres)

	public static void main(String[] args) {		
		int rrr;
		
		//on calcule la somme avec un IntStream
		rrr = IntStream.of(2, 6, 7, 3, 3)
				.sum();
		System.out.println("somme méthode 1:" + rrr);
		
		//ou bien en convertissant un Stream en IntStream
		rrr = Stream.of(2, 6, 7, 3, 3)
				.mapToInt(s->s)
				.sum();
		System.out.println("somme méthode 2:" + rrr);
			
		// ou encore avec reduce
		rrr = Stream.of(2, 6, 7, 3, 3)
				.reduce(0,(x,y)->x+y);

		System.out.println("somme méthode 3:" + rrr);
		
		rrr = Stream.of(2, 6, 7, 3, 3)
				.reduce(1, (x, y) -> x * y);
				
		System.out.println("produit:"+rrr);
		
		rrr = Stream.of(2, 6, 7, 3, 3)
				.reduce(-1000, (x, y) -> Math.max(x,y));
				
		System.out.println("maximum:"+rrr);
		System.out.println();

		//on génère un Stream infini de puissances de 2, puis on en prend et affiche 10  éléments
		System.out.println("Puissances de 2");
		iterate(1,x->2*x)
		.limit(10)
		.forEach(System.out::println);
		
		System.out.println();
		
		// même technique pour afficher 100 nombre premiers
		System.out.println("Premiers");
		iterate(BigInteger.TWO, BigInteger::nextProbablePrime)
		.limit(100)
		.forEach(x->System.out.println(x));
	}
}