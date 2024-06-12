import java.util.Set;
import java.util.TreeSet;

//2.1
public class Factorisation{
    public static Set<Long> factorize(long n){
        long m=(long)Math.sqrt(n);
        for(long d=m; d>1; m--)
            if(n%m==0){
                Set<Long> resFactors=factorize(d);
                resFactors.addAll(factorize(n/d));
                return resFactors;
            }
        Set<Long> resSingleton=new TreeSet<Long>();
        resSingleton.add(n);
        return resSingleton;
    }
}