class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean sortable = true;
            int previousMax = Integer.MIN_VALUE;

            for (int start = 0; start < n; start += k) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int descents = 0;

                for (int j = 0; j < k; j++) {
                    int current = nums[start + j];
                    int next = nums[start + (j + 1) % k];

                    min = Math.min(min, current);
                    max = Math.max(max, current);

                    if (current > next) {
                        descents++;
                    }
                }

                if (descents > 1 ||
                    (start > 0 && previousMax > min)) {
                    sortable = false;
                    break;
                }

                previousMax = max;
            }

            if (sortable) {
                ans += k;
            }
        }

        return ans;
    }
}