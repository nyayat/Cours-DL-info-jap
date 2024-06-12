public class DoubleRead{
    public static void main(String[] args){
        try{
            AttenteUDP au1=new AttenteUDP(4343);
            AttenteUDP au2=new AttenteUDP(4344);
            au1.start();
            au2.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
