// class Solution {
//     public int rob(int[] nums) {
//         int n = nums.length; 

//         return solve( nums ,n-1); 
//     }

//     private int solve( int [] nums,  int i){
//         if(i<0){
//             return 0; 
//         }

//         int take = nums[i] + solve(nums,i-2);
//         int notTake = solve(nums, i-1);

//         return Math.max(take , notTake);
//     }
// }



class Solution {

    int dp [] ; 

    public int rob(int[] nums) {
        int n = nums.length; 

        dp = new int [n];
        Arrays.fill(dp, -1);
        return solve( nums ,n-1); 
    }

    private int solve( int [] nums,  int i){
        if(i<0){
            return 0; 
        }
        if(dp[i] != -1){
            return dp[i];
        }

        int take = nums[i] + solve(nums,i-2);
        int notTake = solve(nums, i-1);
        
        dp[i] =  Math.max(take , notTake);
        
        return dp[i] ;
    }
}