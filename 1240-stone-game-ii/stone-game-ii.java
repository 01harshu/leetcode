class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        int[][] memo = new int[n][n + 1];
        return helper(0, 1, suffixSum, memo, n);
    }

    private int helper(int i, int m, int[] suffixSum, int[][] memo, int n) {
        if (i >= n) {
            return 0;
        }
        if (i + 2 * m >= n) {
            return suffixSum[i];
        }
        if (memo[i][m] != 0) {
            return memo[i][m];
        }

        int maxStones = 0;
        for (int x = 1; x <= 2 * m; x++) {
            int take = suffixSum[i] - helper(i + x, Math.max(m, x), suffixSum, memo, n);
            maxStones = Math.max(maxStones, take);
        }

        memo[i][m] = maxStones;
        return maxStones;
    }
}