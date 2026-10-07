class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int balance, int leftRem, int rightRem, StringBuilder current, Set<String> result) {
        if (balance < 0) {
            return;
        }

        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && balance == 0) {
                result.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = current.length();

        if (c == '(') {
            if (leftRem > 0) {
                backtrack(s, index + 1, balance, leftRem - 1, rightRem, current, result);
            }
            current.append(c);
            backtrack(s, index + 1, balance + 1, leftRem, rightRem, current, result);
            current.setLength(len);
        } else if (c == ')') {
            if (rightRem > 0) {
                backtrack(s, index + 1, balance, leftRem, rightRem - 1, current, result);
            }
            current.append(c);
            backtrack(s, index + 1, balance - 1, leftRem, rightRem, current, result);
            current.setLength(len);
        } else {
            current.append(c);
            backtrack(s, index + 1, balance, leftRem, rightRem, current, result);
            current.setLength(len);
        }
    }
}