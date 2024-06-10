/* attention: désormais, on opte pour
 * la convention où c'est l'appelant qui dépile
 */

class Facto{
    static int f(int n){
        int i=1, r=1;       // 500 màj variable1
                            // 501 màj variable2
        while(i<=n){        // 502 saut conditionnel
            r = r*i;        // 503 màj de variable2
            i = i+1;        // 504 màj de variable1
        }                   // 505 saut inconditionnel
        return r;           // 506 màj valeur de retour
    }                       // 507 retour
    public static void main(String[] args){
        int x=4;                                // 0: màj de mem[0]
        System.out.println("resultat : "+f(x)); // 1: appel
                                                // 2: retour
    }                       //3: sortie du main/programme
}
