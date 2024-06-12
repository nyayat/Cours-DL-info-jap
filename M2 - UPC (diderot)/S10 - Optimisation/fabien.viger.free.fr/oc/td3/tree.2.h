#ifndef __TREE_2_H
#define __TREE_2_H

#include <vector>
using namespace std;

// Entrée: un arbre, représenté par son tableau d'enfants (la sortie de la fonction
// faite dans la question précédente), et un noeud X de cet arbre.
//
// Sortie: la hauteur du sous-arbre de X: 0 si X n'a pas d'enfant, 1 si X n'a que des
// enfants qui n'ont eux-mêmes pas d'enfants, etc.
//
// Indice: la fonction pourra être récursive.
// 
// Exemple:
//    .3.     vector<vector<int>> enfants = [ [], [], [1], [2, 0, 4], [] ]
//   .'|'.
//  2  0  4   -> Height(enfants, 0) = 0 ;   Height(enfants, 1) = 0
//  |         -> Height(enfants, 2) = 1 ;   Height(enfants, 3) = 2
//  1         -> Height(enfants, 4) = 0
int Height(const vector<vector<int>>& enfants, int node);

#endif  // __TREE_2_H
