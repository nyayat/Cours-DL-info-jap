import java.util.*;

public class PetitObjet{
    public String desc;
    public int poids;//en grammes
    
    public PetitObjet(String d, int p){
	desc = d;
	poids = p;
    }
    public String toString(){
	return desc;
    }
}
