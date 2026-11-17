class Solution {
    static int [][] dp;
    public int change(int amount, int[] coins) {
        int n = coins.length; 
        dp = new int [n][amount+1];
        
        for(int [] row : dp){
            Arrays.fill(row , -1);
        }

        return solve(n-1, amount , coins);
        
    }

    private int solve(int i, int amount , int [] coins){
        if(i==0){
            if(amount % coins[0] == 0){
                return  1;
            }
            return 0;
        }

        if(dp[i][amount] != -1){
            return dp[i][amount];
        }



        int notTake = solve(i-1, amount, coins);

        int take = 0;
        if(coins[i] <= amount){
            take = solve(i, amount - coins[i] , coins);
        }

        return dp[i][amount] = take + notTake;
    }
}