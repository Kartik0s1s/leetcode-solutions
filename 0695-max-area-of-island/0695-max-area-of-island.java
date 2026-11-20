class Solution {
    public int maxAreaOfIsland(int[][] grid) {
      int m = grid.length; 
      int n = grid[0].length;
      
      int area = 0;
      for(int i=0; i<m; i++ ){
        for(int j=0; j<n; j++){
            if(grid[i][j] == 1){
                int currentArea = bfs(i,j, grid);
                area = Math.max(currentArea , area);
            }
        }
      }
      return area; 
    }

    private static int bfs ( int i, int j , int [][] grid ){
        Queue<int []> q = new LinkedList<>();

        q.offer(new int []{i,j});
        grid[i][j] = 0;
        int currentArea = 1; 

        int dr [] = {-1, 1, 0,0};
        int dc [] = { 0, 0, -1, 1};

        while(!q.isEmpty()){
            int node [] = q.poll();
            
            int row = node[0];
            int col = node[1];

            for(int k=0; k<4; k++){
                int newRow = row + dr[k];
                int newCol = col + dc[k];

                if(newRow >=0 && newRow <grid.length && 
                   newCol >=0 && newCol <grid[0].length && 
                   grid[newRow][newCol] == 1){
                    currentArea++;
                    q.offer(new int []{newRow, newCol});
                    grid[newRow][newCol] = 0;
                   }
            }

        }
        
        return currentArea;
    }
}