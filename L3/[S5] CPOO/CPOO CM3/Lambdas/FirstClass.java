package lambdaexemples;
//série d'exemples de fonctions de première class et de second ordre

import java.util.*;
import java.util.function.*;

public class FirstClass {

//FONCTIONS DE SECOND ORDRE

// répéter 5 fois une procédure f
	static void repeat5(Runnable f) {
		for (var i = 0; i < 5; i++)
			f.run();
	}

// équivalent de structure de contrôle if-else
//faire if(cond(x)) casPos() else casNeg(x)

	static void ifThenElse(BooleanSupplier cond, Runnable casPos, Runnable casNeg) {
		if (cond.getAsBoolean())
			casPos.run();
		else
			casNeg.run();
	}

// calculer l'image d'un ensemble m par fonction f
	static Set<Double> image(Set<Integer> m, IntToDoubleFunction f) {
		Set<Double> result = new HashSet<Double>();
		for (var x : m)
			result.add(f.applyAsDouble(x));
		return result;
	}

//trouver une racine de f(x)=0 sur [0,1]. Hypothèse f(0)<0, f(1)>0
	static double dichotomy(DoubleUnaryOperator f, double epsilon) {
		if (f.applyAsDouble(0.) >= 0. || f.applyAsDouble(1.) <= 0.)
			throw new IllegalArgumentException();
		var left = 0.;
		var right = 1.;
		while (right - left > epsilon) {
			var middle = (left + right) / 2.;
			if (f.applyAsDouble(middle) > 0.)
				right = middle;
			else
				left = middle;
		}
		return (left + right) / 2.;
	}

// EXEMPLES D'UTILISATION DES FONCTIONS PRÉCÉDENTES

	public static void main(String[] args) {

		// on les applique 
		repeat5(new Print4Stars()); // à une FPC - instance de classe

		repeat5(new Runnable() { // à une FPC - instance de classe anonyme
			static int j = 0;

			@Override
			public void run() {
				System.out.println(j++);
			}
		});

		repeat5(() -> System.out.println("cocorico")); // à une lambda-expression

		repeat5(System.out::println); // à une lambda-expression référence de méthode

		ifThenElse( // à trois lamda-expression
				() -> Math.random() > 0.7, // un BooleanSupplier
				() -> System.out.println("pile"), () -> System.out.println("face"));

		var im = image(Set.of(1, 4, 5), x -> x * 0.5); // un ensemble de 3 entiers et une FPC
		System.out.println(im); // on affiche l'image

		im = image(Set.of(1, 4, 5), Math::sin); // un ensemble de 3 entiers et une FPC
		System.out.println(im); // on affiche l'image

		double root = dichotomy(x -> 3 * x - 1, 0.00001); // on résout l'équation 3x=1
		System.out.println(root);

		root = dichotomy(Math::sin, 0.001); // on essaye de résoudre sin x=0
		// exception, parce que sin(0)=0; commenter la ligne précédente pour avancer

		root = dichotomy(x -> Math.sin(x) - 0.5, 0.001); // on résout l'équation sin x=0.5
		System.out.println(root);
	}
}
