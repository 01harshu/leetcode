class Solution {
    public int minInsertions(String s) {
        int res = 0;
        int openNeeded = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openNeeded += 2;
                if (openNeeded % 2 != 0) {
                    res++;
                    openNeeded--;
                }
            } else {
                openNeeded--;
                if (openNeeded < 0) {
                    res++;
                    openNeeded += 2;
                }
            }
        }

        return res + openNeeded;
    }
}