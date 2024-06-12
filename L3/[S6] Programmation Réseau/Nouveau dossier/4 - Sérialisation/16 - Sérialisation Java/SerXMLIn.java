import java.beans.XMLDecoder;
import java.io.FileInputStream;

public class SerXMLIn{
    public static void main(String[]args){
        try{
            Joueur j=null;
            FileInputStream fi=new FileInputStream("Joueurs.xml");
            XMLDecoder xd=new XMLDecoder(fi);
            try{
                j=(Joueur)xd.readObject();
                while(true){
                    System.out.println(j.toString());
                    j=(Joueur)xd.readObject();
                }
            }catch(ArrayIndexOutOfBoundsException aie){
                //Fin du parsing
            }          
            xd.close();
            fi.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
