class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int total = 0;
        for (int num : nums) total += num;

        int prefix = 0;

        for (int i = 0; i < n; i++) {
            int left = nums[i] * i - prefix;
            int right = (total - prefix - nums[i]) - nums[i] * (n - i - 1);

            res[i] = left + right;
            prefix += nums[i];
        }
        return res;
    }
}