class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        int totalN = n + k - 1;
        int totalK = 2 * k;

        if(totalK > totalN) return 0;

        long num = 1;
        long den = 1;

        for(int i = 1; i <= totalK; i++){
            num = (num * (totalN - i + 1)) % MOD;
            den = (den * i) % MOD;
        }

        return (int) ((num * modInverse(den, MOD)) % MOD);
        
    }
    private long modInverse(long a, long m){
        return power(a, m - 2, m);
    }

    private long power(long base, long exp, long mod){
        long res = 1;
        base %= mod;
        while(exp > 0){
            if((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}