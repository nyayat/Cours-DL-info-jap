import java.util.*;

/* Exercice 2 du l'examen de mai 2014.
 * Aux deux symboles ouvrants "{" et "do"
 * correspondent les deux symboles fermants "}" et "while" (respectivement).
 * Le symbole "while" peut se retrouver isolé.
 */

class DoWhile{

    public static void main(String[] args){
        if(estCorrect(args))
            System.out.println("l'expression est syntaxiquement correcte!");
        else
            System.out.println("l'expression n'est pas syntaxiquement correcte!");
    }
    static boolean estCorrect(String[] e){
        Stack<String> p=new Stack<String>(); //pour empiler des "{" et des "do"
        for(int i=0; i<e.length; i++){
            String s=e[i];
            System.out.println("pile:"+p);
            switch(s){
                case "{": case "do": p.push(s); break;
                case "}": if(p.empty() || !p.pop().equals("{")) return false; break;
                case "while": if(!p.empty() && p.peek().equals("do")) p.pop(); break;
                default: return false;
            }
        }
        return p.empty();
    }

}
