public class CommandeCat extends CommandeShell{
    //3.3.b
    CommandeCat(Dossier r, Dossier c, String[]p){
        super(r, c, p);
    }
    
    @Override
    public Dossier executer(){
        if(this.parametres.length!=1){
            CommandeShell.erreurParam();
            return null;
        }
        Entree e=this.acceder(this.parametres[0], false);
        if(e.getElement() instanceof FichierTexte)
            ((FichierTexte)e.getElement()).afficher();
        else if(e.getElement() instanceof Dossier) ((Dossier)e.getElement()).afficher();
        return null;
    }
    
    public static void aide(){
        System.out.println("cat <name>");
    }
}