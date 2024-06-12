import java.net.*;
import java.io.*;
public class ClientHi{
    public static void main(String[] args){
        try{
            Socket socket=new Socket("lulu",4242);
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String mess=br.readLine();
            System.out.println("Message recu du serveur :"+mess);
            pw.print("HALLO\n");
            pw.flush();    
            pw.close();
            br.close();
            socket.close();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
