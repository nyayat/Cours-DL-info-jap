public class DictionnaireBilingue extends Dictionnaire {
    //2.3
    final private String langue2;
    
    DictionnaireBilingue(String titre, String langue, String langue2, int volumeNum){
        super(titre, langue, volumeNum);
        this.langue2=langue2;
    }
    
    public String toString(){
        String res=super.toString();
        res=res.substring(0, res.length()-1)+"/"+this.langue2+".";
        return res;
    }
}