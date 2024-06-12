package lambdaexemples;
//encore des exemples des fonctions de première classe et d'ordre supérieure

import java.util.function.*;

public class SecondOrder {

//opérateur qui ajoute deux fonctions et retourne la somme (comme fonction!)
	static IntUnaryOperator add(IntUnaryOperator f, IntUnaryOperator g) {
		return x -> f.applyAsInt(x) + g.applyAsInt(x);
	}

	public static void main(String[] args) {

// trois fonctions de première classe (observez leurs types)		
		DoubleSupplier rand = Math::random; // de type standard
		Predicate<String> isLong = s -> s.length() > 100; // aussi
		TernaryRelation<Integer> increasing = (x, y, z) -> (x < y) && (y < z); // de mon type à moi

// on peut évaluer nos fonctions, bien sûr		
		System.out.println(rand.getAsDouble()); // obtenir un réel entre 0 et 1 au hazard
		System.out.println(isLong.test("bonjour")); // est-ce que "bonjour" est long?
		System.out.println(increasing.test(3, 11, 7)); // est-ce que 3<11<7?

//on essaye notre fonction add
		IntUnaryOperator h = add(Math::abs, x -> x * 8); // h(x)=|x|+8x
		var z = h.applyAsInt(-6);
		System.out.println(z);
	}
}
