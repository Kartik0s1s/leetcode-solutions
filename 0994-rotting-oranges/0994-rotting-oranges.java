
class Solution {
    public int orangesRotting(int[][] grid) {

        Queue<int[]> q = new LinkedList<>();

        int m = grid.length;
        int n = grid[0].length;

        int fresh = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } 
                else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) {
            return 0;
        }

        int[] ans = bfs(q, fresh, grid);

        fresh = ans[0];
        int minutes = ans[1];

        return fresh == 0 ? minutes : -1;
    }

    private int[] bfs(Queue<int[]> q, int fresh, int[][] grid) {

        int minutes = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty() && fresh > 0) {

            int size = q.size();

            for (int k = 0; k < size; k++) {

                int[] node = q.poll();

                int row = node[0];
                int col = node[1];

                for (int i = 0; i < 4; i++) {

                    int newRow = row + dr[i];
                    int newCol = col + dc[i];

                    if (newRow >= 0 && newRow < grid.length &&
                        newCol >= 0 && newCol < grid[0].length &&
                        grid[newRow][newCol] == 1) {

                        grid[newRow][newCol] = 2;
                        fresh--;

                        q.offer(new int[]{newRow, newCol});
                    }
                }
            }

            minutes++;
        }

        return new int[]{fresh, minutes};
    }
}
