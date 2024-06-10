import java.util.*;

/* un programme pour résoudre le problème des N dames
 * utilisant le retour sur trace (backtracking in english)
 */

class HuitDames{
    static ArrayList<Integer> sol;  // sol est le vecteur solution (en cours de construction)
                                    // ici: le i-ème élément est le numéro de col de la dame de la ligne i
    static int dim;                 // dim est une des dimensions du problème
                                    // ici: le nombre de lignes (= de colonnes) de l'échiquier
    static int nbSol;               // nbSol est le nombre de solution(s)
    // d'autres variables globales peuvent être utiles...
    
    static boolean solutionComplete(){   // à personnaliser en fonction du problème
        return sol.size()==dim;
    }
    static ArrayList<Integer> possibles(){// à personnaliser en fonction du problème
        ArrayList<Integer> pos=new ArrayList<Integer>();
        // les dames déjà en place sont aux coordonnées:
        // (0,sol.get(0)), (1,sol.get(1)), ..., (sol.size()-1,sol.get(sol.size()-1))
        boolean menace=false;
        for(int col=0; col<dim; col++){     // numéros de colonne candidats
            menace=false;
            for(int i=0; i<sol.size(); i++) // numéros de ligne des dames placées
                if(col==sol.get(i)          // même colonne
                ||                          // ou même diagonale:
                   Math.abs(col-sol.get(i)) // distance colonne_candidate colonne_i-ème_dame
                 == Math.abs(sol.size()-i)) // == distance ligne_candidate ligne_i-ème_dame
                    menace=true;
            if(!menace) pos.add(col);
        }
        return pos;
    }
    
    /* schéma général de backtracking:
     * * les variables globales
     * * les types de sol et de pos
     * * les méthodes solutionComplete() et possibles()
     * sont à personnaliser.
     */
    static void backtrack(){         // on est sur un noeud
        if(solutionComplete()){             // si ce noeud est un fruit (ie, une feuille-solution)
            nbSol++;
            //  System.out.println(nbSol+":"+sol);
            //  on affiche une ou toutes les solutions (au choix, selon le contexte)
        }else                               // on est sur un noeud interne
            for(Integer i:possibles()){       // on parcourt les successeurs possibles
                //(si c'est vide, cela correspond à élaguer)
                sol.add(i);                 // on descend vers le noeud successeur
                backtrack();                // on lance la récursion
                sol.remove(sol.size()-1);   // on remonte
            }
    }
    
    public static void main(String[] args){
        sol=new ArrayList<Integer>();
        dim=13;
        nbSol=0;
        backtrack();
        System.out.println("le nombre de solution(s): "+nbSol);
	}
    
}
