import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // add subset

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);               // choose
            backtrack(nums, i + 1, current, result); // explore
            current.remove(current.size() - 1); // unchoose
        }
    }
}