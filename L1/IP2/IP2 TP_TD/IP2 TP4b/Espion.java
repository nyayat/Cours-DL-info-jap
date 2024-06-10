public class Espion {
    
    //1
    private String vraiNom;
    private boolean loyal;
    private final int id;
    static int n=1;
    
    Espion(String vrN, boolean l){
        vraiNom=vrN;
        loyal=l;
        id=n++;
    }
    
    Espion(String vrN){
        this(vrN,true);
    }
    
    String description(){
        String loyaute=(loyal)? "loyal" : "déloyal";
        String res="espion "+this.id+" ("+loyaute+")";
        return res;
    }
    
    //4.
    String getVraiNom(){
        return this.vraiNom;
    }
    
    boolean getLoyal(){
        return this.loyal;
    }
    
    int getId(){
        return this.id;
    }
    
    //8.
    void setLoyal(boolean b) {
    	this.loyal=b;
    }
}