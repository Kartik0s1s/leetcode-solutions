class Solution {
    public int findCircleNum(int[][] isConnected) {
        
        int n= isConnected.length; 
        boolean visited [] = new boolean [n];
        int provinces = 0;
        for(int i=0; i<n; i++){
           if(!visited[i] ){
              bfs(i, isConnected, visited);
              provinces++;
           }
        }
        return provinces;
    }

    private void bfs(int i , int [][] grid , boolean [] visited){
        Queue<Integer> q = new LinkedList<>();
        
        q.offer(i);
        visited[i]  = true;

       while(!q.isEmpty()){
          int node = q.poll();

          for(int neighbor =0; neighbor < grid.length; neighbor++){
            if( grid[node][neighbor] == 1 && !visited[neighbor]){
                visited[neighbor] = true;
                q.offer(neighbor);
            }
        }

       }
    }
}