#ifndef __FAS_H
#define __FAS_H

#include "graph.h"

// Renvoie un sous-graphe de "graph" (même noeuds, mais un sous-ensemble des
// arcs), acyclique, et avec le plus d'arcs possible.
// Comme les exercices précédents, vous aurez un temps maximal par graphe,
// un peu supérieur cette fois:
// Tmax = 50ms + (M+N)*10µs
//
// Le score sera:
// - 0 si la sortie est incorrecte, ne serait-ce que sur un ensemble de test.
// - si toutes les sorties sont correctes, la somme des scores:
//   - chaque score sera de 0 si le temps max est dépassé
//   - sinon il dépendra du nombre d'arcs du graphe que vous renvoyez.
DirectedGraph MaxDAG(const DirectedGraph& graph);

#endif  // __FAS_H
