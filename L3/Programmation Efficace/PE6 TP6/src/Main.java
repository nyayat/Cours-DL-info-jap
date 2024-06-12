import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileWriter f = new FileWriter("test/test2.out");
            for (int i = 1; i <= 50; i++) {
                Algo algo = new Algo("test/" + String.format("%02d.in", i));
                algo.repartition();
                f.write(algo.getCout() + "\n");
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
