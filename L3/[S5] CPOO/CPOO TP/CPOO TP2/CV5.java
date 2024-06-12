//5.
public class CV5{
    private final Bac bac;
    private final Licence licence;
    private final DiplomeInge dInge;
    private final Master master;
    private final Doctorat doctorat;
    
    CV5(CVBuilder builder){
        this.bac=builder.bac;
        this.licence=builder.licence;
        this.dInge=builder.dInge;
        this.master=builder.master;
        this.doctorat=builder.doctorat;
    }
    
    static CVBuilder builder(){
        return new CVBuilder();
    }
        
    public String toString(){
        return ((bac!=null)?bac.toString():"")
            +((licence!=null)?licence.toString():"")
            +((dInge!=null)?dInge.toString():"")
            +((master!=null)?master.toString():"")
            +((doctorat!=null)?doctorat.toString():"");
    }
    
    
    public static class CVBuilder{
        private Bac bac;
        private Licence licence;
        private DiplomeInge dInge;
        private Master master;
        private Doctorat doctorat;
        
        public CVBuilder bac(Bac bac){
            this.bac=bac;
            return this;
        }
        
        public CVBuilder licence(Licence licence){
            this.licence=licence;
            return this;
        }
        
        public CVBuilder dInge(DiplomeInge dI){
            this.dInge=dI;
            return this;
        }
        
        public CVBuilder master(Master master){
            this.master=master;
            return this;
        }
        
        public CVBuilder doctorat(Doctorat doctorat){
            this.doctorat=doctorat;
            return this;
        }
        
        public CV5 build(){
            if(!validArgTest(this)) throw new IllegalArgumentException();
            return new CV5(this);
        }

        boolean validArgTest(CVBuilder b){
            return validArgTest(b.bac, b.licence, b.dInge, b.master, b.doctorat);
        }

        boolean validArgTest(Bac bac, Licence licence, DiplomeInge dI, Master master, Doctorat doctorat){
            if(bac==null && (licence!=null || dI!=null || master!=null || doctorat!=null)) return false;
            if((licence==null && dI==null) && (master!=null || doctorat!=null)) return false;
            if(master==null && doctorat!=null) return false;
            return true;
        }
    }
}