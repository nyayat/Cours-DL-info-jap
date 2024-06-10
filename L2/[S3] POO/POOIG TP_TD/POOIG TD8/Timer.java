import java.awt.event.*;
import java.time.LocalTime;

public class Timer {
    //4.1
    int delay=1000;
    ActionListener taskPerformer=new ActionListener(){
        public void actionPerformed(ActionEvent evt){
            System.out.println(LocalTime.now());
        }
    };
    Timer(int i, ActionListener a){}
    
    Timer t1=new Timer(delay, taskPerformer);
    t1.start();
    
    //4.2
    Timer t3=new Timer(delay, (evt)-> System.out.println(LocalTime.now()));
    t3.start();
    
    //4.3
    ActionListener compteMoutons=new ActionListener(){
        private int count=0;
        public void actionPerformed(ActionEvent evt){
            System.out.print(++count);
        }
    };
    Timer t2=new Timer(2000, compteMoutons);
    t2.start();
}