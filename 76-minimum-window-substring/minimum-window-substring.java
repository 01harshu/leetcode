class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        
        int[] tFreq = new int[128];
        for (char c : t.toCharArray()) {
            tFreq[c]++;
        }

        int[] windowFreq = new int[128];
        int requiredChars = 0;
        for (int count : tFreq) {
            if (count > 0) requiredChars++;
        }

        int formedChars = 0;
        int left = 0, right = 0;

        
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            windowFreq[rightChar]++;

            
            if (tFreq[rightChar] > 0 && windowFreq[rightChar] == tFreq[rightChar]) {
                formedChars++;
            }

            
            while (left <= right && formedChars == requiredChars) {
                
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);
                windowFreq[leftChar]--;

                if (tFreq[leftChar] > 0 && windowFreq[leftChar] < tFreq[leftChar]) {
                    formedChars--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}