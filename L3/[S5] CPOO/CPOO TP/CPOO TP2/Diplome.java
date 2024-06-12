public abstract class Diplome{
    public final String intitule;
    public final Mention mention;
    public final int annee;
    
    public Diplome(String intitule, Mention mention, int annee){
        this.intitule=intitule;
        this.mention=mention;
        this.annee=annee;
    }
}