  
import java.util.*;

class Solution {

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new int[]{v, w});
        }

        // dist[node][count]
        long[][] dist = new long[n][k + 1];

        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // PriorityQueue: {distance, node, count}
        PriorityQueue<long[]> pq =
            new PriorityQueue<>(
                (a, b) -> Long.compare(a[0], b[0])
            );

        // Starting node
        dist[0][1] = 0;

        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {

            long[] current = pq.poll();

            long d = current[0];
            int u = (int) current[1];
            int count = (int) current[2];

            // Ignore outdated states
            if (d != dist[u][count]) {
                continue;
            }

            // Destination reached
            if (u == n - 1) {
                return (int) d;
            }

            // Explore neighbours
            for (int[] edge : graph[u]) {

                int v = edge[0];
                int w = edge[1];

                int newCount;

                if (labels.charAt(u) == labels.charAt(v)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                // Invalid consecutive sequence
                if (newCount > k) {
                    continue;
                }

                long newDistance = d + w;

                if (newDistance < dist[v][newCount]) {

                    dist[v][newCount] = newDistance;

                    pq.offer(new long[]{
                        newDistance,
                        v,
                        newCount
                    });
                }
            }
        }

        return -1;
    }
}
