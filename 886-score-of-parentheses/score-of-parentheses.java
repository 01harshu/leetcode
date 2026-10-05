class Solution {
    public int scoreOfParentheses(String s) {
        int sc = 0;
        int de = 0;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                de++;
            }else{
                de--;
                if(s.charAt(i - 1) == '('){
                    sc += 1 << de;
                }
            }
        }
        return sc;
        
    }
}