 import java.util.*;

class Solution {

    long[] prefix;
    long[] power;

    long BASE = 100003;

    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;

        prefix = new long[n + 1];
        power = new long[n + 1];

        power[0] = 1;

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] * BASE + nums[i];
            power[i + 1] = power[i] * BASE;
        }

        int low = 1;
        int high = n;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (check(mid, n)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean check(int len, int n) {

        HashMap<Long, Integer> map = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {

            long hash = prefix[i + len]
                      - prefix[i] * power[len];

            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }

        for (int freq : map.values()) {
            if (freq == 1) {
                return true;
            }
        }

        return false;
    }
}