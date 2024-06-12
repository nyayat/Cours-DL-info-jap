#ifndef __LONGEST_PATH_DAG_H
#define __LONGEST_PATH_DAG_H

#include "graph.h"
#include <vector>
using namespace std;

// Entrée: un graphe dirigé et valué: on donne un poids positif à chaque
// noeud dans le tableau "poids".
//
// Sortie: si le graphe est cyclique, renvoyez -1. Sinon, renvoyez la
// somme des poids des noeuds sur le chemin le plus "lourd", i.e. le chemin
// qui maximise la somme des poids de ses noeuds.
//
// Indices: Si vous avez codé TopologicalSort(), vous pourrez l'utiliser pour
// obtenir la réponse relativement facilement. Sinon, pensez à la
// programmation dynamique!
// Dans les 2 cas, il pourra être utile de créer un tableau qui stockera pour
// chaque noeud i le poids du plus lourd chemin partant de i.
//
// Exemple (les poids des noeuds sont mis entre parenthèse)
//
// 2(4)-->5(5)-->6(1)-->7(2)     Le chemin le plus long/lourd est
//        ^      |      ^        unique dans cet exemple: c'est
//        |      v      |        2->5->6->0, qui a un poids total de 14.
// 3(2)-->1(1)   0(4)   4(11)
//
int LongestPathInDag(const DirectedGraph& graph, vector<int>& poids);

#endif  // __LONGEST_PATH_DAG_H
