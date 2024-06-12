import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args){
        try {
            Problem p=Problem.fromFile(args[0]);
            p.getSolution();
            p.save(args[0]);

        } catch (FileNotFoundException e){
            System.out.println("PAS DE FICHIER : "+args[0]);
        }

    }
}
