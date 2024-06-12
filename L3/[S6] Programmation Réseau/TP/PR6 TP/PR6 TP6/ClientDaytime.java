import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

//1.
public class ClientDaytime{
    public static void main(String[] args){
        try{
            Socket socket=new Socket("lampe.informatique.univ-paris-diderot.fr", 13);
            BufferedReader buff=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("date : "+buff.readLine());
            buff.close();
            socket.close();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}