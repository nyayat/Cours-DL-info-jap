package com.mycompany.ci;
import java.util.*;
public class Test {
    /*public static void main(String[]args){
        int ic=0;
        int[]mem=new int[100];
        while(true){
            switch(ic++){
                case 0 : System.out.println("Entrée main"); break;
                case 1 : System.out.println("Milieu f"); break;
                case 2 : System.out.println("Entrée h"); break;
                case 3 : System.out.println("Entrée g"); break;
                case 4 : System.out.println("Milieu f"); break;
                case 5 : System.out.println("Sortie g"); break;
                case 6 : System.out.println("Sortie h"); break;
                case 7 : System.out.println("Sortie main"); break;
                case 8 : System.exit(0);
            }
        }
    }*/
    
    /*static void h(){
        System.out.println("Entrée h");
        g();
        System.out.println("Sortie h");
    }

    static void g(){
        System.out.println("Entrée g");
        f();
        System.out.println("Sortie g");
    }

    static void f(){
        System.out.println("Milieu f");
    }

    public static void main(String[]args){
        System.out.println("Entrée main");
        f();
        h();
        System.out.println("Sortie main ");
    }*/
    
    public static void main(String[]args){
        int ic=0;
        //int[]mem=new int[1000];
        Stack<Integer>p=new Stack<Integer>();
        while(true){
            switch(ic++){
                case 0 : System.out.println("Entrée main"); break;
                
                case 1 : p.push(ic); ic=10; break;  //appel à f()
                case 2 : p.pop(); break;
                
                case 3 : p.push(ic); ic=20; break;  //appel à h()
                case 4 : p.pop(); break;
                
                case 5 : System.out.println("Sortie main"); break;
                case 6 : System.exit(0);
                
                //f():
                case 10 : System.out.println("Milieu f"); break;
                case 11 : ic=p.peek(); break;
                
                //h():
                case 20 : System.out.println("Entrée h"); break;
                case 21 : p.push(ic); ic=30; break;  //appel à g
                case 22 : p.pop(); break;
                case 23 : System.out.println("Sortie h"); break;
                case 24 : ic=p.peek(); break;
                
                //g():
                case 30 : System.out.println("Entrée g"); break;
                case 31 : p.push(ic); ic=10; break;
                case 32 : p.pop(); break;
                case 33 : System.out.println("Sortie g"); break;
                case 34 : ic=p.peek(); break;
            }
        }
    }
}