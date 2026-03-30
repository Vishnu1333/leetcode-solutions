class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];

        // Build in-degree and out-degree
        for (int[] t : trust) {
            int a = t[0];
            int b = t[1];
            out[a]++; // a trusts someone
            in[b]++;  // b is trusted by someone
        }

        // Find the judge
        for (int i = 1; i <= n; i++) {
            if (in[i] == n - 1 && out[i] == 0) {
                return i;
            }
        }

        return -1;
    }
}