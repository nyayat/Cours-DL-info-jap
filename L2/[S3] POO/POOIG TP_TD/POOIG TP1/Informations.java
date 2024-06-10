public class Informations {
    //5.1
    private String nom;
    private int vi,fi,ai;
    private int va,fa,aa;

    Informations(int v,int f,int a){
        this.nom="";
        this.va=v;
        this.fa=f;
        this.aa=a;
        this.vi=v;
        this.fi=f;
        this.ai=a;
    }

    Informations(Informations inf){
        this.nom=inf.nom;
        this.va=inf.va;
        this.fa=inf.fa;
        this.aa=inf.aa;
        this.vi=inf.vi;
        this.fi=inf.fi;
        this.ai=inf.ai;
    }
    
    //pas demandé mais pratique
    Informations(String s, int v, int f, int a){
        this(v,f,a);
        this.nom=s;
    }

    int getVitalite() {
        return this.va;
    }

    int getForce() {
        return this.fa;
    }

    int getAgilite() {
        return this.aa;
    }

    void setAgilite(int a) {
        this.aa=a;
    }

    void setForce(int f) {
        this.fa=f;
    }

    void setVitalite(int v) {
        this.va=v;
    }

    public String toString() {
        return getClass().getName()+String.format(": Nom=%s Vitalite=%d Force=%d Agilite=%d",nom,va,fa,aa);
    }

    //5.2
    void rebirth() {
        this.setAgilite(this.ai);
        this.setForce(this.fi);
        this.setVitalite(this.vi);
    }
    
    //5.4
    String getNom(){
        return this.nom;
    }
}