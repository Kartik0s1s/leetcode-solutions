import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                    intervals.get(i).get(0),
                    intervals.get(i).get(1),
                    intervals.get(i).get(2),
                    i
            );
        }

        // Sort by right endpoint
        Arrays.sort(arr, (a, b) -> {
            if (a.r != b.r)
                return Integer.compare(a.r, b.r);

            return Integer.compare(a.l, b.l);
        });

        // right endpoints for binary search
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            ends[i] = arr[i].r;
        }

        /*
         * prev[i] = number of intervals before i
         * that are compatible with interval i.
         *
         * We need:
         *
         * arr[j].r < arr[i].l
         *
         * Notice strictly '<' because sharing a boundary
         * means overlapping.
         */
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = lowerBound(ends, arr[i].l);
        }

        /*
         * dp[k][i]
         *
         * Best result using first i intervals
         * and choosing at most k intervals.
         *
         * We use i from 0...n.
         */
        State[][] dp = new State[5][n + 1];

        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dp[k][i] = new State(0, new ArrayList<>());
            }
        }

        for (int k = 1; k <= 4; k++) {

            for (int i = 1; i <= n; i++) {

                // Option 1: don't take current interval
                State notTake = dp[k][i - 1];

                // Option 2: take current interval
                Interval cur = arr[i - 1];

                State previous = dp[k - 1][prev[i - 1]];

                List<Integer> newIndices =
                        new ArrayList<>(previous.indices);

                newIndices.add(cur.idx);

                Collections.sort(newIndices);

                State take = new State(
                        previous.score + cur.w,
                        newIndices
                );

                dp[k][i] = better(take, notTake)
                        ? take
                        : notTake;
            }
        }

        List<Integer> answer = dp[4][n].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    /*
     * Find first position where ends[pos] >= target.
     *
     * Therefore all positions before it satisfy:
     *
     * ends[pos] < target
     */
    static int lowerBound(int[] ends, int target) {

        int left = 0;
        int right = ends.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (ends[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /*
     * Return true if a is better than b.
     */
    static boolean better(State a, State b) {

        // Higher score wins
        if (a.score != b.score) {
            return a.score > b.score;
        }

        // Same score -> lexicographically smaller indices
        return lexicographicallySmaller(
                a.indices,
                b.indices
        );
    }

    static boolean lexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        // If one is prefix of another,
        // shorter one is lexicographically smaller.
        return a.size() < b.size();
    }
}