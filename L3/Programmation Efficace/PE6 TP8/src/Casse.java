import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Casse{
    public static void main(String[] args){
        try{
            String filename="test/Casse/test0";
            for(int i=1; i<5; i++){
                File file=new File(filename+i+".in");
                Scanner sc=new Scanner(file);

                int largeur=sc.nextInt();
                int nbParts=sc.nextInt();
                int aire=0;
                for(int j=0; j<nbParts; j++) aire+=sc.nextInt()*sc.nextInt();

                FileWriter output=new FileWriter(filename+i+".out");
                output.write(Integer.toString(aire/largeur));
                output.close();
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}