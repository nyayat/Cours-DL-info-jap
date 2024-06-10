import java.util.*;

class AppelFonction3Traduit{
    public static void main(String[] args){
        int[] mem=new int[100];
        int ic=0;
        Stack<Integer> p=new Stack<Integer>();          //pile d'appel
        while(true){
            System.out.println("ic #"+ic+"\tp="+p);
            switch(ic++){
                case 0: mem[0]=0; break;
                case 1: if(mem[0]>=4) ic+=3; break;     //saut conditionnel vers ic=5
                case 2: p.push(ic); ic=15000; break;    //appel
                case 3: mem[0]++; break;
                case 4: ic-=4; break;                   //saut inconditionnel vers ic=1
                case 5: System.exit(0);
                    
                case 5000: System.out.println("Makeba, ma che bella, ooh wee"); break;
                case 5001: ic=p.pop(); break;           //retour
                    
                case 15000: p.push(ic); ic=5000; break; //appel
                case 15001: System.out.println("can I get a oooh wee"); break;
                case 15002: System.out.println("Makeba, makes my body dance for you"); break;
                case 15003: ic=p.pop(); break;          //retour

            }
        }
    }
}
