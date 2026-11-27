class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int[] free = new int[n];

        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            minPrice = Math.min(minPrice, items[i][1]);

            for (int j = 0; j < n; j++) {

                if (i != j &&
                    items[j][0] % items[i][0] == 0) {

                    free[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1];

        // Base case: spend budget on cheapest item
        for (int b = 0; b <= budget; b++) {
            dp[b] = b / minPrice;
        }

        // Standard 0/1 Knapsack
        for (int i = 0; i < n; i++) {

            int price = items[i][1];
            int reward = 1 + free[i];

            for (int b = budget; b >= price; b--) {

                int notTake = dp[b];

                int take = reward + dp[b - price];

                dp[b] = Math.max(take, notTake);
            }
        }

        return dp[budget];
    }
}