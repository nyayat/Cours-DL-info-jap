#ifndef __JOB4_H
#define __JOB4_H

#include "job.h"

#include <utility>
using std::pair;

// Comme BestJobAssignment2(), mais les dépendances sont bidirectionelles,
// et globales: chaque paire (A, B) dans "global_dep" implique que
// les jobs A et B ne peuvent pas tourner l'un sans l'autre (pas forcément
// sur la même machine).
vector<int> BestJobAssignment4(const vector<Resources>& jobs,
                               const vector<Resources>& machines,
                               const vector<pair<int, int>>& global_dep);

#endif  // __JOB4_H
