
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] arr = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            arr[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(arr);

        long score = 0;
        int n = arr.length;
        int half = n / 2;

        for (int i = 0; i < half; i++) {
            score -= arr[i];
        }

        for (int i = half; i < n; i++) {
            score += arr[i];
        }

        return score;
    }
}
