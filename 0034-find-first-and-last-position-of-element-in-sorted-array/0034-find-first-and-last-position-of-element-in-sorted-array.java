class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = search1(nums, target);
        int right = search2(nums, target);

        return new int [] {left , right};
    }

    public int search1(int [] nums, int target){
        int low = 0 ; 
        int high = nums.length-1;
        int ans = -1;
        while(low <= high){
            int mid = low + (high - low)/2;

            if(nums[mid] == target){
                ans = mid;
                high = mid-1;
            }else if(nums[mid] < target){
                low = mid+1;
            }else {
                high = mid-1;
            }
        } 
        return ans;
    }



    public int search2(int [] nums, int target){
        int low = 0 ; 
        int high = nums.length-1;
        int ans = -1;
        while(low <= high){
            int mid = low + (high - low)/2;

            if(nums[mid] == target){
                ans = mid;
                low = mid+1;
            }else if(nums[mid] < target){
                low = mid+1;
            }else {
                high = mid-1;
            }
        } 
        return ans;
    }
}