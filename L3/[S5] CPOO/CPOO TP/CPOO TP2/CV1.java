//1.
public class CV1{
    private final Bac bac;
    private final Licence licence;
    private final DiplomeInge dInge;
    private final Master master;
    private final Doctorat doctorat;
    
    CV1(){
        this(null);
    }
    
    CV1(Bac bac){
        this(bac, (Licence)null);
    }
    
    CV1(Bac bac, Licence licence){
        this(bac, licence, (Master)null);
    }
    
    CV1(Bac bac, DiplomeInge dI){
        this(bac, dI, (Master)null);
    }
    
    CV1(Bac bac, Licence licence, Master master){
        this(bac, licence, null, master, null);
    }
    
    CV1(Bac bac, DiplomeInge dI, Master master){
        this(bac, null, dI, master, null);
    }
    
    CV1(Bac bac, Licence licence, Master master, Doctorat doctorat){
        this(bac, licence, null, master, doctorat);
    }
    
    CV1(Bac bac, DiplomeInge dI, Master master, Doctorat doctorat){
        this(bac, null, dI, master, doctorat);
    }
    
    CV1(Bac bac, Licence licence, DiplomeInge dI, Master master, Doctorat doctorat){
        if(!validArgTest(bac, licence, dI, master, doctorat)) throw new IllegalArgumentException();
        this.bac=bac;
        this.licence=licence;
        dInge=dI;
        this.master=master;
        this.doctorat=doctorat;
    }
    
    boolean validArgTest(Bac bac, Licence licence, DiplomeInge dI, Master master, Doctorat doctorat){
        if(bac==null && (licence!=null || dI!=null || master!=null || doctorat!=null)) return false;
        if((licence==null && dI==null) && (master!=null || doctorat!=null)) return false;
        if(master==null && doctorat!=null) return false;
        return true;
    }
        
    public String toString(){
        return ((bac!=null)?bac.toString():"")
            +((licence!=null)?licence.toString():"")
            +((dInge!=null)?dInge.toString():"")
            +((master!=null)?master.toString():"")
            +((doctorat!=null)?doctorat.toString():"");
    }
}