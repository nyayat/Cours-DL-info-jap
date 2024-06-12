import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

//2.2
public class FactorisationFJ extends RecursiveTask<Set<Long>>{
    private static final long serialVersionUID=4302593939061433100L;
    private final long n;
    
    FactorisationFJ(long n){
        this.n=n;
    }

    @Override
    protected Set<Long> compute(){
        long m=(long)Math.sqrt(n);
        for(long d=m; d>1; m--)
            if(n%m==0){
                FactorisationFJ f1=new FactorisationFJ(d);
                f1.fork();
                FactorisationFJ f2=new FactorisationFJ(n/d);
                Set<Long> resFactors=f2.compute();
                resFactors.addAll(f1.join());
                return resFactors;
            }
        Set<Long> resSingleton=new TreeSet<Long>();
        resSingleton.add(n);
        return resSingleton;
    }
    
    public static void main(String[] args){
        System.out.println((new ForkJoinPool(5)).invoke(new FactorisationFJ(17300884069530000l)));
    }
}