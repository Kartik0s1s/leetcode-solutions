class Solution {
    public int minAddToMakeValid(String s) {
        int balance = 0;
        int ans = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;
            } else {
                balance--;

                if (balance < 0) {
                    ans++;
                    balance = 0;
                }
            }
        }

        return ans + balance;
    }
}