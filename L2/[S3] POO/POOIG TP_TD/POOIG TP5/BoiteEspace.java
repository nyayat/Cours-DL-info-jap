//public class BoiteEspace extends Boite {  //2.
public class BoiteEspace extends BoiteEtirable {  //8.

    //2.
    public int length(){
        return 1;
    }
    
    public String toString(){
        return " ";
    }
    
    //8.
    boolean isEtirable(){
        return true;
    }
    
    public String toString(int n){
        String res="";
        for(int i=0; i<n+1; i++){
            res+=" ";
        }
        return res;
    }
}