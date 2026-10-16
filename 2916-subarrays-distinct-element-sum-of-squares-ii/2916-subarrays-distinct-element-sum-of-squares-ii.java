
class Solution {
    static final int MOD = 1_000_000_007;

    int[] sum;
    int[] lazy;
    long[] sumSq;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new int[4 * n];
        lazy = new int[4 * n];
        sumSq = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];

            // Increment distinct counts for valid starting indices
            update(1, 0, n - 1, prev + 1, i);

            last[nums[i]] = i;

            // Sum of squared distinct counts for subarrays ending at i
            ans = (ans + sumSq[1]) % MOD;
        }

        return (int) ans;
    }

    void apply(int node, int left, int right, int val) {
        long len = right - left + 1L;

        // (x + val)^2 = x^2 + 2*val*x + val^2
        sumSq[node] = (
            sumSq[node]
            + 2L * val * sum[node]
            + val * val * len
        ) % MOD;

        sum[node] = (int) (
            (sum[node] + val * len) % MOD
        );

        lazy[node] = (lazy[node] + val) % MOD;
    }

    void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        int val = lazy[node];

        apply(node * 2, left, mid, val);
        apply(node * 2 + 1, mid + 1, right, val);

        lazy[node] = 0;
    }

    void update(int node, int left, int right,
                int ql, int qr) {
        if (ql > right || qr < left || ql > qr) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, 1);
            return;
        }

        push(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr);
        update(node * 2 + 1, mid + 1, right, ql, qr);

        sum[node] = (int) (
            ((long) sum[node * 2] + sum[node * 2 + 1]) % MOD
        );

        sumSq[node] = (
            sumSq[node * 2] + sumSq[node * 2 + 1]
        ) % MOD;
    }
}
