import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//3.5
public class BaseDeDonnees{
    final String nom;
    ArrayList<Tableau> db=new ArrayList<Tableau>();
    HashMap<String, ArrayList<Ligne>> data=new HashMap<String, ArrayList<Ligne>>();
    
    BaseDeDonnees(String n){
        nom=n;
    }
    
    Tableau ajouterTableau(String nom){
        Tableau t=new Tableau(nom);
        db.add(t);
        data.put(nom, new ArrayList<Ligne>());
        return t;
    }
    
    Tableau getTableau(String nom){
        for(Tableau t : db){
            if(t.nom.equals(nom)) return t;
        }
        return null;
    }
    
    //3.7
    void inserer(String nomTableau, List<Object> elements){
        for(Tableau t : db){
            if(t.nom.equals(nomTableau)){
                data.get(nomTableau).add(new Ligne(t.preparer(elements)));
                return;
            }
        }
        System.out.println("inexistent tab name");
    }
    
    //3.8
    List<Ligne> chercher(String nomTableau, String nomCol, Object valeur){
        ArrayList<Ligne> res=new ArrayList<Ligne>();
        for(Ligne l : data.get(nomTableau)){//pour les lignes du tableau nomTableau
            if(l.ligne.get(nomCol).equals(valeur)) res.add(l);
        }
        return res;
    }
}