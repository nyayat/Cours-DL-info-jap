import java.util.*;

class FactoFacticeTraduit{
    public static void main(String[] args){
        int[] mem=new int[100];
        int ic=0;
        Stack<Integer> p=new Stack<Integer>();
        while(true){
            switch(ic++){
                case 0: mem[0]=4; break;
                case 1: p.push(ic); ic=100; break;      //appel
                case 2: System.out.println("resultat : "+mem[2]); break;
                case 3: System.exit(0);
                    
                case 100: mem[1]=1; break;
                case 101: mem[2]=1; break;
                case 102: if(mem[1]>mem[0]) ic+=3; break;
                case 103: mem[2]*=mem[1]; break;
                case 104: mem[1]++; break;
                case 105: ic-=4; break;
                case 106: ic=p.pop(); break;            //retour
            }
        }
    }
}
