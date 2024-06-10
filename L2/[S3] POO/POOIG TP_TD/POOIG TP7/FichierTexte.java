import java.util.Scanner;

public class FichierTexte extends Element implements Affichable{
    //1.1
    private String contenu;
    
    public String getType(){
        return "texte";
    }
    
    //1.3
    public void afficher(){
        System.out.println(this.contenu);
    }
    
    //1.4
    public void editer(Scanner sc, boolean echo){
        String res="";
        String line=sc.nextLine();
        while(!(line.equals("."))){
            if(echo) System.out.println(line);
            if(!(res.equals(""))) res+="\n";
            res+=line;
            line=sc.nextLine();
        }
        this.contenu=res;
    }
}