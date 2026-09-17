class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = Integer.MAX_VALUE;

        int[] minLen = new int[n];
        int left = 0;
        int sum = 0;
        int ans = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                int len = right - left + 1;

                // Previous non-overlapping subarray
                if (left > 0 && minLen[left - 1] != INF) {
                    ans = Math.min(ans, len + minLen[left - 1]);
                }

                minLen[right] = len;
            } else {
                minLen[right] = INF;
            }

            // Keep the shortest valid subarray seen so far
            if (right > 0) {
                minLen[right] = Math.min(minLen[right], minLen[right - 1]);
            }
        }

        return ans == INF ? -1 : ans;
    }
}