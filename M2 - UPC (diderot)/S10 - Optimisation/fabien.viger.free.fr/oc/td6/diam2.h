#ifndef __DIAM2_H
#define __DIAM2_H

#include <vector>
#include "ugraph.h"

using namespace std;

// Étant donné un graphe, cette fonction renvoie soit:
// - un noeud (donc, un vector<int> avec un seul élément) "central",
//   qui minimise le "rayon" du graphe pour ce centre: le "rayon" est
//   la plus grande distance à un autre noeud du graphe.
// - une sous-clique centrale, c'est-à-dire un sous-ensemble de noeuds
//   tels qu'ils soient tous directement connectés entre eux par une
//   arête, et tel que le "rayon" soit minimal pour ce "multi-centre".
//
// Dans les 2 cas, on peut déduire une borne supérieure sur le diamètre
// du graphe:
// - si on a renvoyé un noeud de rayon R: Diamètre <= 2*R
// - si on a renvoyé une sous-clique de rayon R: Diamètre <= 2*R+1
//
// Pour faire simple, vous pourrez vous limiter à renvoyer un simple noeud.
// Pour être un peu meilleur, vous pourrez renvoyer une arête (2 noeuds
// liés par une arête). Voire une sous-clique...
//
// ATTENTION:
// Même temps maximal imparti que pour l'exercice précédent.
// Le score sera fonction de la borne sup du diamètre (voire formule en haut):
// plus elle est petite, meilleur sera le score.
vector<int> DiametreUB(const UndirectedGraph& graph);

#endif  // __DIAM2_H
