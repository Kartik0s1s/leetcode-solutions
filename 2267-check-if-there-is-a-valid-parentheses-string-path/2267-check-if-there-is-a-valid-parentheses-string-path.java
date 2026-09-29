class Solution {

    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int len = m + n - 1;

        // Valid parentheses string must have even length
        if ((len & 1) == 1) {
            return false;
        }

        // Must start with '('
        if (grid[0][0] == ')') {
            return false;
        }

        // Must end with ')'
        if (grid[m - 1][n - 1] == '(') {
            return false;
        }

        // dp[column][balance]
        boolean[][] dp = new boolean[n][len + 1];

        // Starting '(' gives balance = 1
        dp[0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                boolean[] current = dp[j];

                int maxBalance = i + j + 1;

                if (grid[i][j] == '(') {

                    // '(' : previous = balance - 1
                    // Process high -> low
                    for (int balance = maxBalance;
                         balance >= 0;
                         balance--) {

                        int previous = balance - 1;

                        if (previous < 0) {
                            current[balance] = false;
                            continue;
                        }

                        boolean fromTop =
                                i > 0 && current[previous];

                        boolean fromLeft =
                                j > 0 && dp[j - 1][previous];

                        current[balance] =
                                fromTop || fromLeft;
                    }

                } else {

                    // ')' : previous = balance + 1
                    // Process low -> high
                    for (int balance = 0;
                         balance <= maxBalance;
                         balance++) {

                        int previous = balance + 1;

                        if (previous > len) {
                            current[balance] = false;
                            continue;
                        }

                        boolean fromTop =
                                i > 0 && current[previous];

                        boolean fromLeft =
                                j > 0 && dp[j - 1][previous];

                        current[balance] =
                                fromTop || fromLeft;
                    }
                }
            }
        }

        return dp[n - 1][0];
    }
}