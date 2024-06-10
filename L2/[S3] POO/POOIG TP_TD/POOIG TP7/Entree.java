public class Entree {
    //2.
    private Element element;
    private String nom;
    private Dossier contenant;
    
    public Entree(Dossier p, String n, Element e){
        contenant=p;
        nom=n;
        element=e;
    }
    
    //2.2
    public String toString(){
        String type="";
        if(this.element==null) type="entrée vide";
        else type=this.element.getType();        
        return this.nom+" ("+type+")";
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public Element getElement(){
        return this.element;
    }
    
    //2.3
    public void supprimer(){
        this.contenant.supprimer(this);
    }
    
    public void remplacer(Element e){
        element=e;
        if(e instanceof Dossier) ((Dossier)e).setParent(contenant);
    }
    
    //3.3.f
    public Dossier getContenant(){
        return this.contenant;
    }
}