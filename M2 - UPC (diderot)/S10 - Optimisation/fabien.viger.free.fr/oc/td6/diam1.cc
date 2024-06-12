#include "diam1.h"
#include "bfs.1.h"
#include "bfs.2.h"
#include "bfs.3.h"
#include <ctime>
#include <algorithm>

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

// Le type "pair" est inclus dans <utility>
// Pour créer une paire :
// pair<int,int> paire = {0, 1};
// Pour accéder aux éléments d'une paire : paire.first et paire.second

pair<int, int> DiametreLB(const UndirectedGraph& graph) {

  // temps maximal en secondes
  const double tmax = 1e-6 * (graph.NumNodes() + graph.NumEdges()) + 50e-3;
  // conversion en "clock ticks" (package <ctime>): ici on n'utilise que 40%
  // du temps alloué
  const clock_t deadline = clock() + 0.4 * CLOCKS_PER_SEC * tmax;
  
  // maintenant, pour vérifier si le temps limite est dépassé, il suffit de tester
  // si la condition "clock() > deadline" est vraie ou fausse.
  
  

}

