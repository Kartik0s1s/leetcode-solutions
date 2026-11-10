class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length; 
        int n = matrix[0].length; 
        
        int dp [][] = new int [m][n];

        for(int i=0; i<n; i++){
            dp[n-1][i] = matrix[m-1][i]; 
        }
        
        for(int i=m-2; i>=0; i--){

            for(int j=0; j< n ; j++){

            int downLeft  =  Integer.MAX_VALUE;
            int down      =  dp[i+1][j];
            int downRight = Integer.MAX_VALUE;
            
            if( j > 0  ){
                downLeft = dp[i+1][j-1];
            }


            if(  j < n-1 ){
                downRight = dp[i+1][j+1];
            }
              
             dp[i][j] = matrix[i][j] + Math.min(down , Math.min(downLeft, downRight));

            }
        }

        int min = Integer.MAX_VALUE; 

        for(int i=0; i<1; i++){
            for(int j=0; j<n; j++){
                min = Math.min(min, dp[i][j]);
            }
        }

        return min;
    }
}