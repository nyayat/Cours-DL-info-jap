import java.util.Scanner;

//1.
public class Messagerie implements Runnable{
    String message;
    final String nom; //pas dans l'enonce mais necessaire pour le pseudo
    final int id;
    static int tour=0;
    
    Messagerie(String s, int i){
        nom=s;
        id=i;
    }

    @Override
    public synchronized void run(){
        while(id!=tour){
            try{
                wait(1);
            }
            catch(InterruptedException e){}
        }
        System.out.println(nom+", saisir votre message :");
        Scanner sc=new Scanner(System.in);
        message=sc.next();
        System.out.println("Message de "+nom+" : "+message);
        tour=(tour==1)?0:1;
        notify();
    }
}