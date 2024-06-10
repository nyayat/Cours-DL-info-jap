import java.io.*;
import java.lang.*;

public class Test {
    public static void main(String[]a) throws FileNotFoundException, UnableToDeleteFileException{
        //4.
        /*StringTransformation addBlah=(String s)->s+".blah";
        String s=addBlah.transf("test");
        System.out.println(s);*/
        
        //try{
            Arbre a1=new Arbre("D:\\DL Info-Jap\\DL2 S3");  //chemin du fichier
            //4.
            /*a1.map(addBlah);
            a1.afficher();*/
            
            //5.
            a1.traverser("pdf");
            System.out.println("--------------");
            a1.supprimer("pdf");
            a1.traverser("pdf");
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        catch(UnableToDeleteFileException e){
            System.out.println("Can not delete this file.");
        }
    }
}