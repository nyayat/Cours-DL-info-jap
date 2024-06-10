/* très vilain programme (récursion mal formulée)
 * qui compile correctement, mais dont l'exécution
 * lève une exception (java.lang.StackOverflowError)
 * et met en évidence l'utilisation d'une "pile d'appel"
 */

class PileDAppel{
    static void f(){
        System.out.println("appel de f");
        g();
    }
    static void g(){
        System.out.println("appel de g");
        f();
    }
    public static void main(String[] args){
        f();
    }
}











