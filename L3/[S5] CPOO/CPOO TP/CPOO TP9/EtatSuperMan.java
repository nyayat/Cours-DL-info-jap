//2.1
public class EtatSuperMan extends EtatPersonnage{
    @Override
    public String toString(){
        return "SuperMan";
    }
    
    //2.2
    private EtatSuperMan(){}
    
    private static EtatSuperMan INSTANCE=null;
    
    public static EtatSuperMan getInstance(){
        if(INSTANCE==null) INSTANCE=new EtatSuperMan();
        return INSTANCE;
    }
}