// class Solution {

//     public int climbStairs(int n) {
         
//         return solve(n);
//     }

//     private int solve (int n ){
//       if(n < 0){
//         return 0;
//       }

//       if(n==0){
//         return 1;
//       }

//       return solve(n-1) + solve(n-2);

//     }

// }




class Solution {
    
    public int climbStairs(int n) {
        if(n==1){
            return 1;
        }
       int prev1 = 2;
       int prev2 = 1; 

       for(int i=3; i<=n; i++){
        int curr = prev1 + prev2;
        prev2 = prev1; 
        prev1 = curr;
       }  
    return prev1;    
    }
}


