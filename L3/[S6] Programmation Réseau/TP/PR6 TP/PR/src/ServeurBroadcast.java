import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServeurBroadcast{
    public static void main(String[] args){
        try{
            DatagramSocket socket=new DatagramSocket(12321);
            while(true){
                //reception
                byte[] data=new byte[1024];
                DatagramPacket receive=new DatagramPacket(data, data.length);
                socket.receive(receive);
                
                String message=new String(receive.getData(), 0, receive.getLength());
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date(System.currentTimeMillis());
                
                //envoie
                String s=receive.getAddress()+"\t"+formatter.format(date)+"\t"+message;
                data=s.getBytes();
                InetSocketAddress ia=new InetSocketAddress("255.255.255.255", 10101);
                DatagramPacket toSend=new DatagramPacket(data, data.length, ia);
                socket.send(toSend);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}