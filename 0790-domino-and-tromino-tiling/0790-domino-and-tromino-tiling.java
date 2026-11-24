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



class Solution {
     int dp [] ; 
      int mod = 1000000007;
    public int numTilings(int n) {
        

        dp = new int [1001];

        Arrays.fill( dp, -1);

        return solve(n);
    }

    public int solve(int n ){
        if(n==1 || n==2){
            return n;
        }

        if(dp[n] != -1){
            return dp[n];
        }

        if(n==3 ){
            return 5;
        }

        return dp[n] = (2 * solve(n-1)% mod + solve(n-3)% mod)%mod;
    }
}