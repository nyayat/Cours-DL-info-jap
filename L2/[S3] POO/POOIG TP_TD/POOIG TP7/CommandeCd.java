public class CommandeCd extends CommandeShell{
    //3.3.d
    CommandeCd(Dossier r, Dossier c, String[]p){
        super(r, c, p);
    }
    
    @Override
    public Dossier executer(){
        if(this.parametres.length>1){
            CommandeShell.erreurParam();
            return null;
        }
        Dossier res=(this.parametres.length==0)?
            this.racine:(Dossier)this.acceder(this.parametres[0], false).getElement();
        return res;
    }
    
    public static void aide(){
        System.out.println("cd [<foldername>]");
    }
}