class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if (k == n) {
            int max = -1;
            for (int num : nums) {
                max = Math.max(max, num);
            }
            return max;
        }

        if (k == 1) {
            int[] freq = new int[51];
            for (int num : nums) {
                freq[num]++;
            }
            int max = -1;
            for (int i = 0; i <= 50; i++) {
                if (freq[i] == 1) {
                    max = i;
                }
            }
            return max;
        }

        int[] freq = new int[51];
        for (int num : nums) {
            freq[num]++;
        }

        int first = nums[0];
        int last = nums[n - 1];

        int ans = -1;
        if (freq[first] == 1) {
            ans = Math.max(ans, first);
        }
        if (freq[last] == 1) {
            ans = Math.max(ans, last);
        }

        return ans;
    }
}