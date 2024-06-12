import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Problem {
    int nbNoteJouee; // nombre total de notes jouées depuis le début
    int sommeA;
    int tailleSequence; // taille de la séquence initiale
    ArrayList<Note> notes = new ArrayList<>(); // liste des notes

    Problem(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        int nbNotes = sc.nextInt();
        tailleSequence = sc.nextInt();

        for (int i = 0; i < nbNotes; i++) {
            int a = sc.nextInt();
            notes.add(new Note(a));
            sommeA += a;
        }
        Note.setSommeA(sommeA);
        for (int i = 0; i < tailleSequence; i++) {
            int b = sc.nextInt();
            nbNoteJouee++;
            notes.get(b - 1).play();
        }
        sc.close();
    }

    /**
     * Vérifie si toutes les notes vérifient les conditions
     *
     * @return true si c'est le cas et false sinon
     */
    boolean check() {
        for (Note n : notes) {
            double freq = n.getFrequency();
            int nb = n.getNbPlayed();
            if (!(Math.abs(nb - (nbNoteJouee * freq)) < 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Trouve une séquence maximale de notes au problème.
     * Si le nombre de notes jouées est égale à la somme des a,
     * alors on peut répéter la séquence trouvée une infinité de fois.
     *
     * @return la longueur de la séquence trouvée ou -1 si la séquence est infinie
     */
    int getSolution() {
        boolean check = check();
        // si le nombre de notes jouées est égale à la somme des a,
        // alors on peut répéter la séquence trouvée une infinité de fois
        while (check && nbNoteJouee < sommeA) {
            // on choisit une note qui peut être jouée et qui a le moins de tours pour être jouée
            Note note = notes.stream()
                    .filter(n -> n.getNbPlayed() + 1 < (nbNoteJouee + 1) * n.getFrequency() + 1)
                    .min(Comparator.comparing(n -> n.getLimit() - nbNoteJouee)).get();
            note.play();
            nbNoteJouee++;
            if (!check()) {
                check = false;
                nbNoteJouee--; // la note peut pas être jouée, donc on la retire
            }
        }
        if (nbNoteJouee >= sommeA)
            return -1;
        else
            return nbNoteJouee - tailleSequence;
    }
}
