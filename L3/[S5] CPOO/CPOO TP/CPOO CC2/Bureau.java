import java.util.HashMap;
import java.util.LinkedList;

//2.
public class Bureau{
    //2.2
    LinkedList<Electeur> electeurs=new LinkedList<Electeur>();
    HashMap<String, Integer> choix=new HashMap<String, Integer>();
    
    Bureau(int n){
        choix.put("oui", 0);
        choix.put("non", 0);
        for(int i=0; i<n; i++)
            electeurs.add(new Electeur(Math.random()<0.3?"non":"oui", this));
    }
}