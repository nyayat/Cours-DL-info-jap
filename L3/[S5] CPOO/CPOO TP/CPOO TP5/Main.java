import java.util.List;

public class Main{
    //1.
    /*
        1. Set<Footballeur>
        2. Map<Footballeur, Role>
        3. List<Footballeur>
        4. Map<Etudiant, TD>
        5. Map<TD, Set<Enseignant>>
        6. Map<Seance, Set<Etudiant>>
    */
    
    public static void main(String[] args){
        //3.
        //creation de la base de donnees
        BaseDeDonnees db=new BaseDeDonnees("UFR Informatique");

        //definition de tableau etudiants
        Tableau etudiants=db.ajouterTableau("etudiants");
        etudiants.ajouterColonne("nom", TypeDonnee.STRING);
        etudiants.ajouterColonne("prenom", TypeDonnee.STRING);
        etudiants.ajouterColonne("groupe", TypeDonnee.INT);

        //insertion de donnees
        db.inserer("etudiants", List.of("nom", "prenom", 1));
        db.inserer("etudiants", List.of("Martin", "Marie", 5));
        db.inserer("etudiants", List.of("Laurent", "Jean", 1));
        db.inserer("etudiants", List.of("Simon", "Pierre", 5));

        //recherche de donnees
        List<Ligne> resultats=db.chercher("etudiants", "groupe", 5);
        for(Ligne ligne : resultats)
            System.out.println(ligne.get("nom")+" "+ ligne.get("prenom"));
    }
}