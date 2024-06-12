import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cuisson{
    public static void main(String[] args){
        try{
            File dir=new File("test/Cuisson");
            File[] fichiers=dir.listFiles();
            
            for(File f : fichiers){
                String file=f.toString();
                if(file.endsWith("in")){
                    file=file.substring(0, file.length()-2);
                    Scanner sc=new Scanner(f);
                    
                    int[] temps_in, temps_out;
                    HashMap<Integer, Integer> d = new HashMap<>();

                    int n = sc.nextInt();
                    int m = sc.nextInt();

                    temps_in = new int[n];
                    for (int i = 0; i < n; i++) {
                        temps_in[i] = sc.nextInt();
                    }

                    temps_out = new int[m];
                    for (int i = 0; i < m; i++) {
                        temps_out[i] = sc.nextInt();
                    }

                    for (int i : temps_in) {
                        for (int j : temps_out) {
                            int k = j - i;
                            if (k > 0) {
                                d.put(k, d.getOrDefault(k, 0) + 1);
                            }
                        }
                    }

                    int result = 0;
                    if (!d.isEmpty()) {
                        int maxValue = d.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
                        result = d.entrySet().stream().filter(x -> x.getValue() == maxValue).min(Map.Entry.comparingByKey()).get().getKey();
                    }
                    FileWriter output = new FileWriter(file + "out");
                    output.write(Integer.toString(result));
                    output.close();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}