import java.net.*;
import java.io.*;
public class ServeurHiConcur{
    public static void main(String[] args){
        try{
            ServerSocket server=new ServerSocket(4242);
            while(true){
                Socket socket=server.accept();
                ServiceHi serv=new ServiceHi(socket);
                Thread t=new Thread(serv);
                t.start();
            }   
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
