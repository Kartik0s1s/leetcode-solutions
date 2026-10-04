class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                minOpen++;
                maxOpen++;
            } 
            else if (ch == ')') {
                minOpen--;
                maxOpen--;
            } 
            else { // '*'
                // '*' can be ')' or '(' or empty
                minOpen--;  // treat '*' as ')'
                maxOpen++;  // treat '*' as '('
            }

            // Even the most optimistic case cannot be valid
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative
            minOpen = Math.max(minOpen, 0);
        }

        // If some possible interpretation has 0 open brackets
        return minOpen == 0;
    }
}