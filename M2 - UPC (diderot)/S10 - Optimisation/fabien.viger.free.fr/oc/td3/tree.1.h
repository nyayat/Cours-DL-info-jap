#ifndef __TREE_1_H
#define __TREE_1_H

#include <vector>
using namespace std;

// Entrée: le tableau de parents d'un arbre enraciné (ou d'une forêt d'arbres
// enracinés): parent[i] = -1 si le noeud i est une racine, et sinon c'est
// le noeud parent de i dans l'arbre qui contient i.
//
// Sortie: le tableau des liste d'enfants: l'élément i du tableau de sortie
// est un vector<int> listant tous les enfants du noeud i (il pourra etre
// vide si i n'a pas d'enfants), dans un ordre quelconque.
//
// Exemple:
//
//    .3.     vector<int> parents = [3, 2, 3, -1, 3, 1]  // size()=6 = num nodes
//   .'|'.
//  2  0  4   vector<vector<int>> enfants = [ [],
//  |                                         [5],
//  1                                         [1],
//  |                                         [2, 0, 4],
//  5                                         [],
//                                            [] ]
vector<vector<int>> ParentVectorToChildrenVector(const vector<int>& parent);

#endif  // __TREE_1_H
