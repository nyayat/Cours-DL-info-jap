public class EnsembleCartes extends TabSet<Carte>{
    //14.
    int score(){
        int res=0;
        for(Carte c : this){
            res+=(int)c.scores.get(c);
        }
        return res;
    }
}