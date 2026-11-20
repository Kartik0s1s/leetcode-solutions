class Solution {
     List< List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
      
     List<Integer> path = new ArrayList<>();
      dfs(0, graph, path);
      return ans ;
    }

    private  void dfs(int node , int [][] grid,  List<Integer> path ){
        path.add(node);

        if(node == grid.length-1){
            ans.add(new ArrayList<>(path));
        }else {
            for(int neighbor : grid[node]){
                dfs(neighbor , grid, path);
            }
        }
        path.remove(path.size()-1);
    }
}