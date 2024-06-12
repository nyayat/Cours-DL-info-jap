import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class ServeurUDP{
    static int getNbLines(String filename){
        int res=0;
        try{
            Scanner sc=new Scanner(new File(filename));
            sc.useDelimiter("%");
            while(sc.hasNext()){
                sc.next();
                res++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
    
    static byte[] getFortune(String filename){
        String res="";
        try{
            Scanner sc=new Scanner(new File(filename));
            sc.useDelimiter("%");
            
            int line=(int)(Math.random()*getNbLines(filename));
            for(int i=0; i<line; i++) res=sc.next();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res.getBytes();
    }
    
    public static void main(String[] args){
        try{
            DatagramSocket dso=new DatagramSocket(1717);
            byte[] tampon=new byte[100];
            while(true){
                //reception
                DatagramPacket receive=new DatagramPacket(tampon, tampon.length);
                dso.receive(receive);
                System.out.println("receive : "+new String(receive.getData()));
                
                //envoi
                tampon=getFortune("/usr/share/games/fortunes/fortunes");
                DatagramPacket toSend=new DatagramPacket(tampon, tampon.length,
                    new InetSocketAddress(receive.getAddress(), receive.getPort()));
                dso.send(toSend);
                System.out.println("send : "+new String(toSend.getData()));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}