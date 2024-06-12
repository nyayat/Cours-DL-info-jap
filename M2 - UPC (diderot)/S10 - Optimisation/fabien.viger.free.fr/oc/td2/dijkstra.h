#include "vgraph.h"

// Runs a Dijkstra search on the graph, starting from node "src".
// See https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm .
// Returns the same "parent" vector as BFS() in bfs.1.h.
vector<int> Dijkstra(const DirectedGraph& graph, int src);
