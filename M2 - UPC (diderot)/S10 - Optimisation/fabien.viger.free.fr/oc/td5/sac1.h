#ifndef __SAC1_H
#define __SAC1_H

#include <vector>
using namespace std;

// On a un sac à dos de capacité C, et des objets de poids p[0], p[1], ...
// On veut selectionner le plus grand nombre possible d'objets tel que
// la somme de leurs poids soit <= C.
// Cette fonction renvoie ce nombre maximal d'objets.
//
// EXEMPLE: C=12, p=[4, 7, 2, 5, 5, 1, 10]
//          -> renvoie 4 car on peut mettre 4 objets au maximum
//             (de poids 4, 2, 5, 1: 4+2+5+1 = 12)
int Sac1(int C, const vector<int>& p);

#endif  // __SAC1_H
