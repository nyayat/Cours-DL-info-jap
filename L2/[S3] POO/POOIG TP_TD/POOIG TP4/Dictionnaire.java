public class Dictionnaire extends Media {
    //2.1
    final protected String langue;
    protected int volumeNum;
    
    Dictionnaire(String titre, String langue, int volumeNum){
        super(titre);
        this.langue=langue;
        this.volumeNum=volumeNum;
    }
    
    //2.2
    public String toString(){
        String res=super.toString()+"C'est le tome "+this.volumeNum
                +" d'un dictionnaire "+this.langue+".";
        return res;
    }
}