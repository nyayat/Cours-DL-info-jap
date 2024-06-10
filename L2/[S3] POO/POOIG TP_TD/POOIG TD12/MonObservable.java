public class MonObservable {
    boolean aChange=false;
    MonObserver[] observateur;
    
    void setChanged(){
        aChange=true;
    }
    
    boolean hasChanged(){
        return aChange;
    }
    
    void clearChanged(){
        aChange=false;
    }
    
    void addObserver(MonObserver o){
        observateur.add(o);
    }
    
    void NotifyObservers(Object param){
        for(MonObserver o : observateur) o.update(this, param);
        clearChanged();
    }
}