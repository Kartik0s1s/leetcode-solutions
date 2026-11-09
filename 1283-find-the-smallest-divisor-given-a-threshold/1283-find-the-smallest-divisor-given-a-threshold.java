class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int max = 0 ; 

        for(int i=0; i<nums.length; i++){
            max = Math.max(max , nums[i]);
        }

        int low = 1; 
        int high = max ; 

        int ans = -1;

        while(low <= high){
            int mid = low + (high - low)/2;

            boolean result = canDevide(mid , nums, threshold);

            if(result ){
                ans = mid;
                high = mid-1;
            }else {
                low = mid+1;
            }
        }
        return ans;
    }

    private boolean canDevide(int d, int [] nums , int threshold){
        int sum = 0; 
        for(int i=0; i<nums.length; i++){
            int currSum = (nums[i] + d -1)/d;
            sum += currSum;
        }
        return sum <= threshold;
    }
}