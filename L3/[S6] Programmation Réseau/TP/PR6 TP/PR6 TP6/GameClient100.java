import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

//3.4
public class GameClient100 implements Runnable{
    Socket socket;
    int name;

    GameClient100(Socket s, int name){
        socket=s;
        this.name=name;
    }

    @Override
    public void run(){
        try{
            BufferedReader buff=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printW=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            int min=0, max=65535, guess;
            String resServer;
            
            while(true){
                guess=(min+max)/2;
                printW.print(guess+"\n");
                printW.flush();
                
                resServer=buff.readLine();
                System.out.println("Guess ("+name+") = "+guess+"\n"+resServer);
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
    
    public static void main(String[] args){
        for(int i=0; i<100; i++){
            try{
                Socket socket=new Socket("127.0.0.1", Integer.parseInt(args[0]));
                new Thread(new GameClient100(socket, i)).start();
            }
            catch(Exception e){
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }
}