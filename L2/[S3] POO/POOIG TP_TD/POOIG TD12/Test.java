public class Test {
    public static void main(String[]args){
        Pseudo a=new Pseudo("a");
        Pseudo b=new Pseudo("b");
        Pseudo c=new Pseudo("c");
        Pseudo d=new Pseudo("d");
        
        ChangePseudoHistory h_ab=new ChangePseudoHistory();
        ChangePseudoHistory h_bc=new ChangePseudoHistory();
        
        ChangePseudoHistory[]aTab=new ChangePseudoHistory[1];
        ChangePseudoHistory[]bTab=new ChangePseudoHistory[2];
        ChangePseudoHistory[]cTab=new ChangePseudoHistory[1];
        aTab[0]=h_ab;
        bTab[0]=h_ab;
        bTab[1]=h_bc;
        cTab[0]=h_bc;        
        
        a.addObservateurs(aTab);
        b.addObservateurs(bTab);
        c.addObservateurs(cTab);
        
        a.set("a1");
        b.set("b1");
        a.set("a2");
        c.set("c1");
        d.set("d1");
        
        System.out.println(h_ab);
        System.out.println(h_bc);
    }
}