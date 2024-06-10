import java.util.*;

class AppelFonction1Traduit{
    public static void main(String[] args){
        int[] mem=new int[100];                 //pas utilisée ici
        int ic=0;
        Stack<Integer> p=new Stack<Integer>();  //pile d'appel
        while(true){
            System.out.println("ic #"+ic+"\tp="+p);
            switch(ic++){
                case 0: p.push(ic); ic=5000; break; //appel : sauvegarde (sur la pile) de @ retour et saut
                case 1: System.exit(0);
                    
                case 5000: System.out.println("Makeba, ma che bella, ooh wee"); break;
                case 5001: ic=p.pop(); break;       //retour
            }
        }
    }
}
