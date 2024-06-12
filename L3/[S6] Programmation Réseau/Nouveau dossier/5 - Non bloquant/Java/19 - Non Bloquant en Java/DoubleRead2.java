import java.nio.channels.*;
import java.util.Iterator;
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;

public class DoubleRead2{
    public static void main(String[] args){
        try{
            Selector sel=Selector.open();
            DatagramChannel dsc1=DatagramChannel.open();
            DatagramChannel dsc2=DatagramChannel.open();
            dsc1.configureBlocking(false);
            dsc2.configureBlocking(false);
            
            dsc1.bind(new InetSocketAddress(4344));
            dsc2.bind(new InetSocketAddress(4343));
           
            dsc1.register(sel,SelectionKey.OP_READ);
            dsc2.register(sel,SelectionKey.OP_READ);
            ByteBuffer buff=ByteBuffer.allocate(100);
            while(true){
                System.out.println("Waiting for messages");
                sel.select();
                Iterator<SelectionKey> it=sel.selectedKeys().iterator();
                while(it.hasNext()){
                    SelectionKey sk=it.next();
                    it.remove();
                    if(sk.isReadable() && sk.channel()==dsc1){
                       
                        System.out.println("Message UDP 4344 recu");
                        dsc1.receive(buff);
                        String st=new String(buff.array(),0,buff.array().length);
                        buff.clear();
                        System.out.println("Message :"+st);
                    } else if (sk.isReadable() && sk.channel()==dsc2){
                        System.out.println("Message UDP 4343 recu");
                        dsc2.receive(buff);
                        String st=new String(buff.array(),0,buff.array().length);
                        System.out.println("Message :"+st);
                    } else{
                        System.out.println("Que s'est il passe");
                        
                    }
                }
            }
                
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
