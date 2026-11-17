class Solution {
    static  int [][] dp ;
    public int coinChange(int[] coins, int amount) {
      int n = coins.length;
      dp  = new int [n][amount+1];
      
      for(int row [] : dp){
        Arrays.fill(row , -1);
      }
      
      int ans =  solve(coins.length-1,coins, amount);
      return ans >= Integer.MAX_VALUE /2 ? -1: ans;   
    }

    private int solve(int i, int [] coins, int amount ){
        if(i==0){
            if(amount % coins[0] == 0 ){
                return amount/coins[0];
            }
            return Integer.MAX_VALUE/2;
        }

        if(dp[i][amount] != -1){
            return dp[i][amount] ;
        }

        int notTake = solve(i-1, coins, amount);

        int take = Integer.MAX_VALUE/2;
        if(coins[i] <= amount){
            take = 1 + solve(i, coins, amount-coins[i]);
        }

        return dp[i][amount] = Math.min(take, notTake);
    }
}