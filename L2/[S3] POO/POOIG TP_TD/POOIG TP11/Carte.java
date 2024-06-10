import java.util.HashMap;

public class Carte {
    //10.
    private final String valeur, couleur;
    //13.
    HashMap<Carte,Integer> scores;
    
    Carte(String v, String c){
        //10.
        valeur=v;
        couleur=c;
        //13.
        scores.put(this, this.carteScore(v));
    }
    
    String getV(){
        return this.valeur;
    }
    
    String getC(){
        return this.couleur;
    }
    
    //11.
    public boolean equals(Object o){
        return (o.getClass()==Carte.class && ((Carte)o).valeur==this.valeur
            && ((Carte)o).couleur==this.couleur);
    }
    
    //12.
    public int hashCode(){
        return (2*this.valeur.hashCode()+3*this.couleur.hashCode());
    }
    
    /*Cette solution est préférable à "return 0;" car on fait une deuxième
      vérification. Sinon, il n'y a aucun intérêt à faire le test du hashCode.
    */
    
    //13.
    int carteScore(String c){
        switch(c){
            case "as":
                return 1;
            case "valet":
                return 11;
            case "dame":
                return 12;
            case "roi":
                return 13;
            default:
                return Integer.getInteger(c);
        }
    }
}