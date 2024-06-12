//4.
public class CV4{
    private final Bac bac;
    private final Licence licence;
    private final DiplomeInge dInge;
    private final Master master;
    private final Doctorat doctorat;
    
    CV4(CV4Builder builder){
        if(!validArgTest(builder)) throw new IllegalArgumentException();
        this.bac=builder.bac;
        this.licence=builder.licence;
        this.dInge=builder.dInge;
        this.master=builder.master;
        this.doctorat=builder.doctorat;
    }
    
    boolean validArgTest(CV4Builder b){
        return validArgTest(b.bac, b.licence, b.dInge, b.master, b.doctorat);
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