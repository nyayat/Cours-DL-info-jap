#ifndef __JOB3_H
#define __JOB3_H

#include "job.h"

// Comme BestJobAssignment1(), mais les exclusivités sont globales
// (2 jobs "exclusifs" ne peuvent pas tourner tous les 2, meme sur
//  des machines différentes).
vector<int> BestJobAssignment3(const vector<Resources>& jobs,
                               const vector<Resources>& machines,
                               const vector<vector<int>>& global_exclusive);

#endif  // __JOB3_H
