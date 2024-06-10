class Hepta{
    /* conversion en base 7 (version récursive)
     * plutôt que de manipuler des chaînes de caractères comme ça:
     *      static String heptaS(int n){
     *          if(n==0) return "";
     *          return heptaS(n/7)+n%7;
     *      }
     * on choisit de manipuler des écritures heptales (vs décimales)
     */
    static int hepta(int n){
        if(n < 7)                       // 3000: saut conditionnel
            return n;                   // 3001: màj valeur retour
                                        // 3002: sortie
        return hepta(n/7) * 10 + n % 7; // 3003: appel (récursif)
                                        // 3004: màj valeur retour
                                        // 3005: sortie
    }
    public static void main(String[] args){
        int n = 2049;   // 0
        System.out.println(" ("+n+")_10 = ("+hepta(n)+")_7");
                        // 1: appel
                        // 2: màj valeur retour
                        // 3: affichage
    }// 4: sortie
}
