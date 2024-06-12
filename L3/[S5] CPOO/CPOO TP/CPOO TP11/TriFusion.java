import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ForkJoinTask;
import static java.util.concurrent.ForkJoinTask.adapt;

//1.
public class TriFusion{
    protected static <E extends Comparable<? super E>> List<E> fusion(List<E> l1, List<E> l2){
        List<E> res=new ArrayList<>(l1.size()+l2.size());
        ListIterator<E> it1=l1.listIterator(), it2=l2.listIterator();
        while(it1.hasNext() || it2.hasNext())
            if(it1.hasNext() &&
              (!it2.hasNext() || l1.get(it1.nextIndex()).compareTo(l2.get(it2.nextIndex()))<0))
                res.add(it1.next());
            else res.add(it2.next());
        return res;
    }
    
    public static <E extends Comparable<? super E>> List<E> triMonoThread(List<E> l){
        if(l.size()<2) return l;
        int pivot=Math.floorDiv(l.size(), 2);
        List<E> l1=triMonoThread(l.subList(0, pivot));
        List<E> l2=triMonoThread(l.subList(pivot, l.size()));
        return fusion(l1, l2);
    }
    
    public static <E extends Comparable<? super E>> List<E> triMultiThread(List<E> l){
        if(l.size()<2) return l;
        System.out.println("before "+l.size()+" -> "+Thread.currentThread().getName());
        int pivot=Math.floorDiv(l.size(), 2);
        ForkJoinTask<List<E>> f1=adapt(() -> triMultiThread(l.subList(0, pivot))).fork();
        ForkJoinTask<List<E>> f2=adapt(() -> triMultiThread(l.subList(pivot, l.size()))).fork();
        List<E> res=fusion(f2.join(), f1.join());
        System.out.println("after "+l.size()+" -> "+Thread.currentThread().getName());
        return res;
    }
    
    public static void main(String[] args){
        //System.out.println(triMonoThread(Arrays.asList(234, 2134, 1, 122, 122, 2, 99, 12, 81)));
        System.out.println(triMultiThread(Arrays.asList(234, 2134, 1, 122, 122, 2, 99, 12, 81)));
    }
}