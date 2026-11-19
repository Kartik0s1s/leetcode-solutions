class Solution {
    public int numIslands(char[][] grid) {
        int n = grid.length; 
        int m = grid[0].length;
        int noOfIsland = 0; 

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
               if(grid[i][j] == '1'){
                noOfIsland++;
                bfs(i,j,grid);
               } 
            }
        }
    return noOfIsland;   
    }

    public static void bfs(int i, int j, char [][] grid){
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int [] {i,j});
        grid[i][j] = '0';
        
        int [] dr = {-1, 1,0,0};
        int [] dc = { 0, 0,-1,1};

        while(!q.isEmpty()){
            int [] node = q.poll();

            int row = node[0];
            int col = node[1];

            for(int k=0; k<4; k++){
                int newRow = row + dr[k];
                int newCol = col + dc[k];

                if(newRow >=0 && newRow < grid.length && 
                   newCol >=0 && newCol < grid[0].length && 
                   grid[newRow][newCol] =='1'){
                     q.offer(new int []{newRow, newCol});
                     grid[newRow][newCol] = '0';
                   }
            }
        }
    
    }
}