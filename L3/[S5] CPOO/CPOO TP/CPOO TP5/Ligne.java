import java.util.HashMap;
import java.util.Map;

//3.2
public class Ligne{
    HashMap<String, Object> ligne=new HashMap<String, Object>();
    
    Ligne(Map<String, Object> l){
        for(String nom : l.keySet()) ligne.put(nom, l.get(nom));
    }
    
    public Object get(String nom){
        return ligne.get(nom);
    }
}