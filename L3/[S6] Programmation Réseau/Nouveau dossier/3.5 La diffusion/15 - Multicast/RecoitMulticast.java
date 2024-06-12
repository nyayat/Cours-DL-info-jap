import java.io.*;
import java.net.*;

public class RecoitMulticast {
    public  static void main(String[] args){
        try{
            MulticastSocket mso=new MulticastSocket(9999);
            mso.joinGroup(InetAddress.getByName("225.1.2.4"));
            byte[]data=new byte[100];
            DatagramPacket paquet=new DatagramPacket(data,data.length);
            while(true){
                mso.receive(paquet);
                String st=new String(paquet.getData(),0,paquet.getLength());
                System.out.println("J'ai reçu :"+st);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
