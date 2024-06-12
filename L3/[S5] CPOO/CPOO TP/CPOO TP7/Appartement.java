import java.util.*;
import static java.util.stream.Collectors.toList;

public class Appartement {
    public int nbPieces;
    public int prix;
    public String lieu;


    public Appartement(int nbPieces, int prix, String lieu){
	this.nbPieces = nbPieces;
	this.prix = prix;
	this.lieu = lieu;
    }

    public String toString(){
	return "("+nbPieces+ ", "+prix+ ", "+ lieu+")";
    }


    public static void main(String[] args){
	List<Appartement> appartements = new ArrayList<>();
	appartements.add(new Appartement(5, 5_000_000, "Paris"));
        appartements.add(new Appartement(1, 200_000, "Paris"));	
        appartements.add(new Appartement(5, 1_000_000, "Nancy"));
        appartements.add(new Appartement(4, 200_000, "Limoges"));
        appartements.add(new Appartement(1, 80_000, "Lille"));
        appartements.add(new Appartement(2, 200_000, "Brest"));
	appartements.add(new Appartement(1, 180_000, "Paris"));
        appartements.add(new Appartement(2, 500_000, "Paris"));
        
        //1.1.a
        int x=appartements.stream()
                .filter(a -> a.nbPieces>=3)
                .map(a -> a.prix)
                .reduce(Integer.MAX_VALUE, (a, b) -> (a<b)?a:b);
        int max=-1;
        for(Appartement a : appartements)
            if(a.nbPieces>=3 && (max==-1 || a.prix<max))
                max=a.prix;
        System.out.println("x="+x+" max="+max);
        
        //1.1.b
        /*Si la liste est vide, Java prend la valeur la plus grande possible
            du langage, càd 2147483647.
        */
        
        //2.1
        for(Appartement a : appartements)
            if(a.prix<=200_000) System.out.println(a.lieu);
        
        System.out.println();
        
        appartements.stream()
                .filter(a -> a.prix<=200_000)
                .map(a -> a.lieu)
                .forEach(System.out::println);
    }

}
