import java.util.ArrayList;

public class Bus {

    public enum BUS_TYPE {
        A,
        B,
        C;
    	
    	public static BUS_TYPE getType(int i) {
            return i==0?A:(i==1?B:C);
        }
    }

    int credit; //on decremente petit a petit
    BUS_TYPE type;
    ArrayList<Ville> trajet;

    public Bus(Ville depart, BUS_TYPE type) {
        this.credit = depart.getCredits(type);
        this.type = type;
        this.trajet = new ArrayList<Ville>();
        this.trajet.add(depart);
    }

    public void passerParVille(Ville ville, int cout){
        this.credit -= cout;
        this.trajet.add(ville);
        ville.visit();
    }

    public String toString(){
        String res="###\n"+type+"\n";
        for(Ville v : trajet) res+=v.getNom()+"\n";
        return res;
    }
}
