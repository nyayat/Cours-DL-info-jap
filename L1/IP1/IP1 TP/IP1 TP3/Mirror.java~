import java.io.*;
import java.util.Random;

class Mirror {

/**** Les fonctions reverse et myReverse à tester dans cet exercice ****/

public static String reverse (String s) {
    String r = "";
    for(int i = 0; i < s.length (); i++) {
        r = r + characterAtPos(s, s.length () - (i+1));
    }
    return r;
}

public static String myReverse (String s) {
    if (s.equals("Miroir") || s.equals("avec des espaces")) {
        return reverse(s);
    } else {
        return s;
    }
}

/**** La fonction main où mettre vos tests ****/

public static void main(String[] args) {
    System.out.println("Pas encore de tests ici...");
}

/**** Les fonctions auxiliaires ****/

/* Fonction fournie en IP1 pour l'accès à un charactère d'une chaîne */

public static String characterAtPos(String s, int i) {
    if (i >= 0 && i < s.length ()) {
        return (String.valueOf(s.charAt(i)));
    } else {
        return "";
    }
}

/* Fonction fournie en IP1 pour comparer deux chaines de caractères */
public static boolean stringEquals(String s, String t){
    return s.equals(t);
}



/* Fonctions fournies pour cet exercice afin de charger le dictionnaire
   "dico_fr.txt" puis de renvoyer le mot numéro i dans ce dictionnaire. */

static String dictName = "dico_fr.txt";
static int dictSize = 336531;
static String[] dict = new String[dictSize];
static boolean dictLoaded = false;

public static void loadError () {
    System.out.println("Erreur de chargement de dico_fr.txt!!");
    System.exit(1);
}

public static void loadDict () {
    try {
        BufferedReader br = new BufferedReader(new FileReader(dictName));
        for (int i=0; i < dictSize; i++) {
            dict[i] = br.readLine();
            if (dict[i] == null) { loadError (); }
        }
        dictLoaded = true;
    }
    catch (Exception e) { loadError (); }
}

public static String wordFromDict (int i) {
    if (!dictLoaded) { loadDict (); }
    return dict[i];
}

/* Fonction fournie pour cet exercice qui renvoie au hasard un entier
   entre a (inclus) et b (exclus) */

static Random rand = new Random();

public static int randRange (int a, int b) {
    return (rand.nextInt(b-a) + a);
}

}
