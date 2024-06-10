import java.util.*;

class Fibo{
    //version récursive (non-terminale)
    static int fibo(int a){
        if(a==0 || a==1) return 1;
        return fibo(a-1)+fibo(a-2);
    }
    //version récursive terminale
    static int fiboTermAux(int a, int p, int q){
        //p et q jouent le rôle des valeurs de retour fibo(a-2) et fibo(a-1)
        if(a==0 || a==1) return q;
        return fiboTermAux(a-1,q,p+q);
        //on fait glisser la fenêtre d'un cran
    }
    static int fiboTerm(int a){
        return fiboTermAux(a,1,1);
    }
    //version itérative (SANS PILE)
    static int fiboIter(int a){
        int p=1, q=1, aux;
        while(a!=0 && a!=1){
            a=a-1;      //
            aux=p;      // on utilise ici une variable auxiliaire
            p=q;        // pour simuler la simultanéité des màj
            q=aux+q;    //(on aurait pu choisir d'utiliser un xor)
        }
        return q;
    }
    public static void main(String[] args){
        int k=9;
        System.out.println("fibo("+k+")="+fibo(k));
        System.out.println("fiboTerm("+k+")="+fiboTerm(k));
        System.out.println("fiboIter("+k+")="+fiboIter(k));
    }
}
