import java.io.*;
import java.net.*;

public class EnvoiBroadcast {
    public  static void main(String[] args){
        try{
            DatagramSocket dso=new DatagramSocket();
            byte[]data;
            for(int i=0;i <= 10; i++){
                Thread.sleep(1000);
                String s="MESSAGE "+i+" \n";
                data=s.getBytes();
                InetSocketAddress ia=new  		   	     
                    InetSocketAddress("255.255.255.255",8888);
                DatagramPacket paquet=new 
                    DatagramPacket(data,data.length,ia);
                dso.send(paquet);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
