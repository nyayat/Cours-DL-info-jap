//5.5
public class Et extends Predicat {
    private Predicat p1;
    private Predicat p2;
    
    Et(Predicat p1, Predicat p2){
        this.p1=p1;
        this.p2=p2;
    }
    
    boolean estVrai(Media m){
        return (this.p1.estVrai(m) && this.p2.estVrai(m));
    }
}