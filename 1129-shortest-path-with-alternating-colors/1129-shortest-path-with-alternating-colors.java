import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        // Build graphs
        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) {
            redGraph[e[0]].add(e[1]);
        }

        for (int[] e : blueEdges) {
            blueGraph[e[0]].add(e[1]);
        }

        // result array
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // visited[node][color]
        boolean[][] visited = new boolean[n][2]; 
        // 0 = red, 1 = blue

        Queue<int[]> queue = new LinkedList<>();
        
        // Start with both colors
        queue.offer(new int[]{0, 0}); // last edge red
        queue.offer(new int[]{0, 1}); // last edge blue

        visited[0][0] = true;
        visited[0][1] = true;

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0];
                int color = curr[1];

                // update shortest distance
                if (result[node] == -1) {
                    result[node] = distance;
                }

                // alternate color
                if (color == 0) { // last was red → go blue
                    for (int next : blueGraph[node]) {
                        if (!visited[next][1]) {
                            visited[next][1] = true;
                            queue.offer(new int[]{next, 1});
                        }
                    }
                } else { // last was blue → go red
                    for (int next : redGraph[node]) {
                        if (!visited[next][0]) {
                            visited[next][0] = true;
                            queue.offer(new int[]{next, 0});
                        }
                    }
                }
            }

            distance++;
        }

        return result;
    }
}