import java.util.*;

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Create adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build graph
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]); // because bi-directional
        }

        // Visited array
        boolean[] visited = new boolean[n];

        // Call DFS
        return dfs(graph, visited, source, destination);
    }

    private boolean dfs(List<List<Integer>> graph, boolean[] visited, int current, int destination) {
        if (current == destination) return true;

        visited[current] = true;

        for (int neighbor : graph.get(current)) {
            if (!visited[neighbor]) {
                if (dfs(graph, visited, neighbor, destination)) {
                    return true;
                }
            }
        }

        return false;
    }
}