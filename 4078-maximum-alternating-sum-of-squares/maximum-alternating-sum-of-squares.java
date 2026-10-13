class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squared = new long[n];
        for(int i = 0; i < n; i++){
            squared[i] = (long) nums[i] * nums[i];

        }
        Arrays.sort(squared);
        long score = 0;
        int mid = n / 2;
        for(int i = 0; i < n; i++){
            if(i < mid){
                score -= squared[i];
            }else{
                score += squared[i];
            }
        }
        return score;
        
    }
}