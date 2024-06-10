import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Etat {
    private final int id;
    private final boolean etatTerm;
    private Map<Character, Set<Etat>> transitions = new HashMap<Character, Set<Etat>>();
    
    public Etat(int id, boolean etatTerm) {
	this.id = id;
	this.etatTerm = etatTerm;
    }
   
    public int getId() {
	return id;
    }

    public boolean estTerminal(){
	return etatTerm;   
    }

    public final boolean equals(Object e){
	if(!(e instanceof Etat)) return false;
	return (((Etat)e).id == id);
    }
    
    public final int hashCode(){
	return id;
    }
    
    // ensemble des lettres étiquetant une transition sortante
    public Set<Character> alphabet() {
	return this.transitions.keySet();
    }

    // pour l'affichage
    public String toString() {
	String s = "Etat " + id;
	if(estTerminal()) s+=" (terminal)";
	s += "\n";
	for (char c : this.alphabet()) {
            for (Etat e: this.transitions.get(c)){
		s += c + " " +  e.id + "\n";
            }
	}
	return s;
    }
   
    //return null if c is not a key of transitions
    public Set<Etat> succ(char c) {
        return  transitions.get(c);
    }

    //if c is already a key of transitions, the old value is replaced by e
    public void ajouteTransition(char c, Etat e) {
        Set<Etat> es = new HashSet<Etat>(); 
        if(transitions.get(c) != null) es.addAll(transitions.get(c)); 
        es.add(e);
        transitions.put(c, es);
    }

    // premier algorithme d'acceptation d'un mot
    boolean accepte(String mot) {
	//si on a fini de lire 
	if (mot.length()==0) return estTerminal();// c'est accepté si état terminal
	else { //on suit les transition possibles de la première lettre
	    Set<Etat> es = this.succ(mot.charAt(0));
	    //si pas de transition c'est un échec
	    if(es == null) return false;
	    else{
                for (Etat e: es){
                    if (e.accepte(mot.substring(1))) return true;
                }	
                return false;
            }
	}
    }
    
    //2.
    Set<Etat> getEtat(char c){
        return(transitions.get(c));
    }
}
