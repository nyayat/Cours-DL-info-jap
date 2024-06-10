public class CommandeRm extends CommandeShell{
    //3.3.g
    CommandeRm(Dossier r, Dossier c, String[]p){
        super(r, c, p);
    }
    
    @Override
    public Dossier executer(){
        if(this.parametres.length!=1){
            CommandeShell.erreurParam();
            return null;
        }
        Entree e=this.acceder(this.parametres[0], false);
        e.supprimer();
        return null;
            
    }
    
    public static void aide(){
        System.out.println("rm <name>");
    }
}