import java.util.LinkedList;
import java.util.List;

//2.
public class Interval{
    long debut, fin;
    
    Interval(long d, long f){
        debut=d;
        fin=f;
    }
    
    List<Interval> diviser(int t){
        LinkedList<Interval> res=new LinkedList<Interval>();
        long debTmp=2;
        long interval=(fin-debut)/t;
        long finTmp=debTmp+interval;
        for(int i=0; i<t; i++){
            res.add(new Interval(debTmp, (finTmp<fin)?finTmp:fin));
            debTmp+=interval+1;
            finTmp=debTmp+interval;
        }
        return res;
    }
}