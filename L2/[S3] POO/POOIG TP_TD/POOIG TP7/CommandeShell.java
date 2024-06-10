import java.util.Scanner;
import java.util.LinkedList;

public abstract class CommandeShell {
    //3.2
    protected Dossier racine, courant;
    protected String[]parametres;
    
    CommandeShell(Dossier r, Dossier c, String[]p){
        racine=r;
        courant=c;
        parametres=p;
    }
    
    public abstract Dossier executer();
    
    public static void aide(){
        return;
    }
    
    protected static void erreurParam(){
        System.out.println("Pas un bon nombre de paramètres.");
        aide();
    }
    
    protected Entree acceder(String chemin, boolean creer){
        Scanner sc=new Scanner(chemin).useDelimiter("/");
        Dossier act=(chemin.charAt(0)=='/')?this.racine:this.courant;  //dossier actuel
        String pos=(sc.hasNext())?sc.next():"";  //position dans le chemin
                                                 //ici on prend le premier terme
        while(sc.hasNext()){  //si on n'est pas à la fin du chemin
            for(Entree e : act.getEntrees()){
                if(e.getElement() instanceof Dossier && e.getNom().equals(pos)){
                    act=(Dossier)e.getElement();
                    pos=sc.next();
                }
            }
        }
        return act.getEntree(pos, creer);
    }
}
