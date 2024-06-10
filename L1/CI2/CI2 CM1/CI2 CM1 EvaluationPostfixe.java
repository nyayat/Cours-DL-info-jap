import java.util.*;

/* algorithme pour l'évaluation d'une forme postfixe
 * (d'expression arithmétique entière):
 * on lit l'expression de gauche à droite
 * si un élément est un opérande, on l'empile
 * sinon (c'est un opérateur (binaire))
 *  * on dépile (deux) éléments
 *  * on exécute l'opération
 *  * on empile son résultat
 *
 */

class EvaluationPostfixe{
    public static void main(String[] args){
        System.out.println(evaluation(args));
    }
    static String evaluation(String[] e){
        Stack<Integer> p=new Stack<Integer>();  //pile d'entier
        int e1,e2;
        /* rappel pour convertir un entier n en une chaîne: ""+n
           rappel pour convertir une chaîne s en un entier: Integer.valueOf(s) */
        for(int i=0; i<e.length; i++)   //parcours de l'expression
            if(e[i].equals("+") || e[i].equals("%")
               || e[i].equals("x") || e[i].equals("/") || e[i].equals("-")){
               //on cherche à dépiler deux opérandes:
                if(p.empty()) return "expression mal formée";//manque un opérande
                e1 = p.pop();
                if(p.empty()) return "expression mal formée";//manque un opérande
                e2 = p.pop();
                switch(e[i]){
                    case "+": p.push(e2+e1); break;
                    case "%": p.push(e2%e1); break;
                    case "x": p.push(e2*e1); break;
                    case "/": p.push(e2/e1); break;
                    case "-": p.push(e2-e1); break;
                }}
            else
                p.push(Integer.valueOf(e[i]));
        if(p.empty()) return "expression mal formée";
        e1 = p.pop();
        if(p.empty()) return "l'évaluation est "+e1;
        return "expression mal formée";
    }
}
