class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] seen = new boolean[101];
        for (int num : nums) {
            seen[num] = true;
        }
        int multiple = k;
        while (true) {
            if (multiple > 100 || !seen[multiple]) {
                return multiple;
            }
            multiple += k;
        }
    }
}