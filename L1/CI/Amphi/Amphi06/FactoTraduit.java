import java.util.*;

class FactoTraduit{
    public static void main(String[] args){
        int[] mem=new int[1000];
        int ic=0;
        Stack<BlocF> p=new Stack<BlocF>();  //pile d'appel
        while(true){
            switch(ic++){
                case 0: mem[0]=4;
                case 1: p.push(new BlocF(ic,mem[0])); ic=500; break;
                case 2: System.out.println("resultat : "+p.pop().getVal()); break;
                    //on dépile le bloc en récupérant la valeur de retour
                case 3: System.exit(0);
                    
                case 500: p.peek().setVar1(1); break;
                case 501: p.peek().setVar2(1); break;
                case 502: if(p.peek().getVar1()>p.peek().getArg()) ic+=3; break;
                case 503: p.peek().setVar2(p.peek().getVar2()*p.peek().getVar1()); break;
                case 504: p.peek().setVar1(p.peek().getVar1()+1); break;
                case 505: ic-=4; break;
                case 506: p.peek().setVal(p.peek().getVar2()); break;
                case 507: ic=p.peek().getAdr(); break;
                    //on utilise l'adresse de retour en maintenant le bloc sur la pile
            }
        }
    }
}

class BlocF{
    /* bloc spécifique à la fonction f */
    
    // attributs (environnement de f)
    private final int adresse_retour;
    private int valeur_retour;
    private int argument;
    private int variable1, variable2;
    
    // constructeurs
    public BlocF(int adr, int arg){
        /* l'appelant doit spécifier
         * * l'adresse de retour
         * * les valeurs des arguments
         */
        this.adresse_retour=adr;
        this.argument=arg;
    }
    
    // accesseur utilisé par le code appelant
    public int getVal(){
        return this.valeur_retour;
    }
    
    // accesseurs et mutateurs utilisés par le code appelé
    public int getVar1(){
        return this.variable1;
    }
    public int getVar2(){
        return this.variable2;
    }
    public int getArg(){
        return this.argument;
    }
    public int getAdr(){
        return this.adresse_retour;
    }
    public void setVar1(int v){
        this.variable1=v;
    }
    public void setVar2(int v){
        this.variable2=v;
    }
    public void setVal(int v){
        this.valeur_retour=v;
    }
}
