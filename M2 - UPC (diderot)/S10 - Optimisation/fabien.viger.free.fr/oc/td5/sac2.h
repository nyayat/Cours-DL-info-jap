#ifndef __SAC2_H
#define __SAC2_H

#include <vector>
using namespace std;

// On a un sac à dos de capacité C, et des objets de poids p[0], p[1], ...
// et de valeurs v[0], v[1], ...
// Cette fonction renvoie la valeur maximale que l'on peut collecter dans le sac
// à dos, en supposant qu'on peut mettre les objets de manière fractionnaire.
//
// EXEMPLE: C=12, p=[4, 7, 2, 5, 5, 1, 10], v=[3.1, 99, 0.1, 0.2, 6.6, 0.4, 111]
// -> Doit renvoyer 154.5. Explication:
//    la solution optimale est de prendre l'objet #1 en entier (poids 7, valeur 99)
//    et la moitié de l'objet #6 (poids 10, valeur 111).
//    On a bien un poids total de 7*1 + 10*0.5 = 12.
//    On obtient une valeur totale de 99*1 + 111*0.5 = 154.5
double Sac2(int C, const vector<int>& p, const vector<double>& v);

#endif  // __SAC2_H
