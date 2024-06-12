import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

//3.3
public class GameClient{
    public static void main(String[] args){
        try{
            Socket socket=new Socket("127.0.0.1", Integer.parseInt(args[0]));
            BufferedReader buff=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printW=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            int min=0, max=65535, guess;
            String resServer;
            
            while(true){
                guess=(min+max)/2;
                printW.print(guess+"\n");
                printW.flush();
                
                resServer=buff.readLine();
                System.out.println("Guess="+guess+"\n"+resServer);
                if(resServer.equals("GAGNE\n")){
                    endClient(buff, printW, socket);
                    return;
                }
                if(resServer.startsWith("PLUS")) min=guess;
                else if(resServer.startsWith("MOINS")) max=guess;
                else{
                    endClient(buff, printW, socket);
                    return;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    static void endClient(BufferedReader buff, PrintWriter printW, Socket socket) throws IOException{
        buff.close();
        printW.close();
        socket.close();
    }
}
