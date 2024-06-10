public class CommandeLs extends CommandeShell{
    //3.3.c
    CommandeLs(Dossier r, Dossier c, String[]p){
        super(r, c, p);
    }
    
    @Override
    public Dossier executer(){
        if(this.parametres.length>1){
            CommandeShell.erreurParam();
            return null;
        }
        if(this.parametres.length==0) this.courant.afficher();
        else{
            Entree temp=this.acceder(this.parametres[0], false);
            if(temp.getElement() instanceof FichierTexte) System.out.println(temp);
            else if(temp.getElement() instanceof Dossier)
                ((Dossier)temp.getElement()).afficher();
        }
        return null;
    }
    
    public static void aide(){
        System.out.println("ls [<name>]");
    }
}