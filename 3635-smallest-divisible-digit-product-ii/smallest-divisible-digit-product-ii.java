//import java.util.Arrays;

class Solution {
    public String smallestNumber(String num, long t) {
        long temp = t;
        int[] req = new int[10];
        int[] primes = {2, 3, 5, 7};
        for (int p : primes) {
            while (temp % p == 0) {
                req[p]++;
                temp /= p;
            }
        }
        if (temp > 1) return "-1";

        int n = num.length();
        int[] numDigits = new int[n];
        for (int i = 0; i < n; i++) {
            numDigits[i] = num.charAt(i) - '0';
        }

        int[][] prefixReq = new int[n + 1][10];
        prefixReq[0] = req.clone();

        int zeroIdx = -1;
        for (int i = 0; i < n; i++) {
            if (numDigits[i] == 0) {
                zeroIdx = i;
                break;
            }
            prefixReq[i + 1] = prefixReq[i].clone();
            reduceFactors(prefixReq[i + 1], numDigits[i]);
        }

        int limit = (zeroIdx != -1) ? zeroIdx : n - 1;

        if (zeroIdx == -1 && minDigitsNeeded(prefixReq[n]) == 0) {
            return num;
        }

        for (int i = limit; i >= 0; i--) {
            int startDigit = numDigits[i] + 1;
            for (int d = startDigit; d <= 9; d++) {
                int[] remReq = prefixReq[i].clone();
                reduceFactors(remReq, d);
                int remLen = n - 1 - i;
                if (minDigitsNeeded(remReq) <= remLen) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++) sb.append(numDigits[j]);
                    sb.append(d);
                    sb.append(buildSmallestSuffix(remReq, remLen));
                    return sb.toString();
                }
            }
        }

        int minLenForT = minDigitsNeeded(req);
        int targetLen = Math.max(n + 1, minLenForT);
        
        return buildSmallestSuffix(req.clone(), targetLen);
    }

    private void reduceFactors(int[] req, int d) {
        if (d <= 1) return;
        int temp = d;
        int[] primes = {2, 3, 5, 7};
        for (int p : primes) {
            while (temp % p == 0 && req[p] > 0) {
                req[p]--;
                temp /= p;
            }
        }
    }

    private int minDigitsNeeded(int[] req) {
        int c2 = Math.max(0, req[2]);
        int c3 = Math.max(0, req[3]);
        int c5 = Math.max(0, req[5]);
        int c7 = Math.max(0, req[7]);

        int count8 = c2 / 3;
        c2 %= 3;
        int count9 = c3 / 2;
        c3 %= 2;

        int extra = 0;
        if (c2 == 2 && c3 == 1) extra = 2; // e.g. 3, 4 or 2, 6
        else if (c2 > 0 || c3 > 0) extra = 1;

        return count8 + count9 + c5 + c7 + extra;
    }

    private String buildSmallestSuffix(int[] req, int len) {
        char[] res = new char[len];
        for (int i = 0; i < len; i++) {
            for (int d = 1; d <= 9; d++) {
                int[] testReq = req.clone();
                reduceFactors(testReq, d);
                if (minDigitsNeeded(testReq) <= len - 1 - i) {
                    res[i] = (char) ('0' + d);
                    req = testReq;
                    break;
                }
            }
        }
        return new String(res);
    }
}