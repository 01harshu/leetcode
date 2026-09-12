class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] sorted = new int[n][4];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = intervals.get(i).get(0);
            sorted[i][1] = intervals.get(i).get(1);
            sorted[i][2] = intervals.get(i).get(2);
            sorted[i][3] = i;
        }

        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));

        long[][] dp = new long[n + 1][5];
        List<Integer>[][] choice = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                choice[i][k] = new ArrayList<>();
            }
        }

        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int low = i + 1, high = n - 1, target = sorted[i][1];
            int ans = n;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (sorted[mid][0] > target) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            next[i] = ans;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                dp[i][k] = dp[i + 1][k];
                choice[i][k] = choice[i + 1][k];

                int nxtIdx = next[i];
                long takeWeight = sorted[i][2] + dp[nxtIdx][k - 1];
                List<Integer> takeChoice = new ArrayList<>(choice[nxtIdx][k - 1]);
                takeChoice.add(sorted[i][3]);
                Collections.sort(takeChoice);

                if (takeWeight > dp[i][k]) {
                    dp[i][k] = takeWeight;
                    choice[i][k] = takeChoice;
                } else if (takeWeight == dp[i][k]) {
                    if (isLexicographicallySmaller(takeChoice, choice[i][k])) {
                        choice[i][k] = takeChoice;
                    }
                }
            }
        }

        List<Integer> bestChoice = choice[0][4];
        long maxScore = dp[0][4];
        for (int k = 1; k < 4; k++) {
            if (dp[0][k] > maxScore) {
                maxScore = dp[0][k];
                bestChoice = choice[0][k];
            } else if (dp[0][k] == maxScore) {
                if (isLexicographicallySmaller(choice[0][k], bestChoice)) {
                    bestChoice = choice[0][k];
                }
            }
        }

        int[] res = new int[bestChoice.size()];
        for (int i = 0; i < bestChoice.size(); i++) {
            res[i] = bestChoice.get(i);
        }
        return res;
    }

    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        if (a.isEmpty()) return false;
        if (b.isEmpty()) return true;
        int minLen = Math.min(a.size(), b.size());
        for (int i = 0; i < minLen; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}