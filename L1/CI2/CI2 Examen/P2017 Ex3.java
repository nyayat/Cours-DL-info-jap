import java.util.*;
public class P2017{
    /*public static void main(String[]args){
        int ic=0;
        int[]mem=new int[5];
        Stack<Integer>p=new Stack<Integer>();
        while(true){
            switch(ic++){
		case 0 : mem[0]=5; break;
                case 1 : mem[1]=3; break;
                case 2 : p.push(ic); ic=10; break;
                case 3 : p.pop(); break;
                case 4 : System.out.println("a= "+mem[0]+" b= "+mem[1]); break;
                case 5 : System.exit(0);
                
                //f():
                case 10 : p.push(ic); ic=20; break;
                case 11 : p.pop(); break;
		case 12 : if(7<2*mem[0] && mem[1]<11){ ic=10; } break;
                case 13 : System.out.println("a= "+mem[0]+" b= "+mem[1]); break;
                case 14 : ic=p.peek(); break;
                
                //g():
                case 20 : if(!(mem[0]+mem[1]<51)){ic++;} break;
                case 21 : mem[0]+=mem[1]; ic++; break;
                case 22 : mem[1]=-mem[1]; break;
                case 23 : ic=p.peek(); break;
            }
        }
    }*/
	
    public static int a = 5;
    public static int b = 3;

    public static void f(int x, int y){
            do g(a+b);
            while (x < 2*a && b < y);
    }

    public static void g(int z){
            if(z < 51) a += b;
            else b = -b;
    }

    public static void main ( String [] args ){
            f (7 ,11);
            System .out. println ("a="+a+" b="+b);
    }
}