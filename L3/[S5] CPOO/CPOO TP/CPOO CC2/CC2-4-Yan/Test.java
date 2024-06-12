import java.util.ArrayList;
import java.util.List;

//2.2
public class Test{
    public static void main(String[] args){
        List<Thread> jobs=new ArrayList();
        for(int i=0; i<1/*0*/; i++)
            if(i<5) jobs.add(new Thread(new Element("H", i)));
            else jobs.add(new Thread(new Element("O", i)));
        for(Thread t : jobs) t.start();
    }
}