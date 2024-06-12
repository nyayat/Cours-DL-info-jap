import java.util.List;

//2.
public class Paire<X, Y>{
    //2.1
    X gauche;
    Y droite;
    
    Paire(X x, Y y){
        gauche=x;
        droite=y;
    }
    
    X getGauche(){
        return gauche;
    }
    
    Y getDroite(){
        return droite;
    }
    
    void setGauche(X x){
        gauche=x;
    }
    
    void setDroite(Y y){
        droite=y;
    }
    
    //2.2
    static <U extends Number, V extends Number> Paire<Double, Double> somme(List<Paire<U, V>> aSommer){
        Paire<Double, Double> res=new Paire<Double, Double>(0.0, 0.0);
        for(int i=0; i<aSommer.size(); i++){
            res.droite+=(Double)aSommer.get(i).droite;
            res.gauche+=(Double)aSommer.get(i).gauche;
        }
        return res;
    }
    
    //2.3
    //Paire<Number, Number>
    //Paire<? extends Number, ? extends Number>
    /*Dans le 2e cas, on ne pourra pas faire d'écriture, car seul l'accès en
        lecture est autorisé.
    */
    
    //2.4
    //Avec les extends, seul les getteurs serviront (accès en lecture).
    //Le contraire avec les super.
    /*L'instance Paire est imuable, donc les droits en écriture ne sont pas
        nécessaires, donc il faudrait la déclarer avec des extends.
    */
}