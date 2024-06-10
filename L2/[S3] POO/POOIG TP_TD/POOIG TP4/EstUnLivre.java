//5.2
public class EstUnLivre extends Predicat {
    boolean estVrai(Media m){
        return(m instanceof Livre);
    }
}