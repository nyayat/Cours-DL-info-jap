import java.util.*;

class AppelFonction1Trad{
    public static void main(String[] args){
        int ic=0;               //compteur inst
        int[] mem=new int[100000];    //pas utilisée
        Stack<Integer> p=new Stack<Integer>();
        while(true)
            switch(ic++){
                case 0: p.push(ic); ic=500; break;
                    //@retour=1 sur la pile + saut inconditionnel vers 500
                case 1: p.pop(); break;
                    //c'est l'appelant (qui a empilé) qui dépile
                case 2: System.exit(0);
                    
                //...
                
                case 500: System.out.println("Makeba, ma che bella, ooh wee"); break;
                case 501: ic=p.peek(); break;
            }
    }
}
