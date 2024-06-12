package streamexemples;

import static java.util.Comparator.comparing; //import statique pour simplifier les appels
import static java.util.function.Function.identity;// pareil

import java.util.ArrayList;
import java.util.List;

public class Tris {
	public static void main(String[] args) {
		// en 2 coups on fabrique et remplit une liste mutable de chaines
		List<String> semaineImmuable = List.of("lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche");
		List<String> semaine = new ArrayList<String>(semaineImmuable);

		// on trie selon divers critères, attention aux lambdas

		// d'abord on instancie un Comparator en implémentant sa méthode

		// tri selon la longueur
//		semaine.sort((s1,s2) -> s1.length()-s2.length()); 
		// il vaut mieux utiliser Integer.compare et non -		
		semaine.sort((String s1, String s2) -> Integer.compare(s1.length(), s2.length()));
		System.out.println(semaine);

// comparing est plus lisible
// on passe en argument une lambda qui extrait la clé de comparaison
		
		// tri par longueur
		semaine.sort(comparing(String::length));
		System.out.println(semaine);

		// tri par longueur d'abord, puis par la chaine elle-même (alphabétique)
		semaine.sort(comparing(String::length).thenComparing(s -> s));
		System.out.println(semaine);

		// s->s s'appelle aussi identity(), c'est donc la même chose que le tri
		// précédent
		semaine.sort(comparing(String::length).thenComparing(identity()));
		System.out.println(semaine);

		// par longueur décroissante d'abord, alphabétique après
		semaine.sort(comparing(String::length).reversed().thenComparing(identity()));
		System.out.println(semaine);

	}
}
