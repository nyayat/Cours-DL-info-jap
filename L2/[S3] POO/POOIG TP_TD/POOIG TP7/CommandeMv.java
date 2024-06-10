public class CommandeMv extends CommandeShell{
    //3.3.f
    CommandeMv(Dossier r, Dossier c, String[]p){
        super(r, c, p);
    }
    
    @Override
    public Dossier executer(){
        if(this.parametres.length!=2){
            CommandeShell.erreurParam();
            return null;
        }
        Entree src=this.acceder(this.parametres[0], false);
        Entree dest=this.acceder(this.parametres[1], true);
        if(dest.getElement()==null) dest.remplacer(new Dossier(dest.getContenant()));
                                        //on crée le dossier s'il n'existe pas
        if(dest.getElement() instanceof Dossier){
            if(src.getElement() instanceof Dossier){
                if(((Dossier)dest.getElement()).estEnfantDe(((Dossier)src.getElement()))){
                    System.out.println("Pas possible de déplacer un dossier dans lui-même.");
                    return null;
                }
                ((Dossier)src.getElement()).setParent(src.getContenant());
            }
            ((Dossier)dest.getElement()).getEntrees().add(src);
                                        //on admet plusieurs entrées de même nom
            src.getContenant().supprimer(src);
        }
        return null;
    }
    
    public static void aide(){
        System.out.println("mv <src> <dst>");
    }
}