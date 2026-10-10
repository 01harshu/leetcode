import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long totalK = (long) k1 + k2;
        long sumDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sumDiff += diff[i];
        }

        if (sumDiff <= totalK) {
            return 0;
        }

        int[] count = new int[100001];
        for (int d : diff) {
            count[d]++;
        }

        for (int d = 100000; d > 0 && totalK > 0; d--) {
            if (count[d] > 0) {
                long take = Math.min((long) count[d], totalK);
                count[d] -= take;
                count[d - 1] += (int) take;
                totalK -= take;
            }
        }

        long result = 0;
        for (int d = 1; d <= 100000; d++) {
            if (count[d] > 0) {
                result += (long) count[d] * d * d;
            }
        }

        return result;
    }
}