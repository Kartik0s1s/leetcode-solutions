// Recursion 
// class Solution {
//     int dp [] ; 
//     public int numTilings(int n) {
//         int mod = 1000000007;

//         dp = new int [1001];

//         Arrays.fill( dp, -1);

//         return solve(n);
//     }

//     public int solve(int n ){
//         if(n==1 || n==2){
//             return n;
//         }

//         if(dp[n] != -1){
//             return dp[n];
//         }

//         if(n==3 ){
//             return 5;
//         }

//         return dp[n] = 2 * solve(n-1) + solve(n-3);
//     }
// }



// Memoization 
// class Solution {
//      int dp [] ; 
//       int mod = 1000000007;
//     public int numTilings(int n) {
        

//         dp = new int [1001];

//         Arrays.fill( dp, -1);

//         return solve(n);
//     }

//     public int solve(int n ){
//         if(n==1 || n==2){
//             return n;
//         }

//         if(dp[n] != -1){
//             return dp[n];
//         }

//         if(n==3 ){
//             return 5;
//         }

//         return dp[n] = (2 * solve(n-1)% mod + solve(n-3)% mod)%mod;
//     }
// }









// tabulation 
// Bootum up 
class Solution {
     
    public int numTilings(int n) {
     if(n==1 || n==2){
        return n;
     }

     if(n==3){
        return 5;
     }

     int mod = 1000000007; 
     int [] dp = new int [1001];
      
    dp[1]= 1;
    dp[2]=2;
    dp[3]=5;

    for(int i=4; i<=n; i++){
        dp[i] = (2*dp[i-1] % mod + dp[i-3] % mod ) % mod; 
    }

    return dp[n];  
    }
}

