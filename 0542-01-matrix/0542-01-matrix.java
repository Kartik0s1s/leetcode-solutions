class Solution {
    public int[][] updateMatrix(int[][] mat) {

        int m = mat.length; 
        int n = mat[0].length;

        int [][] grid = mat;

        Queue<int []> q = new LinkedList<>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0){
                    q.offer(new int []{i,j});
                }
                else if(grid[i][j] == 1){
                    grid[i][j] = -1;
                }
            }
        }

        while(!q.isEmpty()){
            int node [] = q.poll();

            int row = node[0];
            int col = node[1];

            int dr [] = {-1,1,0,0};
            int dc [] = {0,0,-1,1};

            for(int i=0; i<4; i++){
                int newRow = row + dr[i];
                int newCol = col + dc[i];

                if(newRow >=0 && newRow <grid.length && 
                   newCol >=0 && newCol <grid[0].length && 
                   grid[newRow][newCol] == -1 ){
                    grid[newRow][newCol] = grid[row][col] + 1;
                    q.offer(new int []{newRow, newCol});
                   }
            }
        }
        return grid;
    }
}