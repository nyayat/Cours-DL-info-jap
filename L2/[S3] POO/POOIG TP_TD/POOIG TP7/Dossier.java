import java.util.LinkedList;

public class Dossier extends Element implements Affichable{
    //2.1
    private LinkedList<Entree> entrees;
    private Dossier parent;
    
    Dossier(Dossier p){
        parent=p;
        entrees=new LinkedList<Entree>();
        entrees.addFirst(new EntreeSpeciale(this, ".", this));
        if(p!=null) entrees.add(new EntreeSpeciale(p, "..", this.parent));
    }
    
    public String getType(){
        return "dossier";
    }
    
    //2.3
    public void supprimer(Entree e){
        this.entrees.remove(e);
    }
    
    public void setParent(Dossier d){
        parent=d;
    }
    
    //2.4
    /*public Entree getEntree(String nom){
        for(Entree e : this.entrees){
            if(e.getNom().equals(nom)) return e;
        }
        return null;
    }*/
    
    //2.5
    public Dossier getParent(){
        return this.parent;
    }
    
    //2.6
    public void afficher(){
        for(Entree e : this.entrees){
            System.out.println(e);
        }
    }
    
    //2.7
    public Entree getEntree(String nom, boolean creer){
        for(Entree e : this.entrees){
        //si l'entrée existe, on la renvoie dans tous les cas
            if(e.getNom().equals(nom)) return e;
        }
        if(creer){
            Entree e=new Entree(this, nom, null);
            this.entrees.addLast(e);
            return e;
        }
        System.out.println("Erreur, l'entrée n'existe pas.");  //pour 3.2
        return null;
    }
    
    //3.2
    public LinkedList<Entree> getEntrees(){
        return this.entrees;
    }
    
    //3.3.f
    public boolean estEnfantDe(Dossier o){
        Dossier temp=this.parent;
        while(temp!=null){
            if(temp.equals(o)) return true;
            temp=temp.parent;
        }
        return false;
    }
}