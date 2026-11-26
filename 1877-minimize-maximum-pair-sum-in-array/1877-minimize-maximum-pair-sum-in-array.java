class Solution {
    public int minPairSum(int[] nums) {
      Arrays.sort(nums);

      int low = 0 ; 
      int high = nums.length-1;
      int ans = 0; 

      while(low < high){
        int sum = nums[low] + nums[high];
        ans = Math.max(ans, sum );

        low++;
        high--;
      }
      return ans;
    }
}