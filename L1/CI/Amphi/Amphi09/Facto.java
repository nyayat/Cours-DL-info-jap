import java.util.*;

class Facto{
    /* version récursive "non terminale":
     * il y a une opération (ici une multiplication par l'argument)
     * entre l'appel récursif et le retour (à la ligne 13)
     */
    static Stack<String> pile;//pile qui mime la pile d'appel (uniquement les empilements)
    
    static int facto(int k){
        pile.push(""+k); System.out.println(pile);//mime l'empilement de l'appel
        if(k<2) return 1;
        return k*facto(k-1);
    }
    /* version récursive terminale: ici, on utilise un paramètre p
     * qui aura un "rôle d'accumulateur" et qui désignera la valeur de retour
     */
    static int factoTermAux(int k, int p){
        pile.push(""+k+"-"+p); System.out.println(pile);//mime l'empilement de l'appel
        if(k<2) return p; // le bloc du haut de la pile contient le résultat!!!
        return factoTermAux(k-1,k*p);
    }
    static int factoTerm(int k){
        return factoTermAux(k,1);
    }
    /* ici, on transforme (de façon algorithmique)
     * la version récursive terminale version itérative (SANS PILE):
     * * les paramètres-accumulateurs servent de variables locales
     * * elles sont initialisées selon l'appel à la version terminale auxiliaire
     * * le(s) test(s) d'arrêt est(sont) transformé(s) en test(s) de poursuite
     * * on encode le passage de paramètres (et sa simultanéité !!!)
     * * on renvoie le paramètre portant la valeur de retour
     */
    static int factoIter(int k){
        int p=1;
        while(k>=2){
            p=k*p;  // attention: on doit ici
            k=k-1;  // simuler la simultanéité
        }
        return p;
    }
    public static void main(String[] args){
        int x = 8;
        pile=new Stack<String>();
        System.out.println("facto("+x+")="+facto(x));
        pile=new Stack<String>();
        System.out.println("factoTerm("+x+")="+factoTerm(x));
        System.out.println("factoIter("+x+")="+factoIter(x));
    }
}










