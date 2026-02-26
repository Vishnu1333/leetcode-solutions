class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<int[]>> map = new TreeMap<>();
        Queue<Object[]> queue = new LinkedList<>();
        
        queue.offer(new Object[]{root, 0, 0});
        
        while (!queue.isEmpty()) {
            Object[] arr = queue.poll();
            TreeNode node = (TreeNode) arr[0];
            int col = (int) arr[1];
            int row = (int) arr[2];
            
            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(new int[]{row, node.val});
            
            if (node.left != null)
                queue.offer(new Object[]{node.left, col - 1, row + 1});
            if (node.right != null)
                queue.offer(new Object[]{node.right, col + 1, row + 1});
        }
        
        List<List<Integer>> result = new ArrayList<>();
        
        for (List<int[]> list : map.values()) {
            Collections.sort(list, (a, b) -> 
                a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            
            List<Integer> temp = new ArrayList<>();
            for (int[] arr : list) temp.add(arr[1]);
            result.add(temp);
        }
        
        return result;
    }
}