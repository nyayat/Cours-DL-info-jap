
/* la mémoïsation consiste à éviter les appels récursifs rédondants
 * en utilisant une mémoire auxiliaire
 */

class Fibo{
    //fonction récursive de départ
    static long fibo(int k){
        if(k==0 || k==1) return 1;
        return fibo(k-1)+fibo(k-2);
    }
    //fonction récursive avec "affichage de l'arbre" des appels récursifs
    static long fibo(int k, String s){
        System.out.println(s+"f("+k+")");
        if(k==0 || k==1) return 1;
        return fibo(k-1,s+"\t")+fibo(k-2,s+"\t");
    }
    //version récursive mémoïsée
    static long[] mem;
    static long fiboaux(int k){
        if(mem[k]==0){  //la valeur de fibo(k) n'est pas encore dans mem
            if(k==0 || k==1) mem[k]=1;
            else mem[k]=fiboaux(k-1)+fiboaux(k-2);
        }
        return mem[k];
    }
    static long fibomemo(int k){
        mem=new long[k+1];   //allocation de la mémoire auxiliaire
        return fiboaux(k);
    }
    //test
    public static void main(String[] args){
        System.out.println(fibo(7,""));
        System.out.println(fibomemo(50));
    }
}
