import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for (int[] p : prerequisites) {
            int a = p[0];
            int b = p[1];
            graph.get(b).add(a); // b → a
            indegree[a]++;
        }

        // Step 2: Add all nodes with indegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 3: BFS
        int count = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;

            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        return count == numCourses;
    }
}