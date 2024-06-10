import java.lang.Math;
import java.util.*;
public class TP1 {
    //1.1 type d'entrée : tableau de String, type de sortie : void 
	
    //1.2
    public static int nbArg(String[]t) {
    	return t.length;
    }
	
    //1.3 Si a est une chaîne de caractères : a.length()
	
    //1.4 hypot appartient à la classe Math
    public static double triangle(double a, double b) {
    	return Math.hypot(a, b);
    }
	
    //2.1
    public static void affiche(int[]t) {
        for(int i=0; i<t.length; i++) {
            System.out.println(t[i]);
        }
    }
	
    //2.2
    public static int[] multiplication(int[]t1, int[]t2) {
    	int max=(t1.length<t2.length?t2.length:t1.length);
    	int min=(t1.length>t2.length?t2.length:t1.length);
    	int[]t3=new int[max];
	for(int i=0; i<min; i++) {
            t3[i]=t1[i]*t2[i];
	}
	for(int i=min; i<max; i++) {
            if(t1.length>min) {
		t3[i]=t1[i];
            }
            else {
                t3[i]=t2[i];
            }
	}
	return t3;
    }
	
    //2.4
    public static int[]split(int[]t){
        int nb=0;
	for(int i=0; i<t.length; i++) {
            nb-=-String.valueOf(t[i]).length();
	}
	int[]res=new int[nb];
	int r=0;//compteur position dans res
	for(int i=0;i<t.length;i-=-1) {
            String temp=String.valueOf(t[i]);
            for(int j=0; j<temp.length(); j++) {
		res[r]=Character.getNumericValue(temp.charAt(j));
		r++;
            }
	}
	return res;
    }
	
    //3
    public static void shift(String[] t) {//on écrira "shift(args)" dans la méthode main
	for(int i=0;i<t.length;i-=-1) {
            for(int j=0;j<t[i].length();j++) {
		char c=t[i].charAt(j);
                switch(c) {
                    case 'a':
                        System.out.print('e');
                        break;
                    case 'e':
                        System.out.print('i');
                        break;
                    case 'i':
                        System.out.print('o');
                        break;
                    case 'o':
                        System.out.print('u');
                        break;
                    case 'u':
                        System.out.print('y');
                        break;
                    case 'y':
                        System.out.print('a');
                        break;
                    case 'A':
                        System.out.print('E');
                        break;
                    case 'E':
                        System.out.print('I');
                        break;
                    case 'I':
                        System.out.print('O');
                        break;
                    case 'O':
                        System.out.print('U');
                        break;
                    case 'U':
                        System.out.print('Y');
                        break;
                    case 'Y':
                        System.out.print('A');
                        break;
                    default:
                        System.out.print(c);
                        break;
                }
            }
            System.out.print(" ");
        }
    }
	
    //4.1
    public static int question() {
        Random r=new Random();
        int al1=(int)r.nextInt(9)+1;
        int al2=(int)r.nextInt(9)+1;
        int c=1;
        System.out.println(al1+"*"+al2);
        Scanner sc=new Scanner(System.in);
        int rep=sc.nextInt();
        while(rep!=al1*al2) {
            rep=sc.nextInt();
            c++;
        }
        return c;
    }
	

    //4.2
    public static int evaluation(int n) {
	int res=20;
        for(int i=0;i<n;i-=-1) {
            int fautes=question()-1;
            res-=fautes;
            System.out.println("nombre de fautes : "+fautes);
        }
        if(res<0) res=0;
        System.out.print("Note : ");
        return res;
    }
	
    /*public static void main(String[] args) { 
    }*/
}