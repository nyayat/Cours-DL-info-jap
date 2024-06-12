#ifndef __FLOW_H
#define __FLOW_H

#include <vector>

using std::vector;

// Problème de l'affectation de traffic réseau dans un backbone:
// On gère un backbone, qui est un graphe dirigé valué (chaque arc a une
// certaine bande passante).
// Le backbone est donné sous forme de ce graphe: on connait le nombre de
// noeuds N, et on a les arcs valués (from, to, capacity) où "from" et "to"
// sont des noeuds (donc des entiers entre 0 et N-1 inclus) et "capacity"
// est la bande passante, en Gb/s.
// On a également un ensemble de demandes commerciales de traffic, se
// présentant sous la forme (origine, destination, bande passante max,
// prix par Gb/s): cette demande correspond à un client qui veut envoyer
// du traffic du noeud "origine" au noeud "destination" en empruntant notre
// réseau, et qui est prêt a payer un certain prix par Gb/s qu'on lui alloue,
// dans la limite de sa bande passante max.
//
// Quels demandes commerciales doit-on choisir, en quelles quantités?
// Comment router le traffic réseau correspondant?
struct BackboneArc {
  int from;  // Un noeud
  int to;    // Un noeud
  double capacity;  // >= 0
};
struct FlowDemand {
  int src;  // Source: un noeud
  int dst;  // Destination: un noeud
  double demand;  // >= 0
  double price;   // per unit of demand (max profit = demand * price)
};
struct FlowOnArc {
  int arc_index;  // Index dans l'entrée "backbone".
  int demand_index;  // Index dans l'entrée "demands".
  double flow;
};
vector<FlowOnArc> BestFlow(int num_nodes,
                           const vector<BackboneArc>& backbone,
                           const vector<FlowDemand>& demands);

#endif  // __FLOW_H
