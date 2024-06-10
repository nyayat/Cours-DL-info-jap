public class BandeDessinee extends Livre {
    //2.3
    final protected String illustrateur;
    final protected int volumeNum;
    
    BandeDessinee(String titre, String auteur, int pages, String illustrateur, int volumeNum){
        super(titre, auteur, pages);
        this.illustrateur=illustrateur;
        this.volumeNum=volumeNum;
    }
}