#include <vector>
using namespace std;

// Extracts the distances of each node in the given BFS tree, which
// is the returned format described in 2.2.h
// Eg. in the following tree, whose root is "2":
//
//         .---- 4
//         v
//   2 <-- 3 <-- 1
//   ^
//   '---- 0 <-- 5
//
// The bfs tree is represented by the following 'parent' vector:
// [2, 3, 2, 2, 3, 0]
// And the distance vector should be:
// [1, 2, 0, 1, 2, 2]
//
// If a node was not reached by the BFS, its parent is -1, and its distance
// should also be -1.
vector<int> GetBfsDistances(const vector<int>& parent);
