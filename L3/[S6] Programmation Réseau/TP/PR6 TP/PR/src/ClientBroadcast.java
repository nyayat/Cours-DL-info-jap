import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientBroadcast{
    public static void main(String[] args){
        Thread send=new Thread(() -> {
            try{
                DatagramSocket socket=new DatagramSocket();
                Scanner sc=new Scanner(System.in);
                while(sc.hasNext()){
                    String toSend=sc.next();
                    byte[] data=toSend.getBytes();

                    DatagramPacket paquet=new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 12321);
                    socket.send(paquet);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });
        
        Thread receive=new Thread(() -> {
            try{
                DatagramSocket socket=new DatagramSocket(10101);
                while(true){
                    byte[] data=new byte[1024];
                    DatagramPacket packet=new DatagramPacket(data, data.length);
                    socket.receive(packet);
                    String messR=new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Received from server : "+messR);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });
        
        send.start();
        receive.start();
    }
}