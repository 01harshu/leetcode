class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long low = 1;
        long high = (long) 1e18;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (count(coins, mid, n) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long count(int[] coins, long m, int n) {
        long total = 0;
        int totalSubsets = 1 << n;

        for (int i = 1; i < totalSubsets; i++) {
            long lcmVal = 1;
            int bitCount = 0;
            boolean overflow = false;

            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    bitCount++;
                    lcmVal = lcm(lcmVal, coins[j]);
                    if (lcmVal > m) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            if (bitCount % 2 == 1) {
                total += m / lcmVal;
            } else {
                total -= m / lcmVal;
            }
        }
        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}