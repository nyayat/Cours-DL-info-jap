import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Decoration{
    public static void main(String[] args){
        try{
            String filename="test/Decoration/test0";
            for(int i=0; i<10; i++){
                File file=new File(filename+i+".in");
                Scanner sc=new Scanner(file);

                int n=sc.nextInt();
                long[] couleur=new long[3];
                long[] largeurs = {0L, 0L, 0L};
                long[] hauteurs = {0L, 0L, 0L};

                int[] largeur=new int[n];
                int[] hauteur=new int[n];

                for(int l=0; l<n; l++) largeur[l]=sc.nextInt();
                for(int h=0; h<n; h++) hauteur[h]=sc.nextInt();

                for(int l=0; l<n; l++) {
                    largeurs[(l + l + 2) % 3] += largeur[l];
                    hauteurs[(l + l + 2) % 3] += hauteur[l];
                }
                
                couleur[0] = largeurs[0] * hauteurs[0] + largeurs[1] * hauteurs[2] + largeurs[2] * hauteurs[1];
                couleur[1] = largeurs[0] * hauteurs[2] + largeurs[1] * hauteurs[1] + largeurs[2] * hauteurs[0];
                couleur[2] = largeurs[0] * hauteurs[1] + largeurs[1] * hauteurs[0] + largeurs[2] * hauteurs[2];
                FileWriter output=new FileWriter(filename+i+".out");
                output.write(couleur[0]+" "+couleur[1]+" "+couleur[2]+" ");
                output.close();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}