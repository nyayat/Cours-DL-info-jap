import java.io.*;

public class SerOut{
    public static void main(String[]args){
        try{
            Chien c1=new Chien("Laika");
            Personne p1=new Personne("Alice",c1);
            Personne p2=new Personne("Bob",c1);
            Personne p3=new Personne("Charles",c1);
            FileOutputStream fo=new FileOutputStream("PersonneObj.bin");
            ObjectOutputStream os=new ObjectOutputStream(fo);
            os.writeObject(p1);
            os.writeObject(p2);
            os.writeObject(p3);
            os.close();
            fo.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
