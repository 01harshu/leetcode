class Solution {
    public int[] validSequence(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[] last = new int[n2 + 1];
        last[n2] = n1;
        
        for (int j = n2 - 1, i = n1 - 1; j >= 0; j--) {
            while (i >= 0 && word1.charAt(i) != word2.charAt(j)) {
                i--;
            }
            last[j] = i;
            if (i >= 0) {
                i--;
            }
        }
        
        int[] ans = new int[n2];
        boolean changed = false;
        int i = 0;
        
        for (int j = 0; j < n2; j++) {
            boolean found = false;
            while (i < n1) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    if (!changed || last[j + 1] >= i + 1) {
                        ans[j] = i;
                        i++;
                        found = true;
                        break;
                    }
                } else {
                    if (!changed && last[j + 1] >= i + 1) {
                        ans[j] = i;
                        changed = true;
                        i++;
                        found = true;
                        break;
                    }
                }
                i++;
            }
            if (!found) {
                return new int[0];
            }
        }
        
        return ans;
    }
}