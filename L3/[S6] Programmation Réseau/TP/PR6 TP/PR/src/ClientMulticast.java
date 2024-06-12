import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class ClientMulticast{
    public static void main(String[] args){
        Thread send=new Thread(() -> {
            try{
                DatagramSocket socket=new DatagramSocket();
                Scanner sc=new Scanner(System.in);
                while(sc.hasNext()){
                    String toSend=sc.next();
                    byte[] data=toSend.getBytes();

                    DatagramPacket paquet=new DatagramPacket(data, data.length, InetAddress.getByName("225.1.2.4"), 12121);
                    socket.send(paquet);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });
        
        Thread receive=new Thread(() -> {
            try{
                MulticastSocket socket=new MulticastSocket(10201);
                socket.joinGroup(InetAddress.getByName("225.1.2.4"));
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