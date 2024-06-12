package streamexemples;
// exemples d'utilisation de streams, comme en BD
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class Simples {
// on instancie quelques étudiants pour expérimenter
	public static final Student NICO = new Student("Nicolas", "Dupont", Genre.MALE, 123456);
	public static final Student COCO = new Student("Corinne", "Dupont", Genre.FEMALE, 234567);
	public static final Student TOTO = new Student("Thomas", "Martin", Genre.MALE, 173456);
	public static final Student MIMI = new Student("Marie", "Durand", Genre.FEMALE, 214567);

// on liste toutes les filles classées selon le no de carte	d'étudiant
//d'abord avec les méthodes de Collection	
	public static List<Student> solution1(List<Student> l) {
		List<Student> result = new ArrayList<Student>();
		for (var et : l)
			if (et.genre==Genre.FEMALE)
				result.add(et);
		result.sort(comparing(s -> s.carte));
		return result;
	}

//même  chose avec les streams
	public static List<Student> solution2(List<Student> l) {
		return l.stream()
				.filter(et -> et.genre==Genre.FEMALE)
				.sorted(comparing(et -> et.carte))
				.collect(toList());
	}
		
//et si encore on veut supprimer les doublons et ne lister que les prénoms
		public static List<String> solution3(List<Student> l) {
		return l.stream()
				.distinct()
				.filter(et -> et.genre==Genre.FEMALE)
				.sorted(comparing(et -> et.carte))
				.map(et -> et.prenom)
				.collect(toList());
	}		

	public static void main(String[] args) {
		var groupe = List.of(NICO, COCO, TOTO, NICO, MIMI, MIMI, MIMI);
		//on essaye tous ces traitements et affiche les résultats
		System.out.println(solution1(groupe));
		System.out.println();
		System.out.println(solution2(groupe));
		System.out.println();
		System.out.println(solution3(groupe));
		System.out.println();

// et si on veut juste les compter
		var cpt = groupe.stream()
				.distinct()
				.filter(et -> et.genre==Genre.FEMALE)
				.count();

		System.out.println(cpt);
	}
}
