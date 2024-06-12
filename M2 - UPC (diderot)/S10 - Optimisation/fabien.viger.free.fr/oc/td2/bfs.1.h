#include "ugraph.h"
#include <vector>
using namespace std;

// Runs a Breadth-First-Search on the graph, starting from node "src".
// See https://en.wikipedia.org/wiki/Breadth-first_search .
// Returns a vector of size N (N is the number of nodes of the
// graph) representing the "parent" tree: parent[i] is the parent of
// node #i in the BFS tree. The parent of "src" is itself, and the
// parent of a node that wasn't reached by the BFS exploration is -1.
vector<int> BFS(const UndirectedGraph& graph, int src);
