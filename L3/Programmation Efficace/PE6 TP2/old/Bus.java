package TP2;

import java.util.ArrayList;

public class Bus {

    public enum BUS_TYPE {
        A,
        B,
        C
    }

    private int credit;
    private BUS_TYPE type;
    private ArrayList<Ville> trajet;

    public Bus(Ville depart, BUS_TYPE type) {
        this.credit = depart.getCredits(type);
        this.type = type;
        this.trajet = new ArrayList<Ville>();
        this.trajet.add(depart);
    }

    public void passerParVille(Ville ville, int cout){
        if (this.credit >= cout){
            this.credit -= cout;
            this.trajet.add(ville);
        }
    }

    public String toString(){
        String res="###\n"+type+"\n";
        for(Ville v : trajet) res+=v.getNom()+"\n";
        return res;
    }
}
