public final class Licence extends Diplome{
    public Licence(String intitule, Mention mention, int annee){
        super(intitule, mention, annee);
    }
    
    public String toString(){
        return "Licence : "+this.intitule
                +", mention "+this.mention
                +", "+this.annee+"\n";
    }
}
