class Solution {

    private boolean canAssign(int[] nums, int val, int operations) {

        long count = 0;

        for (int ele : nums) {

            if (ele > val) {
                count += (long) Math.ceil((double)(ele - val) / val);
            }
        }

        return count <= operations;
    }

    public int minimumSize(int[] nums, int maxOperations) {

        int low = 1;
        int high = 0;

        for (int num : nums) {
            high = Math.max(high, num);
        }

        int res = Integer.MAX_VALUE;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canAssign(nums, mid, maxOperations)) {

                res = mid;
                high = mid - 1;

            } else {

                low = mid + 1;
            }
        }

        return res;
    }
}