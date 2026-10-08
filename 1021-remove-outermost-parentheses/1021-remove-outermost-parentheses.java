class Solution {
    public String removeOuterParentheses(String s) {

        StringBuilder result = new StringBuilder();
        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;

                // Not the outermost '('
                if (balance > 1) {
                    result.append(ch);
                }

            } else {
                balance--;

                // Not the outermost ')'
                if (balance > 0) {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }
}