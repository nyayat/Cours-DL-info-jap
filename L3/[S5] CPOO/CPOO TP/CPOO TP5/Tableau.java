import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//3.4
public class Tableau{
    String nom;
    ArrayList<Colonne> tab=new ArrayList<Colonne>();
    static int n=0;
    
    Tableau(String n){
        nom=n;
        ajouterColonne("id", TypeDonnee.INT);
    }
    
    void ajouterColonne(String nom, TypeDonnee type){
        tab.add(new Colonne(nom, type));
    }
    
    //3.6
    Map<String, Object> preparer(List<Object> elements){
        Map<String, Object> res=new HashMap<String, Object>();
        res.put("id", n++);
        for(int i=1; i<tab.size(); i++) res.put(tab.get(i).nom, elements.get(i-1));
        return res;
    }
}