public final class Bac extends Diplome{
    public Bac(String intitule, Mention mention, int annee){
        super(intitule, mention, annee);
    }
    
    public String toString(){
        return "Bac : "+this.intitule
                +", mention "+this.mention
                +", "+this.annee+"\n";
    }
}
