public class HorlogeDigitale {
    //6.
    public static class Digit{  //classe interne
        int valC;
        final int ValMaxC;
        
        Digit(int c, int max){
            this.valC=c;
            this.ValMaxC=max;
        }
        
        Digit(int max){
            this.ValMaxC=max;
        }
        
        int getValeur(){
            return this.valC;
        }
        
        int estALaValeurMax(){
            return this.ValMaxC;
        }
        
        public String toString(){
            return String.valueOf(valC);
        }
        
        void incremente(){
            this.valC=(this.valC==this.ValMaxC-1)?0:this.valC+1;
        }
    }  //fin classe interne
    
    //7.
    Digit[]t=new Digit[6];
    
    HorlogeDigitale(){
        for(int i=2; i<6; i+=2){
            t[i]=new Digit(0,6);
            t[i+1]=new Digit(0,10);
        }
        t[0]=new Digit(0,3);
        t[1]=new Digit(0,10);
    }
    
    public String toString(){
        String res="";
        for(int i=0; i<6; i++){
            res+=t[i].toString();
            if(i==1 || i==3) res+=":";
        }
        return res;
    }
    
    void incremente(){
        boolean continuer=true;  //continuer d'incrémenter ?
        int i=5;
        while(continuer && i>-1){
            t[i].incremente();
            if(t[i].valC!=0) continuer=false;
            else i--;
        }
        if(t[0].valC==2 && t[1].valC==4){
            t[0].valC=0;
            t[1].valC=0;
        }
    }
}