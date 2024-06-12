import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Supervisor s = new Supervisor(args[0]);
            s.getSolution();
            s.save(args[0]);

        } catch (FileNotFoundException e) {
            System.out.println("PAS DE FICHIER : " + args[0]);
        }
    }
}
