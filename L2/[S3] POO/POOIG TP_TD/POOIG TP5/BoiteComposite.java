import java.util.LinkedList;

//public class BoiteComposite extends Boite {  //3.
public class BoiteComposite extends BoiteEtirable {  //9.
    //3.
    LinkedList<Boite> composite=new LinkedList<Boite>();
    
    public int length(){
        int res=0;
        for(Boite b : this.composite){
            res+=b.length();
        }
        return res;
    }
    
    public String toString(){
        String res="";
        for(Boite b : this.composite){
            res+=b.toString();
        }
        return res;
    }
    
    public boolean isEmpty(){
        return this.composite.isEmpty();
    }
    
    public void addBoite(Boite b){
        this.composite.add(b);
    }
    
    public void deleteLastSpace(){
        if(!this.composite.isEmpty()
          && this.composite.getLast().getClass()==BoiteEspace.class)
            this.composite.removeLast();
    }
    
    //9.
    boolean isEtirable(){
        for(Boite b : this.composite){
            if(b.getClass()==BoiteEspace.class) return true;
        }
        return false;
    }
    
    public String toString(int n){  //on suppose que n est la taille de la ligne
        int etirable=0;  //nombre de BoiteEspace
        String res="";
        for(Boite b : this.composite){
            if(b.isEtirable()) etirable++;
            n-=b.length();
        }
        int reste=0;  //nombre d'espaces restant à répartir
        if(etirable!=0) reste=n%etirable;
        for(Boite b : this.composite){
            if(b.isEtirable()){
                res+=((BoiteEspace)b).toString(n/etirable);
                if(reste>0){
                    res+=((BoiteEspace)b).toString(0);
                                                //équivalent à ajouter un espace
                    reste--;
                }
            }
            else res+=b.toString();
        }
        return res;
    }
}