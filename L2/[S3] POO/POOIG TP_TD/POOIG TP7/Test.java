import java.util.*;
import java.io.*;

class Test {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Missing <file> parameter");
            System.exit(1);
        }

        Dossier racine = new Dossier(null);
        Shell s = new Shell(racine);

        try {
            s.interagir(new FileInputStream(args[0]));
        }
        catch (Exception e) {
            System.out.println("Erreur lors d'ouverture fichier :");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
