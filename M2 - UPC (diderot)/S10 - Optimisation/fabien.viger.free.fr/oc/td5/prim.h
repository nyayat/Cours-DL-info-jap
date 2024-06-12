#ifndef __PRIM_H
#define __PRIM_H

#include <vector>
using namespace std;

// Renvoie le poids de l'arbre couvrant minimal pour le graphe valué donné:
// N est le nombre de sommets, et "edges" liste toutes les arêtes du graphe,
// avec leur poids.
// On suppose que le graphe donné en entrée est connexe (inutile de vérifier).
//
// EXEMPLE:
//   N = 4
//   edges = [{0, 1, 23}, {1, 3, 13}, {2, 1, 6}, {3, 2, 11}, {0, 2, 41}]
//
//    .--[23]--1--[13]--.      Les noeuds sont 0,1,2,3.
//  .´         |         `.    Les poids des arêtes sont entre [ ].
// 0          [6]          3
//  `.         |         .´    Arbre couvrant minimal: 23 + 6 + 11 = 40.
//    `--[41]--2--[11]--´
//
struct Edge {
  int node1;
  int node2;
  int weight;
};
int Prim(int N, const vector<Edge>& edges);

#endif  // __PRIM_H
