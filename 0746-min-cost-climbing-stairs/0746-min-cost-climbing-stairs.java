class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int prev1 = 0; // dp[i-1]
        int prev2 = 0; // dp[i-2]

        for (int c : cost) {
            int curr = c + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = curr;
        }

        return Math.min(prev1, prev2);
    }
}