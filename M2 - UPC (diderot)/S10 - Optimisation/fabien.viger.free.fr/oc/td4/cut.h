#ifndef __CUT_H
#define __CUT_H

#include <vector>
using namespace std;

// Description d'un modèle de planche vendable: longueur et prix.
struct PricedCut {
  int length;  // Sera toujours >= 1.
  double price;  // Sera toujours > 0.
};

// Détermine la manière optimale de découper une planche de longueur L
// en planches vendables (pour maximiser le prix de vente total).
// Renvoie le prix de vente maximal.
// Écrit la liste des modèles choisis (un même modèle peut être répété
// plusieurs fois), dans un ordre quelquonque, dans le vecteur "cut_indices"
// (on écrira simplément l'indice des modèles).
//
// IMPORTANT: Le test de cette fonction est en 2 parties: d'abord, il teste
// simplement la valeur de renvoi, ensuite il teste "cut_indices". Si vous
// ne remplissez pas ce dernier, vous pourrez quand meme avoir des points.
//
// ATTENTION: les gains sont des nombres réels (double), pas des entiers.
//
// EXEMPLE:
// OptimalCut(257, {{40, 3.8}, {70, 6.7}, {90, 9.0}}) = 3.8 + 6.7 + 6.7 + 6.7
// avec cut_indices = [0, 1, 1, 1]. 
double OptimalCut(int L, const vector<PricedCut>& cuts,
                  vector<int>* cut_indices)

#endif  // __CUT_H
