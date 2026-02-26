class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int max = 0, count = 0;

        for (char c : tasks) {
            freq[c - 'A']++;
            max = Math.max(max, freq[c - 'A']);
        }

        for (int f : freq) {
            if (f == max) count++;
        }

        return Math.max(tasks.length,
                (max - 1) * (n + 1) + count);
    }
}