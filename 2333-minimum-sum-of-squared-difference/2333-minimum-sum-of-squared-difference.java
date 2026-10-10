
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int n = nums1.length;
        int[] diff = new int[n];

        long total = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            max = Math.max(max, diff[i]);
        }

        if (k >= total) return 0;

        long left = 0, right = max;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long required = 0;

            for (int d : diff) {
                if (d > mid) required += d - mid;
            }

            if (required <= k) right = mid;
            else left = mid + 1;
        }

        long level = left;
        long used = 0;
        long answer = 0;

        for (int d : diff) {
            long reduced = Math.min(d, level);
            used += d - reduced;
            answer += reduced * reduced;
        }

        long remaining = k - used;

        for (int d : diff) {
            if (remaining == 0) break;

            if (d >= level && d > 0) {
                answer -= level * level - (level - 1) * (level - 1);
                remaining--;
            }
        }

        return answer;
    }
}
