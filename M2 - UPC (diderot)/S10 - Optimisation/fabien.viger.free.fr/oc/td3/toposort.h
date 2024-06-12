#ifndef __TOPOSORT_H
#define __TOPOSORT_H

#include "graph.h"
#include <vector>
using namespace std;

// Fait le tri topologique du graphe dirigé donné en argument, et renvoie
// les indices des noeuds triés selon l'ordre topologique: les "feuilles"
// (noeuds n'ayant pas d'arcs sortant) seront plutot vers le début.
// Si le graphe a un cycle (ce n'est donc pas un DAG, il n'y a donc pas
// d'ordre topologique), la fonction devra renvoyer une tableau vide.
//
// A NOTER / INDICES:
// - Plusieurs ordres topologiques différents existent souvent.
// - Puisque le graphe vous donne la liste des enfants d'un noeud, et
//   que vous auriez plutot besoin de la liste des parents pour faire un
//   tri topologique comme demandé, vous avec 2 choix:
//   a) précalculer la liste des parents de chaque noeuds
//   b) faire le tri topologique inverse (ou on commencerai par les noeuds
//      n'ayant pas de parents), puis inverser le resultat.
// - On pourra pré-calculer le "degré résiduel" de chaque noeud (entrant ou
//   sortant, selon que vous avez choisi a) ou b)), et initialiser une file
//   FIFO (queue<int> par exemple) qui contiendra tous les noeuds de degré
//   residuel zéro, mise à jour dynamiquement.
// - La complexité devra être O(M) (nombre d'arcs).
//
// Exemple:
//
// 2-->5-->6-->7   Plusieurs ordres topologiques valides (liste non exhaustive):
//     ^   |   |    - [4, 0, 7, 6, 5, 1, 2, 3]
//     |   v   v    - [0, 4, 7, 6, 5, 2, 1, 3]
// 3-->1   0   4    - [4, 7, 0, 6, 5, 1, 3, 2]
//
vector<int> TopologicalSort(const DirectedGraph& graph);

#endif  // __TOPOSORT_H
