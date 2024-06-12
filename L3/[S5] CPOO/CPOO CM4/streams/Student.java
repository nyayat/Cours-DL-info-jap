package streamexemples;

public class Student {

	String prenom,nom;
	Genre genre;
	int carte;
	public Student(String prenom, String nom, Genre genre, int carte) {
		this.prenom = prenom;
		this.nom = nom;
		this.genre = genre;
		this.carte = carte;
	}
	@Override
	public String toString() {
		return "Student [prenom=" + prenom + ", nom=" + nom + ", genre=" + genre + ", carte=" + carte + "]"+"\n";
	}

}
