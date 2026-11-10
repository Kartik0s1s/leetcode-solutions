class Solution {

    int dp [][] ;

    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size(); 

        dp = new int [n][n];

        for(int i=0; i<n; i++){
           
           dp[n-1][i]  = triangle.get(n-1).get(i);
            
        }

        return solve( triangle, dp);
    }

    private int solve(  List<List<Integer>> triangle , int [][] dp){
        int n = triangle.size(); 
        
       for(int i=n-2; i>=0; i--){
            for(int j=0; j<= i; j++){
                 
                int down = dp[i+1][j];
                int diagonal =  dp[i+1][j+1];

                dp[i][j] = triangle.get(i).get(j) + Math.min(down , diagonal);
            }
       }
     return dp[0][0];
    }
}