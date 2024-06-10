import java.util.*;

/* un programme pour énumérer des courbes de type dragon
 *
 * (voir aussi l'exercice 5 de l'examen IP1 2012)
 * utilisant le retour sur trace (backtracking in english)
 */

class TypeDragon{
    static ArrayList<Point> sol;    //sol est le vecteur solution (en cours de construction)
                                    //ici: les points successifs de la courbe
    static int dim;                 //dim est une des dimensions du problème
                                    //ici: la taille du carré disons
    static int nbSol;               //nbSol est le nombre de solution(s)
    // d'autres variables globales peuvent être utiles...
    
    static boolean solutionComplete(){   //à personnaliser en fonction du problème
        boolean b=false;
        //...
        return b;
    }
    static ArrayList<Point> possibles(){ //à personnaliser en fonction du problème
        ArrayList<Point> pos=new ArrayList<Point>();
        //...
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
            for(Point i:possibles()){       // on parcourt les successeurs possibles
                                            //(si c'est vide, cela correspond à élaguer)
                sol.add(i);                 // on descend vers le noeud successeur
                backtrack();                // on lance la récursion
                sol.remove(sol.size()-1);   // on remonte
            }
    }
    
    public static void main(String[] args){
        sol=new ArrayList<Point>();
        dim=16;
        nbSol=0;
        backtrack();
        System.out.println("le nombre de solution(s): "+nbSol);
	}
}

class Point{
    int x,y;
}
