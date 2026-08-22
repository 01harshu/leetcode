class Solution {
    public boolean checkDivisibility(int n) {
        int sum = 0;
        int p = 1;
        int temp = n;

        while(temp > 0){
            int digit = temp % 10;
            sum += digit;
            p *= digit;
            temp /= 10; 
        }

        return n % (sum + p) == 0;
        
    }
}