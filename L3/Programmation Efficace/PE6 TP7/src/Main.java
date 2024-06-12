import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {
        try {
            FileWriter f = new FileWriter("TP7/tests/output.out");
            for (int i = 3; i <= 83; i++) {
                String filename = "TP7/tests/" + String.format("%02d.in", i);
                System.out.println("Test " + filename);
                Problem p = new Problem(filename);
                int res = p.getSolution();
                if (res == -1)
                    f.write("infini\n");
                else
                    f.write(res + "\n");
                f.flush();

            }
            f.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
