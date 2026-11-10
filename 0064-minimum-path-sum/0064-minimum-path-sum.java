class Solution {

    int [][] dp;

    public int minPathSum(int[][] grid) {
         
        int m = grid.length; 
        int n = grid[0].length;

        dp = new int [m][n];

        for(int row [] : dp){
            Arrays.fill(row,-1);
        }

        return solve(m-1,n-1, grid, dp);
    }

    private int solve(int m , int n , int [][] grid, int [][] dp ){
      if(m < 0|| n < 0 ){
        return Integer.MAX_VALUE; 
      }

       
      if(m==0 && n==0){
        return grid[0][0];
      }

      if(dp[m][n] != -1){
        return dp[m][n];
      }


      int up = solve(m-1, n, grid, dp);
      int left = solve(m,n-1, grid, dp);
 
      return dp[m][n] =  grid[m][n] +  Math.min(up,left);
    }

}