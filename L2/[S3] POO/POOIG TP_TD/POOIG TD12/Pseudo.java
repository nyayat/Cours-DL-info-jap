import java.util.LinkedList;
import java.util.Observable;

public class Pseudo extends Observable{
    private String pseudo;
    private LinkedList<ChangePseudoHistory> observateurs;
    
    Pseudo(String x){
        pseudo=x;
        observateurs=new LinkedList<>();
    }
    
    public void addObservateurs(ChangePseudoHistory[]h_tab){
        for(ChangePseudoHistory p : h_tab) observateurs.add(p);
    }
    
    public void set(String y){
        //1.
        /*for(ChangePseudoHistory p : observateurs) p.infoChange(pseudo, y);
        pseudo=y;*/
        //3.
        String old=pseudo;
        pseudo=y;
        setChanged();
        notifyObservers(old);
    }
    
    //3.
    public String getContent(){
        return pseudo;
    }
}