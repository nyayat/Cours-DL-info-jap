public class CommandeMkdir extends CommandeShell{
    //3.3.a
    CommandeMkdir(Dossier r, Dossier c, String[]p){
        super(r, c, p);
    }
    
    @Override
    public Dossier executer(){
        if(this.parametres.length!=1){
            CommandeShell.erreurParam();
            return null;
        }
        if(this.parametres[0].equals(".") || this.parametres[0].equals("..")){
            EntreeSpeciale eS=new EntreeSpeciale(null, null, null);
            eS.remplacer(null);  //juste pour le message d'erreur
            return null;
        }
        Entree e=this.acceder(this.parametres[0], true);
        e.remplacer(new Dossier(courant));
        return null;
    }
    
    public static void aide(){
        System.out.println("mkdir <foldername>");
    }
    
}