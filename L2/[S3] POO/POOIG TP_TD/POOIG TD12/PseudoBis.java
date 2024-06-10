import java.util.LinkedList;

//2.
public class PseudoBis<T> {
    private T pseudo;
    private LinkedList<CPHBis> observateurs;
    
    PseudoBis(T x){
        pseudo=x;
        observateurs=new LinkedList<>();
    }
    
    public void addObservateurs(CPHBis[]h_tab){
        for(CPHBis p : h_tab) observateurs.add(p);
    }
    
    public void set(T y){
        for(CPHBis p : observateurs) p.infoChangeBis(pseudo, y);
        pseudo=y;
    }
}