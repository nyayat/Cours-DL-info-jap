//3.
public class CV3{
    private Bac bac;
    private Licence licence;
    private DiplomeInge dInge;
    private Master master;
    private Doctorat doctorat;
    
    CV3(){
        bac=null;
        licence=null;
        dInge=null;
        master=null;
        doctorat=null;
    }
    
    boolean setBac(Bac bac){
        this.bac=bac;
        return true;
    }
    
    boolean setLicence(Licence licence){
        if(this.bac==null) return false;
        this.licence=licence;
        return true;
    }
    
    boolean setDI(DiplomeInge dI){
        if(this.bac==null) return false;
        this.dInge=dI;
        return true;
    }
    
    boolean setMaster(Master master){
        if(this.bac==null && (this.licence==null || this.dInge==null)) return false;
        this.master=master;
        return true;
    }
    
    boolean setDoctorat(Doctorat doctorat){
        if(this.bac==null && (this.licence==null || this.dInge==null) && this.master==null)
            return false;
        this.doctorat=doctorat;
        return true;
    }
        
    public String toString(){
        return ((bac!=null)?bac.toString():"")
            +((licence!=null)?licence.toString():"")
            +((dInge!=null)?dInge.toString():"")
            +((master!=null)?master.toString():"")
            +((doctorat!=null)?doctorat.toString():"");
    }
    
    //3.1
    //Cela resoud le probleme des coherences
    
    //3.2
    //Il faudra modifier les diplomes dans un certain ordre croissant d importance.
    
    //3.3
    //Avec ce genre d approche, impossible de rendre les objets immuables.
}