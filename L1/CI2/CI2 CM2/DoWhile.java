import java.util.*;

/* Exercice 2 du l'examen de mai 2014.
 *
 */

class DoWhile{

    public static void main(String[] args){
        if(estCorrect(args))
            System.out.println("l'expression est correcte");
        else
            System.out.println("l'expression n'est pas correcte");
    }

    public static boolean estCorrect(String[] e){
        Stack<String> p = new Stack<String>();  //contiendra des "{" et des "do"
        System.out.println("# pile: "+p);         //pour voir l'évolution du contenu
        for(int i=0; i<e.length; i++){          //parcours de gauche à droite
            switch(e[i]){
                case "do": case "{": p.push(e[i]); break;
                case "}":   if(p.empty()) return false; //manque une "{" à gauche
                            if(p.peek().equals("{")) p.pop();
                            else return false; break;   //un "do" n'a pas son "while"
                case "while":
                    if(!p.empty() && p.peek().equals("do")) p.pop(); break;
                    // si ce test n'est pas vérifié, c'est le "while" est isolé
                /* on peut imaginer le cas par défaut en cette fin de switch
                 *      default: return false;
                 * qui permet de rejeter toute expression
                 * contenant autre chose que "{", "}", "do" et "while"
                 */
            }
            System.out.println("# pile: "+p);     //pour voir l'évolution du contenu
        }
        return p.empty(); //vérifie que la pile a bien été complètement vidée
    }
}
