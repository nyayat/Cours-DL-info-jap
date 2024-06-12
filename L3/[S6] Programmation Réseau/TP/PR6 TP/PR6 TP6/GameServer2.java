import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//3.6
public class GameServer2 implements Runnable{
    Socket player1, player2;
    
    GameServer2(Socket p1, Socket p2){
        player1=p1;
        player2=p2;
    }
    
    @Override
    public void run(){
        try{
            BufferedReader buff1=new BufferedReader(new InputStreamReader(player1.getInputStream()));
            PrintWriter printW1=new PrintWriter(new OutputStreamWriter(player1.getOutputStream()));
            BufferedReader buff2=new BufferedReader(new InputStreamReader(player2.getInputStream()));
            PrintWriter printW2=new PrintWriter(new OutputStreamWriter(player2.getOutputStream()));

            printW1.print("START\n");
            printW1.flush();
            printW2.print("START\n");
            printW2.flush();
            
            int number=(int)(Math.random()*65536);
            int guess1=0;
            int guess2=0;
            String tmp;
            for(int i=0; i<10; i++){
                do{
                    tmp=buff1.readLine();
                }
                while(!tmp.matches("[0-9]+") || (guess1=Integer.parseInt(tmp))>65535 || guess1<0);
                do{
                    tmp=buff2.readLine();
                }
                while(!tmp.matches("[0-9]+") || (guess2=Integer.parseInt(tmp))>65535 || guess2<0);

                if(guess1==number){
                    if(guess2!=number){
                        printW1.print("GAGNE\n");
                        printW2.print("PERDU : "+number+"\n");
                    }
                    else{
                        printW1.print("DRAW\n");
                        printW2.print("DRAW\n");
                    }
                    endGame(buff1, printW1, player1);
                    endGame(buff2, printW2, player2);
                    return;
                }
                if(guess2==number){
                    printW1.print("PERDU : "+number+"\n");
                    printW2.print("GAGNE\n");
                    endGame(buff1, printW1, player1);
                    endGame(buff2, printW2, player2);
                    return;
                }
                if(guess1<number) printW1.print("PLUS "+(19-i)+"\n");
                else printW1.print("MOINS "+(9-i)+"\n");
                printW1.flush();
                if(guess2<number) printW2.print("PLUS "+(19-i)+"\n");
                else printW2.print("MOINS "+(9-i)+"\n");
                printW2.flush();
            }
            printW1.print("PERDU : "+number+"\n");
            printW2.print("PERDU : "+number+"\n");
            endGame(buff1, printW1, player1);
            endGame(buff2, printW2, player2);
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
                Socket player1=server.accept();
                Socket player2=server.accept();
                new Thread(new GameServer2(player1, player2)).start();
            }
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}