import java.util.*;
public class Meuble{
    public List<PetitObjet> contenu;
    public String desc;
    
    public Meuble(String d){
	desc = d;
	contenu = new LinkedList<>();
    }

    public void add(PetitObjet ob){
	 contenu.add(ob);
    }
}
