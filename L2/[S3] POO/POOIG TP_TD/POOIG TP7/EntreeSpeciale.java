public class EntreeSpeciale extends Entree{
    //2.5
    public EntreeSpeciale(Dossier p, String n, Element e){
        super(p, n, e);
    }
    
    public void supprimer(){
        System.out.println("Impossible de supprimer.");
    }
    
    public void remplacer(Element e){
        System.out.println("Impossible de remplacer.");
    }
}