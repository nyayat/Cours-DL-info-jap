import java.net.*;
import java.io.*;
import java.lang.*;

public class ServiceHi implements Runnable{
    public Socket socket;

    public ServiceHi(Socket s){
        this.socket=s;
    }

    public void run(){
        try{
            BufferedReader br=new BufferedReader(new 		
								InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(new 			
                                     OutputStreamWriter(socket.getOutputStream()));
            pw.print("HI\n");
            pw.flush();
            String mess=br.readLine();
            System.out.println("Message recu :"+mess);
            br.close();
            pw.close();
            socket.close();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
