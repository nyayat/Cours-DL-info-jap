#ifndef __TREE_3_H
#define __TREE_3_H

#include <vector>
using namespace std;

// Entrée: un arbre, représenté par son tableau d'enfants (la sortie de la
// fonction faite dans la question précédente).
//
// Sortie: les hauteurs de tous les noeuds de l'arbre (le tableau des valeurs
// de Height(i) pour tous les noeuds i de 0 à N-1).
//
// Complexité: O(N).
//
// Indice: vous pourrez utiliser une fonction auxilliaire récursive, déclarée
// et définie dans le .cc, qui ressemblera beaucoup à la Height() de la
// question précédente, à la différence qu'elle utilisera la mémorisation.
// 
// Exemple:
//    .3.     vector<vector<int>> enfants = [ [], [], [1], [2, 0, 4], [] ]
//  .' | '.
//  2  0  4   -> AllHeights(enfants) = [0, 0, 1, 2, 0]
//  |
//  1
vector<int> AllHeights(const vector<vector<int>>& enfants);

#endif  // __TREE_3_H
