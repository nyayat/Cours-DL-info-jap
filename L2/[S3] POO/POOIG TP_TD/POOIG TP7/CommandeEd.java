import java.util.Scanner;

public class CommandeEd extends CommandeShell{
    //3.3.e
    boolean echo;
    Scanner sc;
    
    CommandeEd(Dossier r, Dossier c, String[]p, Scanner s, boolean e){
        super(r, c, p);
        sc=s;
        echo=e;
    }
    
    @Override
    public Dossier executer(){
        if(this.parametres.length!=1){
            CommandeShell.erreurParam();
            return null;
        }
        if(this.parametres[0].equals(".") || this.parametres[0].equals("..")){
            System.out.println("Impossible de nommer ainsi un fichier texte.");
            return null;
        }
        Entree e=this.acceder(this.parametres[0], true);
        FichierTexte nouv=new FichierTexte();
        nouv.editer(this.sc, this.echo);
        e.remplacer(nouv);
        return null;
    }
    
    public static void aide(){
        System.out.println("ed <filename>");
    }
}