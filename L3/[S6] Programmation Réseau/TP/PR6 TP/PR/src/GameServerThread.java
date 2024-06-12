import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//3.5
public class GameServerThread implements Runnable{
    Socket socket;
    
    GameServerThread(Socket s){
        socket=s;
    }
    
    @Override
    public void run(){
        try{
            BufferedReader buff=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printW=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            int number=(int)(Math.random()*65536);
            int guess=0;
            String tmp;
            for(int i=0; i<20; i++){
                do{
                    tmp=buff.readLine();
                }
                while(!tmp.matches("[0-9]+") || (guess=Integer.parseInt(tmp))>65535 || guess<0);

                if(guess<number) printW.print("PLUS "+(19-i)+"\n");
                else if(guess>number) printW.print("MOINS "+(19-i)+"\n");
                else{
                    printW.print("GAGNE\n");
                    endGame(buff, printW, socket);
                    return;
                }
                printW.flush();
            }
            printW.print("PERDU : "+number+"\n");
            endGame(buff, printW, socket);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    static void endGame(BufferedReader buff, PrintWriter printW, Socket socket) throws IOException{
        printW.flush();
        buff.close();
        printW.close();
        socket.close();
    }
    
    public static void main(String[] args){
        try{
            ServerSocket server=new ServerSocket(Integer.parseInt(args[0]));
            while(true){
                Socket socket=server.accept();
                new Thread(new GameServerThread(socket)).start();
            }
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
