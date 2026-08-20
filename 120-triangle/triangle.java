class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];

        for (int row = n - 1; row >= 0; row--) {
            List<Integer> currentRow = triangle.get(row);
            for (int i = 0; i < currentRow.size(); i++) {
                dp[i] = currentRow.get(i) + Math.min(dp[i], dp[i + 1]);
            }
        }

        return dp[0];
    }
}