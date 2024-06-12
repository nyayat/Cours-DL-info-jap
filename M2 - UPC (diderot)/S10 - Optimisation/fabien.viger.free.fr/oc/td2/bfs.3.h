#include <vector>
using namespace std;

// Returns the shortest path, from the source of a BFS to the given target node.
// The argument is the target node and the BFS "parent" tree.
// If the target node was not reached by the BFS, the returned path should be
// empty.
// Example: using the same example as in 2.3.h, with BFS 'parent' tree:
// [2, 3, 2, 2, 3, 0]
// Then:
// - the shortest path to node #4 should be: [2, 3, 4]
// - the shortest path to node #0 should be: [2, 0]
// - the shortest path to node #5 should be: [2, 0, 5]
// - the shortest path to node #2 should be: [2]
vector<int> GetShortestPathFromRootedTree(const vector<int>& parent, int target);
