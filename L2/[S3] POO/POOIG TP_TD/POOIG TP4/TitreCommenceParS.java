//5.4
public class TitreCommenceParS extends Predicat {
    boolean estVrai(Media m){
        char temp=m.getTitre().charAt(0);
        return(temp=='S' || temp=='s');
    }
}