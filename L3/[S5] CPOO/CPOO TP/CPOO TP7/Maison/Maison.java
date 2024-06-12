import java.util.*;
public class Maison{
    public List<Meuble> meubles;

    public Maison(){
	meubles = new LinkedList<>();
    }

    public void add(Meuble m){
	meubles.add(m);
    }
}
