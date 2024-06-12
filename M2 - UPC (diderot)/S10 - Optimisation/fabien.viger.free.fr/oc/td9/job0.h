#ifndef __JOB0_H
#define __JOB0_H

#include "job.h"

// Comme le BestJobAssignment() original (sans contraintes exotiques),
// mais cette fois:
// 1) Les jobs rapportent un certain "profit", et vous voulez maximiser
//    la somme des profits des jobs assignés.
// 2) Vous devrez etre capables de traiter des problemes
//    de taille beaucoup plus grande
// 3) Vous ne devez pas dépasser 10s de temps de calcul.
// 4) Du coup, vous n'etes pas obligé de renvoyer la solution optimale:
//    votre solution sera jugée en fonction de sa qualité (elle devra
//    bien sûr etre faisable!)
vector<int> BestJobAssignment0(const vector<Resources>& jobs,
                               const vector<double>& profits,
                               const vector<Resources>& machines);

#endif  // __JOB0_H
