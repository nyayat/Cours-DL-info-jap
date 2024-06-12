import java.io.FileNotFoundException;

public class Main {
    //exemple d'utilisation (avec un jar) : java -jar TP2.jar newprobA max
    //exemple d'utilisation (avec un jar) : java -jar TP2.jar oldprobB 30
    //permet de jouer sur la longueur maximale de chaque chemin en fonction de la memoire allouee
    public static void main(String[] args){
        try{
            World w=World.fromFile("test/"+args[0]);
            w.createTrajets(args[1].equals("max")?w.nbVilles:Integer.parseInt(args[1]));
            w.toFile("test/"+args[0]);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
}
