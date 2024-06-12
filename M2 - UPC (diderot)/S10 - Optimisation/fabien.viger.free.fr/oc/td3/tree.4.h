#ifndef __TREE_4_H
#define __TREE_4_H

#include <vector>
using namespace std;

// Exactement comme la question précédente, mais cette fois on calcule pour
// chaque noeud i la taille de son sous-arbre (nombre de noeuds, i inclus, qui
// sont descendants de i).
// 
// Indices: le code ressemble beaucoup à la question précédente.
//
// Exemple:
//    .3.     vector<vector<int>> enfants = [ [], [], [1], [2, 0, 4], [] ]
//  .' | '.
//  2  0  4   -> AllSubtreeSizes(enfants) = [1, 1, 2, 5, 1]
//  |
//  1
vector<int> AllSubtreeSizes(const vector<vector<int>>& enfants);

#endif  // __TREE_4_H
