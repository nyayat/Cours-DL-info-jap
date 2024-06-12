//2.1
public class EtatHumain extends EtatPersonnage{
    @Override
    public String toString(){
        return "humain";
    }
    
    //2.2
    private EtatHumain(){}
    
    private static EtatHumain INSTANCE=null;
    
    public static EtatHumain getInstance(){
        if(INSTANCE==null) INSTANCE=new EtatHumain();
        return INSTANCE;
    }
}