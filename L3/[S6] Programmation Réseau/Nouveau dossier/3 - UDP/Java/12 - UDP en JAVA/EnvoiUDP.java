import java.io.*;
import java.net.*;

public class EnvoiUDP {
    public  static void main(String[] args){
        try{
            DatagramSocket dso=new DatagramSocket();
            byte[]data;
            for(int i=0;i <= 60; i++){
               
                String s="MESSAGE "+i+" \n";
                data=s.getBytes();
                DatagramPacket paquet=new DatagramPacket(data,data.length, InetAddress.getByName("localhost"),5555);
                dso.send(paquet);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
