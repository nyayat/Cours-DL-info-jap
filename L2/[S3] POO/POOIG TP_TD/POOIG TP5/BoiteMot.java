public class BoiteMot extends Boite {
    //2.
    String mot;
    
    BoiteMot(String mot){
        this.mot=mot;
    }
    
    public int length(){
        return this.mot.length();
    }
    
    public String toString(){
        return this.mot;
    }
}