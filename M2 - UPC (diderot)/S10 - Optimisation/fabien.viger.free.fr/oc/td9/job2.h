#ifndef __JOB2_H
#define __JOB2_H

#include "job.h"

#include <utility>
using std::pair;

// Comme BestJobAssignment1(), mais on a en plus des contraintes de dépendance
// locales: pour chaque paire (A, B) dans "local_dep", job A dépend de job B
// localement, ce qui veut dire que si job A tourne sur une machine, alors
// job B tourne aussi, et sur la meme machine.
vector<int> BestJobAssignment2(const vector<Resources>& jobs,
                               const vector<Resources>& machines,
                               const vector<pair<int, int>>& local_dep);

#endif  // __JOB2_H
