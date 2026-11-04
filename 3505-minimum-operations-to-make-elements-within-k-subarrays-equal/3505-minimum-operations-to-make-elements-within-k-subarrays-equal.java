
import java.util.*;

class Solution {

    class Fenwick {
        long[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void update(int i, long val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }

        long query(int i) {
            long sum = 0;

            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }

            return sum;
        }

        // Find smallest index whose prefix sum >= target
        int lowerBound(long target) {
            int idx = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {

                int next = idx + bit;

                if (next <= n && tree[next] < target) {
                    idx = next;
                    target -= tree[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] unique = Arrays.stream(sorted)
                .distinct()
                .toArray();

        int m = unique.length;

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(unique, nums[i]) + 1;
        }

        Fenwick count = new Fenwick(m);
        Fenwick sum = new Fenwick(m);

        long[] cost = new long[n - x + 1];

        long totalSum = 0;

        // Calculate cost for every window of size x
        for (int i = 0; i < n; i++) {

            int r = rank[i];

            count.update(r, 1);
            sum.update(r, nums[i]);
            totalSum += nums[i];

            if (i >= x) {
                int oldRank = rank[i - x];

                count.update(oldRank, -1);
                sum.update(oldRank, -nums[i - x]);

                totalSum -= nums[i - x];
            }

            if (i >= x - 1) {

                int medianRank = count.lowerBound((x + 1L) / 2);

                long median = unique[medianRank - 1];

                long leftCount = count.query(medianRank);
                long leftSum = sum.query(medianRank);

                long rightCount = x - leftCount;
                long rightSum = totalSum - leftSum;

                long leftCost =
                        median * leftCount - leftSum;

                long rightCost =
                        rightSum - median * rightCount;

                cost[i - x + 1] = leftCost + rightCost;
            }
        }

        // DP
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                // Don't choose current element
                dp[i][j] = dp[i - 1][j];

                // Choose a window ending at i - 1
                if (i >= x &&
                    dp[i - x][j - 1] != INF) {

                    long take =
                            dp[i - x][j - 1]
                            + cost[i - x];

                    dp[i][j] =
                            Math.min(dp[i][j], take);
                }
            }
        }

        return dp[n][k];
    }
}
