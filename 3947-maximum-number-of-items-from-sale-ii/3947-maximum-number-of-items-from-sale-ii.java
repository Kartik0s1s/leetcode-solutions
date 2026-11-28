class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        int[] freq = new int[n + 1];
        int[] free = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        // Frequency counting
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Sieve: count eligible free items
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                free[i] += freq[j];
            }

            free[i]--;
        }

        // Sort by price
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long remaining = budget;
        long ans = 0;

        // Greedy purchases with free copies
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            if (price >= 2L * minPrice) {
                break;
            }

            long buy = Math.min(
                free[factor],
                remaining / price
            );

            ans += 2 * buy;
            remaining -= buy * price;
        }

        // Purchase cheapest items
        ans += remaining / minPrice;

        return (int) ans;
    }
}