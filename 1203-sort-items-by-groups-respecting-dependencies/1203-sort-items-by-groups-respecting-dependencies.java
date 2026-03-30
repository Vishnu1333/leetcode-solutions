import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        
        // Step 1: assign new group to -1
        int newGroupId = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = newGroupId++;
            }
        }

        // Step 2: build graphs
        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < newGroupId; i++) groupGraph.add(new ArrayList<>());

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[newGroupId];

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // Step 3: topo sort
        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, newGroupId);

        if (itemOrder.size() == 0 || groupOrder.size() == 0) {
            return new int[0];
        }

        // Step 4: group items
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for (int item : itemOrder) {
            groupToItems.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        // Step 5: build result
        List<Integer> result = new ArrayList<>();
        for (int g : groupOrder) {
            List<Integer> items = groupToItems.get(g);
            if (items != null) {
                result.addAll(items);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int size) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);

            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        return result.size() == size ? result : new ArrayList<>();
    }
}