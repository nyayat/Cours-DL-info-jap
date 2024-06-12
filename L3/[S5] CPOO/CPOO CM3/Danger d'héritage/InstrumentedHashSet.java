package fr.uparis.dangers;
//Exemple de Effective Java
//Danger d'héritage

//On a pris HashSet et ajouté le compteur d'ajouts d'éléments
//Le résultat du petit test  à la fin devrait être 3, mais ça donne 6!!!

import java.util.*;

//Broken - Inappropriate use of inheritance!
public class InstrumentedHashSet<E> extends HashSet<E> {

	private int addCount = 0; //The number of attempted element insertions

	public InstrumentedHashSet() {
	}

	public InstrumentedHashSet(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}

// on teste ici	
	public static void main(String[] args) {
		InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
		s.addAll(List.of("Snap", "Crackle", "Pop"));
		System.out.println(s.getAddCount());
	}
}