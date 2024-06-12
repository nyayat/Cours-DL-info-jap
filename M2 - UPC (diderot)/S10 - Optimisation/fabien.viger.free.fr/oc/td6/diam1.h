#ifndef __DIAM1_H
#define __DIAM1_H

#include <utility>
#include "ugraph.h"

using namespace std;

// Étant donné un graphe, cette fonction trouve 2 noeuds dont la distance
// (la longueur du plus court chemin entre ces 2 noeuds) est la plus grande
// possible: cela représentera une borne inférieure du diamètre.
//
// ATTENTION:
// Votre algorithme devra tourner en un temps maximal imparti par graphe:
// Tmax = 50ms + (M+N)*1µs, où M est le nombre d'arêtes et N de noeuds.
// Ça correspond a du O(M+N).
// Par exemple, si M = 10M (10 millions), N = 1M (1million), Tmax = 11.05s.
//
// Ensuite, le score dépendra uniquement du diamètre obtenu: plus il est
// grand, meilleur sera votre score.
// Mais si le temps dépasse (sur ma machine!), le score sera 0: gardez une
// bonne marge!
pair<int, int> DiametreLB(const UndirectedGraph& graph);

#endif  // __DIAM1_H
