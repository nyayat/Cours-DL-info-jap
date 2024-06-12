public final class Master extends Diplome{
    public Master(String intitule, Mention mention, int annee){
        super(intitule, mention, annee);
    }
    
    public String toString(){
        return "Master : "+this.intitule
                +", mention "+this.mention
                +", "+this.annee+"\n";
    }
}
