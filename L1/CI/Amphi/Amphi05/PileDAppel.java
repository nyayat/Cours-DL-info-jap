/* vilain programme (récursion mal formulée)
 * qui compile correctement, mais dont l'exécution
 * lève une exception (java.lang.StackOverflowError)
 * et met en évidence l'utilisation d'une "pile d'appel"
 */

class PileDAppel{
    static void f(int x){
        System.out.println("appel de f #"+x);
        g(x+1);
    }
    static void g(int x){
        System.out.println("appel de g #"+x);
        f(x+1);
    }
    public static void main(String[] args){
        f(1);
    }
}
