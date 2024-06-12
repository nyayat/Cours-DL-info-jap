import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Bougies{
    public static void main(String[] args){
        try{
            String filename="test/Bougies/candle";
            for(int i=1; i<15; i++){
                File file=new File(filename+(i<10?"0"+i:i)+".in");
                Scanner sc=new Scanner(file);

                int diffAge=sc.nextInt();
                int bougiesInitRita=sc.nextInt();
                int bougiesInitTheo=sc.nextInt();
                int totalBougies=bougiesInitRita+bougiesInitTheo;
                
                int ageRita=4+diffAge-1;
                int bougiesRita=4;
                for(int j=0; j<diffAge-1; j++) bougiesRita+=5+j;
                
                int ageTheo=3;
                int bougiesTheo=3;
                
                while(totalBougies!=bougiesRita+bougiesTheo){
                    bougiesRita+=++ageRita;
                    bougiesTheo+=++ageTheo;
                }

                FileWriter output=new FileWriter(filename+(i<10?"0"+i:i)+".myout");
                output.write(Integer.toString(bougiesInitRita-bougiesRita));
                output.close();
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}