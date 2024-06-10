import java.util.*;

class EFTraduit{
    static Stack<Bloc> p=new Stack<Bloc>();
    static int ic=0;
    public static void main(String[] args){
        while(true){
            //System.out.println(ic+" : "+p);
            switch(ic++){
                case 0: p.push(new BlocF(ic,5)); ic=100; break;
                case 1: System.out.println("resultat : "+((BlocR)(p.pop())).getVal());break;
                case 2: p.push(new BlocE(ic,4,3)); ic=500; break;
                case 3: System.out.println("resultat : "+((BlocR)(p.pop())).getVal());break;
                case 4: System.exit(0);
                    
                case 100: ((BlocF)p.peek()).setVar1(1); break;
                case 101: ((BlocF)p.peek()).setVar2(1); break;
                case 102: if(((BlocF)p.peek()).getVar1()
                             >((BlocF)p.peek()).getArg1()) ic+=3; break;
                case 103: ((BlocF)p.peek()).setVar2(((BlocF)p.peek()).getVar2()
                                                  *(((BlocF)p.peek()).getVar1())); break;
                case 104: ((BlocF)p.peek()).setVar1(((BlocF)p.peek()).getVar1()+1); break;
                case 105: ic-=4; break;
                case 106: ((BlocR)p.peek()).setVal(((BlocF)p.peek()).getVar2()); break;
                case 107: ic=p.peek().getAdr(); break;
                
                case 500: //à terminer
            }
        }
    }
}

class Bloc{
    //bloc générique
    private int adresseDeRetour;
    //constructeurs
    public Bloc(int n){
        this.adresseDeRetour=n;
    }
    //accesseurs
    public int getAdr(){
        return this.adresseDeRetour;
    }
}

class BlocR extends Bloc{
    //bloc pour fonction avec valeur de retour
    private int valeurDeRetour;
    //constructeurs
    public BlocR(int n){
        super(n);//appel au constructeur du type "super"ieur
    }
    //accesseurs
    public int getVal(){
        return this.valeurDeRetour;
    }
    //mutateurs
    public void setVal(int n){
        this.valeurDeRetour=n;
    }
    //affichage
    public String toString(){
        return "{"+getAdr()+","+valeurDeRetour+"}";//...
    }
}

class BlocE extends BlocR{
    //bloc spécifique à la fonction e
    private int argument1,argument2;
    private int variable1;
    //constructeurs
    public BlocE(int n, int a, int b){
        super(n);
        this.argument1=a;
        this.argument2=b;
    }
    //accesseurs
    public int getArg1(){
        return this.argument1;
    }
    public int getArg2(){
        return this.argument2;
    }
    public int getVar1(){
        return this.variable1;
    }
    //mutateurs
    public void setArg1(int n){
        this.argument1=n;
    }
    public void setArg2(int n){
        this.argument2=n;
    }
    public void setVar1(int n){
        this.variable1=n;
    }
    //affichage
    public String toString(){
        return "E{"+getAdr()+","+getVal()+","+argument1+","+argument2+","+variable1+"}";//...
    }
}

class BlocF extends BlocR{
    //bloc spécifique à la fonction f
    private int argument1;
    private int variable1,variable2;
    //constructeurs
    public BlocF(int n, int a){
        super(n);
        this.argument1=a;
    }
    //accesseurs
    public int getArg1(){
        return this.argument1;
    }
    public int getVar1(){
        return this.variable1;
    }
    public int getVar2(){
        return this.variable2;
    }
    //mutateurs
    public void setArg1(int n){
        this.argument1=n;
    }
    public void setVar1(int n){
        this.variable1=n;
    }
    public void setVar2(int n){
        this.variable2=n;
    }
    //affichage
    public String toString(){
        return "F{"+getAdr()+","+getVal()+","+argument1+","+variable1+","+variable2+"}";//...
    }
}














