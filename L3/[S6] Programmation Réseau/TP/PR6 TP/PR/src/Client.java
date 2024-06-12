import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client{
    public static void main(String[] args){
        try{
            DatagramSocket dso=new DatagramSocket();
            DatagramPacket toSend=new DatagramPacket(new byte[0], 0, InetAddress.getByName(args[1]), 1717);
            
            byte[] data=new byte[100];
            for(int i=0; i<Integer.valueOf(args[0]); i++){
                dso.send(toSend);
                DatagramPacket receive=new DatagramPacket(data, data.length);
                dso.receive(receive);
                System.out.println(new String(receive.getData(), 0, receive.getLength()));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}