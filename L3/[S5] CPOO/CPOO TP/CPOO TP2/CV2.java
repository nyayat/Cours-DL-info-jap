//2.
public class CV2{
    private Bac bac;
    private Licence licence;
    private DiplomeInge dInge;
    private Master master;
    private Doctorat doctorat;
    
    CV2(){
        bac=null;
        licence=null;
        dInge=null;
        master=null;
        doctorat=null;
    }
    
    void setBac(Bac bac){
        this.bac=bac;
    }
    
    void setLicence(Licence licence){
        this.licence=licence;
    }
    
    void setDI(DiplomeInge dI){
        this.dInge=dI;
    }
    
    void setMaster(Master master){
        this.master=master;
    }
    
    void setDoctorat(Doctorat doctorat){
        this.doctorat=doctorat;
    }
        
    public String toString(){
        return ((bac!=null)?bac.toString():"")
            +((licence!=null)?licence.toString():"")
            +((dInge!=null)?dInge.toString():"")
            +((master!=null)?master.toString():"")
            +((doctorat!=null)?doctorat.toString():"");
    }
    
    //On peut avoir des problemes d incoherence de CV avec cette approche.
}