public class Exemple2{
    /* en Java, le passage d'arguments se fait uniquement "par valeur":
     * à la ligne 11: au moment de son appel, f reçoit le contenu de la boîte: la valeur 7 (et rien d'autre!)
     * à la ligne 13: au moment de son appel, f reçoit le contenu de la boîte: la valeur 51 (et rien d'autre!)
     * à la ligne 16: au moment de son appel, f reçoit le contenu de la boîte: la référence au tableau {1,3} (et rien d'autre!)
     */
    public static void main(String[] args){
        int x=7;
        int[] y={1,3};
        System.out.println("valeur de x avant l'appel à f: "+ x);//affichage: 7
        f(x);       // l'argument de f est de type int: c'est la fonction définie lignes 21-25 (push/pop)
        System.out.println("valeur de x après l'appel à f: "+ x);//affichage: 7
        f(x*x+2);   // l'argument de f est de type int: c'est la fonction définie lignes 21-25 (push/pop)
        System.out.println("valeur de x après l'appel à f: "+ x);//affichage: 7
        System.out.println("valeur de y[0] avant l'appel à f: "+ y[0]);//affichage: 1
        f(y);       // l'argument de f est de type int[]: c'est la fonction définie lignes 28-32 (push/pop)
        System.out.println("valeur de y[0] après l'appel à f: "+ y[0]);//affichage: 2
    }
    /* fonction qui prend un argument de type int
     */
    static void f(int x){
        int y=3;
        x=x+1;
        y=y+x;
    }
    /* fonction qui prend un argument de type (référence vers) tableau de int
     */
    static void f(int[] x){
        int y=3;
        x[0]=x[0]+1;
        y=y+x[0];
    }
    /* dans les deux cas (fonction f lignes 21-25 et fonction f lignes 28-32)
     * le paramètre x et la variable locale y
     * ne peuvent pas être distingués en tant que variables:
     * une fois l'exécution de f enclenchée, elles ont
     * une place (dans la pile), un statut et un comportement comparables;
     * la différence se situe dans le moment où chacune
     * est affectée: au moment de l'appel de f pour x
     * et au moment de l'instruction d'affectation pour y
     */
}
