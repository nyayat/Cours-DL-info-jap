#ifndef __PARCEL2_H
#define __PARCEL2_H

#include <vector>
using namespace std;

// Le problème est le même, mais au lieu de renvoyer le gain maximum, on
// veut renvoyer la liste des parcelles à cultiver pour obtenir le gain maximal.
//
// EXEMPLE:
// OptimalParcels([3, 7, 2, 1, 8, 4, 3]) = [1, 4, 6] (indices choisis)
vector<int> OptimalParcels(const vector<int>& gain);

#endif  // __PARCEL2_H
