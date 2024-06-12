import java.io.*;
import java.net.*;

public class RecoitBroadcast {
    public  static void main(String[] args){
        try{
            DatagramSocket dso=new DatagramSocket(8888);
            byte[]data=new byte[100];
            DatagramPacket paquet=new DatagramPacket(data,data.length);
            while(true){
                dso.receive(paquet);
                String st=new 
  					  String(paquet.getData(),0,paquet.getLength());
                System.out.println("J'ai reçu :"+st);
            }
        } catch(Exception e){
            e.printStackTrace();
       }    
    }
}
