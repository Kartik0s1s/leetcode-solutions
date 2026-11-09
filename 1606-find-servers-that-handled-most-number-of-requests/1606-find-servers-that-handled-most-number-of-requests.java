
class Solution {
    public List<Integer> busiestServers(
            int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] count = new int[k];

        // Initially all servers are available
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            int currentTime = arrival[i];

            // Release servers that have completed requests
            while (!busy.isEmpty()
                    && busy.peek()[0] <= currentTime) {

                int[] completed = busy.poll();

                available.add(completed[1]);
            }

            // All servers are busy
            if (available.isEmpty()) {
                continue;
            }

            // Preferred server
            int preferred = i % k;

            // Find next available server
            Integer server = available.ceiling(preferred);

            // Wrap around
            if (server == null) {
                server = available.first();
            }

            // Assign request
            available.remove(server);

            count[server]++;

            // Calculate finishing time
            int finishTime = currentTime + load[i];

            busy.offer(new int[]{finishTime, server});
        }

        // Find maximum requests
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        // Collect busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                result.add(i);
            }
        }

        return result;
    }
}
