/* une fonction qui calcule la factorielle
 * mais dont on a artificiellement extrait
 * l'environnement
 * (arguments, variables locales, valeur de retour).
 * ainsi seule l'adresse de retour
 * a besoin d'être empilée.
 * cf FactoFacticeTraduit
 * nous verrons (Amphi 6) que ce procédé n'est
 * cependant pas généralisable et qu'il faut
 * envisager de pouvoir empiler tout
 * l'environnement d'un appel d'une fonction
 */

class FactoFactice{
/* rappel: du point de vue de la "traduction", les variables locales au "main"
 * ont le même statut que les variables statiques du programme
 */
    static int n, i ,r;         //mem[0], mem[1], mem[2]
    static void f(){
        i=1;                    // 100
        r=1;                    // 101
        while(i<=n){            // 102 : saut conditionnel vers 106
            r = r*i;            // 103
            i = i+1;            // 104
        }                       // 105 : saut inconditionnel vers 102
        return ;                // 106 : sortie
    }
    public static void main(String[] args){
        n=4;                                //0: initialisation de la variable
                                            //   qui joue le rôle de l'argument de f
        f();                                //1: appel à f
        System.out.println("resultat : "+r);//2: affichage de la valeur calculée
    }                                       //3: sortie du main/programme
}
