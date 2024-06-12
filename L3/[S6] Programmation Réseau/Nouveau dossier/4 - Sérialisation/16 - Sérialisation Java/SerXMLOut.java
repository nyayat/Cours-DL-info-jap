import java.beans.XMLEncoder;
import java.io.FileOutputStream;

public class SerXMLOut{
    public static void main(String[]args){
        try{
            Joueur j1=new Joueur("Alice",12);
            Joueur j2=new Joueur("Bob",14);
            FileOutputStream fo=new FileOutputStream("Joueurs.xml");
            XMLEncoder xe=new XMLEncoder(fo);
            xe.writeObject(j1);
            xe.writeObject(j2);
            xe.close();
            fo.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
