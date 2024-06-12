#ifndef __JOB1_H
#define __JOB1_H

#include "job.h"

// Comme BestJobAssignment(), mais on a des contraintes d'exclusivités locales:
// pour chaque liste d'exclusivité L dans "local_exclusive",
// cela exprime le fait que 2 jobs ayant des indices dans L ne peuvent être
// placés sur la même machine.
vector<int> BestJobAssignment1(const vector<Resources>& jobs,
                               const vector<Resources>& machines,
                               const vector<vector<int>>& local_exclusive);

#endif  // __JOB1_H
