 
class Solution {

    static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        // Step 1: Precompute reflection destinations
        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                int pos = i * n + j;

                if (grid[i][j] == 0) {

                    right[i][j] = pos;
                    down[i][j] = pos;

                } else {

                    // Entered from left -> reflect down
                    right[i][j] =
                        (i + 1 < m) ? down[i + 1][j] : -1;

                    // Entered from above -> reflect right
                    down[i][j] =
                        (j + 1 < n) ? right[i][j + 1] : -1;
                }
            }
        }

        // Step 2: Dynamic Programming
        int[][] dp = new int[m][n];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) {
                    continue;
                }

                int ways = dp[i][j];

                // Try moving right
                if (j + 1 < n) {

                    int dest = right[i][j + 1];

                    if (dest != -1) {

                        int x = dest / n;
                        int y = dest % n;

                        dp[x][y] =
                            (int)((dp[x][y] + (long) ways) % MOD);
                    }
                }

                // Try moving down
                if (i + 1 < m) {

                    int dest = down[i + 1][j];

                    if (dest != -1) {

                        int x = dest / n;
                        int y = dest % n;

                        dp[x][y] =
                            (int)((dp[x][y] + (long) ways) % MOD);
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
