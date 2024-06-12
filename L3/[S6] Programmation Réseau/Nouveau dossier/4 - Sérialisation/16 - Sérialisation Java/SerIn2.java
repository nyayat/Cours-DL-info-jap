import java.io.*;

public class SerIn2{
    public static void main(String[]args){
        try{
            Personne p1=null;
             Personne p2=null;
             Personne p3=null;
            FileInputStream fi=new FileInputStream("PersonneObj.bin");
            ObjectInputStream ois=new ObjectInputStream(fi);
            try{
                p1=(Personne)ois.readObject();
                p2=(Personne)ois.readObject();
                p3=(Personne)ois.readObject();
                if(p1.getChien()==p2.getChien()){
                    System.out.println("Même chien");
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
