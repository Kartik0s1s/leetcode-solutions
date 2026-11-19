class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        
         Queue<Integer> q = new LinkedList<>();

         boolean visited [] = new boolean[n];

         List<List<Integer>> adjList = new  ArrayList<>();

         for(int i=0; i<n; i++){
            adjList.add(new ArrayList<>());
         }

         for(int [] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adjList.get(u).add(v);
            adjList.get(v).add(u);
         }


         visited[source] = true;
         
         q.offer(source);

         while(!q.isEmpty()){
            int node = q.poll();

            if(node == destination ){
                return true;
            }

            for(int neighbor : adjList.get(node)){
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    q.offer(neighbor);
                }
            }
         }
        return false; 
    }
 

 

}