
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // Sort logs by time
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        // Store queries with their original indices
        int[][] sortedQueries = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries by query time
        Arrays.sort(sortedQueries, (a, b) -> a[0] - b[0]);

        int[] ans = new int[queries.length];

        // Server ID -> number of requests in current window
        Map<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;

        int activeServers = 0;

        for (int[] query : sortedQueries) {

            int q = query[0];
            int originalIndex = query[1];

            int start = q - x;

            // Add logs whose time <= q
            while (right < logs.length &&
                   logs[right][1] <= q) {

                int server = logs[right][0];

                map.put(server, map.getOrDefault(server, 0) + 1);

                if (map.get(server) == 1) {
                    activeServers++;
                }

                right++;
            }

            // Remove logs older than start
            while (left < right &&
                   logs[left][1] < start) {

                int server = logs[left][0];

                map.put(server, map.get(server) - 1);

                if (map.get(server) == 0) {
                    map.remove(server);
                    activeServers--;
                }

                left++;
            }

            // Servers with zero requests
            ans[originalIndex] = n - activeServers;
        }

        return ans;
    }
}
