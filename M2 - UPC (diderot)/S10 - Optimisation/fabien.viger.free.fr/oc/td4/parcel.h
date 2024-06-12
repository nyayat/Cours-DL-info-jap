#ifndef __PARCEL_H
#define __PARCEL_H

#include <vector>
using namespace std;

// Le gain de cultiver la parcelle #i est gain[i]. Si on cultive la parcelle
// #i, on ne peut pas cultiver la parcelle #i-1 ni la parcelle #i+1.
// Cette fonction renvoie le gain maximum.
//
// EXEMPLE:
// MaxGain([3, 7, 2, 1, 8, 4, 3]) = 18
int MaxGain(const vector<int>& gain);

#endif  // __PARCEL_H
