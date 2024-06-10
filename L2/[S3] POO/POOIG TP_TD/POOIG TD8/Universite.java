import java.util.Random;

//3.
public class Universite {
    private int nbEtu, capALL, capSHS, capSTS;
    //Art Lettres Langues ; Sc Humaines et Soc ; Sc Techno et Santé
    
    //3.1
    public Universite(int nbEtu, int capALL, int capSHS, int capSTS){
        if(nbEtu<0 || capALL<0 || capSHS<0 || capSTS<0 || nbEtu>capALL+capSHS+capSTS)
            throw new IllegalArgumentException("Mauvais arguments.");
        this.nbEtu=nbEtu;
        this.capALL=capALL;
        this.capSHS=capSHS;
        this.capSTS=capSTS;
    }
    
    //3.2
    /*public void restructuration(int capALL, int capSHS, int capSTS){
        if(capALL<0 || capSHS<0 || capSTS<0)
            throw new IllegalArgumentException("Mauvais arguments.");
        if(nbEtu>capALL+capSHS+capSTS)
            throw new TropDEtudiantException("Trop d'étudiants.");
        this.capALL=capALL;
        this.capSHS=capSHS;
        this.capSTS=capSTS;
    }*/
    
    public class TropDEtudiantException extends RuntimeException{
        int surplus;
        
        public TropDEtudiantException(String s){
            super(s);
        }
        
        public TropDEtudiantException(int surplus){
            this.surplus=surplus;
        }
    }
    
    //3.3
    public class DirectiveMinException extends Exception{
        public DirectiveMinException(String s){
            super(s);
        }
    }
    
    public void restructuration (int capALL, int capSHS, int capSTS)
    throws DirectiveMinException{
        if(capALL<0 || capSHS<0 || capSTS<0)
            throw new IllegalArgumentException("Mauvais arguments.");
        if(nbEtu>capALL+capSHS+capSTS)
            throw new TropDEtudiantException(this.nbEtu-(capSHS+capALL+capSTS));
        if(capALL > capSHS+capSTS){
            throw new DirectiveMinException("Trop d'ALL.");
        }
        if(capSHS > capALL+capSTS){
            throw new DirectiveMinException("Trop d'SHS.");
        }
        if(capSTS > capSHS+capALL){
            throw new DirectiveMinException("Trop d'STS.");
        }
        this.capALL=capALL;
        this.capSHS=capSHS;
        this.capSTS=capSTS;
    }
    
    //3.4
    public void restrictionBudgetaire() throws DirectiveMinException{
        Random sourceAlea=new Random();
        int nCapALL=sourceAlea.nextInt((int)(0.9*capALL));
        int nCapSHS=sourceAlea.nextInt((int)(0.9*capSHS));
        int nCapSTS=sourceAlea.nextInt((int)(0.9*capSTS));
        try{
            this.restructuration(nCapALL, nCapSHS, nCapSTS);
        }
        catch(TropDEtudiantException e){
            this.reduction(10);
        }
        catch(DirectiveMinException e){
            nCapALL=sourceAlea.nextInt((int)(0.9*capALL));
            nCapSHS=sourceAlea.nextInt((int)(0.9*capSHS));
            nCapSTS=sourceAlea.nextInt((int)(0.9*capSTS));
        }
        finally{
            this.restructuration(nCapALL, nCapSHS, nCapSTS);
        }
    }
    
    public void reduction(int nb){
        nbEtu-=nb;
    }
}