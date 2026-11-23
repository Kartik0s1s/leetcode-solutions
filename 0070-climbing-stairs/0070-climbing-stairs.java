class Solution {
    public int climbStairs(int n) {
        if(n==1){
            return 1;
        }

        int a1= 1;
        int a2= 2; 
    
        for(int i=3; i<=n; i++){
            int curr = a1+a2;

            a1 = a2;
            a2 = curr;
        }
        return a2;
    }
}