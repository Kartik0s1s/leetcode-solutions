class Solution {
    public int minFallingPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int dp [][] = new int [m][n];

        for(int row [] : dp){
            Arrays.fill(row , Integer.MAX_VALUE);
        }

        for(int i=0; i<n; i++){
            dp[m-1][i]= grid[m-1][i];
        } 

        for(int i=m-2; i>=0; i--){
            for(int j=0; j<n; j++){

              for(int k=0; k<n; k++){
                if(k==j){
                    continue;
                }
                if(grid[i][j] + dp[i+1][k] < dp[i][j] ){
                    dp[i][j] = grid[i][j] + dp[i+1][k];
                }
              }
            }
        }
    int min = Integer.MAX_VALUE;
    for(int i=0; i<n; i++){
        min = Math.min(min,dp[0][i]);
    }
    return min;
    }
}