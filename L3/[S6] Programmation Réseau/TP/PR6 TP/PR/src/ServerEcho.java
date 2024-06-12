import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//2.
public class ServerEcho {
    public static void main(String[] args){
        try{
            ServerSocket server=new ServerSocket(Integer.parseInt(args[0]));
            while(true){
                Socket socket=server.accept();
                BufferedReader buff=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printW=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                printW.print(buff.readLine());
                printW.flush();
                buff.close();
                printW.close();
                socket.close();
            }
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
