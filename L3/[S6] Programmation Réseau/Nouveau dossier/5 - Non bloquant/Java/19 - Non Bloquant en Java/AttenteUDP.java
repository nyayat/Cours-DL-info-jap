import java.net.DatagramSocket;
import java.net.DatagramPacket;

public class AttenteUDP extends Thread{
   
    int port;

    public AttenteUDP(int p){
        port=p;
    }
    
    public void run(){
        try{
            DatagramSocket ds=new DatagramSocket(port);
            byte[]data=new byte[100];
            DatagramPacket paquet=new DatagramPacket(data,data.length);
            while(true){
                ds.receive(paquet);
                String st=new 		                              
							String(paquet.getData(),0,paquet.getLength());
                System.out.println("PORT :"+port+", MESS :"+st);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
