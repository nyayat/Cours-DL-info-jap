import java.util.*;
import java.util.stream.*;

public class Requete{
   
    
    public static void main(String[] args){
	Maison maison = new Maison();
	Meuble a = new Meuble("Armoire");
	a.add(new PetitObjet("chemise", 100));
	a.add(new PetitObjet("pantalon", 200));
	maison.add(a);
	Meuble b = new Meuble("Lit");
	maison.add(b);
	Meuble c = new Meuble("Vaisselier");
	c.add(new PetitObjet("assiette",150));
	c.add(new PetitObjet("verre",70));
	c.add(new PetitObjet("verre",75));
	maison.add(c);
        
        //1.2.a
        List<PetitObjet> inventaire=maison.meubles.stream()
                .flatMap(m -> m.contenu.stream())
                .collect(Collectors.toList());
        List<PetitObjet> res=new LinkedList<PetitObjet>();
        for(Meuble m : maison.meubles)
            for(PetitObjet o : m.contenu)
                res.add(o);
        
        for(PetitObjet o : inventaire) System.out.println(o);
        System.out.println();
        for(PetitObjet o : res) System.out.println(o);
        
        //1.2.b
        //Si la liste est vide, rien ne sera afficher, c'est tout.
        
        //2.2
        int pdsTotal=0;
        for(Meuble m: maison.meubles)
            for(PetitObjet obj : m.contenu)
                pdsTotal+=obj.poids;
        System.out.println(pdsTotal);
        
        int poids=maison.meubles.stream()
                .flatMap(m -> m.contenu.stream())
                .mapToInt(o -> o.poids)
                .reduce(0, (x, y) -> x+y);
        System.out.println(poids);
    }
}



