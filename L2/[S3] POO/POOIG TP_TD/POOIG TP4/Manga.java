public class Manga extends Livre {
    //2.3
    final protected String illustrateur;
    final protected String traducteur;
    final protected int volumeNum;
    
    Manga(String titre, String aut, int pages, String illus, String trad, int volumeNum){
        super(titre, aut, pages);
        this.illustrateur=illus;
        this.traducteur=trad;
        this.volumeNum=volumeNum;
    }
}