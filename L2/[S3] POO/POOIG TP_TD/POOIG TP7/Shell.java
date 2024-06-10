import java.io.InputStream;
import java.util.Scanner;

public class Shell {
    //3.1
    private Dossier racine;
    private Dossier courant;
    
    public Shell(Dossier d){
        racine=d;
        courant=d;
    }
    
    //3.4
    public static void affichageHelp(){
        CommandeCat.aide();
        CommandeCd.aide();
        CommandeEd.aide();
        CommandeLs.aide();
        CommandeMkdir.aide();
        CommandeMv.aide();
        CommandeRm.aide();
        System.out.println("quit : termine le programme");
        System.out.println("help : affiche tous les manuels des commandes");
    }
    
    public void interagir(InputStream in){
        Scanner scLine=new Scanner(in).useDelimiter("\n");
        while(scLine.hasNext()){
            Scanner scWord=new Scanner(scLine.next());
            CommandeShell commande;  //la commande à exécuter
            String commandeNom=scWord.next();  //nom de la commande
            
            String[]commandeParam=new String[0];  //tableau de paramètres
            while(scWord.hasNext()){  //on renvoie un nouveau tableau plus grand
                String[]commandeParamTemp=new String[commandeParam.length+1];
                for(int i=0; i<commandeParam.length; i++){
                    commandeParamTemp[i]=commandeParam[i];
                }
                commandeParamTemp[commandeParamTemp.length-1]=scWord.next();
                commandeParam=commandeParamTemp;
            }
            
            switch(commandeNom){
                case "cat":
                    commande=new CommandeCat(this.racine, this.courant, commandeParam);
                    commande.executer();
                    break;
                    
                case "cd":
                    commande=new CommandeCd(this.racine, this.courant, commandeParam);
                    this.courant=commande.executer();
                    break;
                    
                case "ed":
                    commande=new CommandeEd(this.racine, this.courant, commandeParam,
                        scLine, in!=System.in);
                    commande.executer();
                    break;
                    
                case "ls":
                    commande=new CommandeLs(this.racine, this.courant, commandeParam);
                    commande.executer();
                    break;
                    
                case "mkdir":
                    commande=new CommandeMkdir(this.racine, this.courant, commandeParam);
                    commande.executer();
                    break;
                    
                case "mv":
                    commande=new CommandeMv(this.racine, this.courant, commandeParam);
                    commande.executer();
                    break;
                    
                case "rm":
                    commande=new CommandeRm(this.racine, this.courant, commandeParam);
                    commande.executer();
                    break;
                    
                case "quit":
                    return;
                    
                case "help":
                    affichageHelp();
                    break;
                
                default:
                    System.out.println("La commande n'existe pas.");
                    break;
            }
        }
    }
}