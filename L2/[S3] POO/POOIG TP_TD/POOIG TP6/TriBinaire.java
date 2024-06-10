//9.
public class TriBinaire implements Triable{
    String[]t;
    
    TriBinaire(String[]t){  //on va supposer que t contiendra bien que des bits
        this.t=t;
    }
    
    @Override
    public void echange(int i, int j){
        String temp=t[i];
        t[i]=t[j];
        t[j]=temp;
    }
    
    @Override
    public boolean plusGrand(int i, int j){  //par défaut, false si t[i]==t[j]
        return binaireVersInt(t[i])>binaireVersInt(t[j]);
    }
    
    static int binaireVersInt(String s){  //s supposer être une chaîne de bits
        int res=0;
        int puissance=1;
        for(int i=s.length()-1; i>=0; i--){
            switch (s.charAt(i)){
                case '0':
                    puissance*=2;
                    break;
                case '1':
                    res+=puissance;
                    puissance*=2;
                    break;
            }
        }
        return res;
    }
    
    @Override
    public int taille(){
        return this.t.length;
    }
    
    public String toString(){
        String res="{";
        for(int i=0; i<this.taille()-1; i++){
            res+=this.t[i]+", ";
        }
        res+=this.t[this.taille()-1]+"}";
        return res;
    }
}