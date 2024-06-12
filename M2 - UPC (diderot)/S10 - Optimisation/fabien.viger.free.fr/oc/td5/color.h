#ifndef __COLOR_H
#define __COLOR_H

#include <vector>
#include "ugraph.h"  // Vous l'avez: il était dans test.tar.gz.

using namespace std;

// Étant donné un graph non dirigé, renvoie un coloriage utilisant
// aussi peu de couleurs que possible.
// Entrée: le graphe non dirigé.
// Valeur de retour: le nombre de couleur C de votre coloriage.
// Sortie: "color" doit contenir autant d'élément que le nombre de noeuds
//         du graphe: color[i] sera la couleur du noeud #i.
//         color[i] doit etre entre 0 et C-1, et deux noeuds ayant une
//         arête en commun ne peuvent pas avoir la même couleur.
//
// NOTE: On attends pas un algorithme optimal (NP complet!), mais votre
// heuristique devra etre aussi "bonne" que possible.
int Color(const UndirectedGraph& graph,
          vector<int>* color);  // La sortie. N'oubliez pas de la remplir!

#endif  // __COLOR_H
