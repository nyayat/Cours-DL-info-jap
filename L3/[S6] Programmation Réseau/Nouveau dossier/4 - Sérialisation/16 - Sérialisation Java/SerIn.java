import java.io.*;

public class SerIn{
    public static void main(String[]args){
        try{
            Personne p=null;
            FileInputStream fi=new FileInputStream("PersonneObj.bin");
            ObjectInputStream ois=new ObjectInputStream(fi);
            try{
                while(true){
 					 p=(Personne)ois.readObject();
                    System.out.println(p.toString());
                }
            }catch(EOFException eof){
                //Fin du fichier
            }
            
            ois.close();
            fi.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
