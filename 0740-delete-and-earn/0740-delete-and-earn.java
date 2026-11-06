class Solution {
    public int deleteAndEarn(int[] nums) {
        int n = nums.length; 

        int max = 0 ; 
        for(int num : nums){
          max = Math.max(max, num);
        }
        
        int [] points = new int [max+1];
        
        for(int num : nums){
            points[num] += num;
        }

        int prev2 = 0;
        int prev1 = 0;



        for(int value: points){
             
            int take = value + prev2; 
            int notTake = prev1;

            int curr = Math.max(take , notTake);

            prev2 = prev1; 
            prev1 = curr;
        }

     return prev1;
    }
}