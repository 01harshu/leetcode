class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;

        for(int i = 0; i < s.length(); i++){
            totalSum += ('z' - s.charAt(i) + 1) * (i + 1);
        }
        return totalSum;
        
    }
}