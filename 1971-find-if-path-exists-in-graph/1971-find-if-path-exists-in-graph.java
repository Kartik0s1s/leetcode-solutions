class Solution 
{
    public boolean validPath(int n, int[][] edges, int source, int destination) 
    {
       ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

       for(int i =0; i<n; i++)
       {
        graph.add(new ArrayList<>());
       }

    for(int [] arr : edges)
    {
      int u = arr[0];
      int v = arr[1];

      graph.get(u).add(v);
      graph.get(v).add(u);
    } 

    boolean [] visited = new boolean [n];
    Queue<Integer> q = new LinkedList<>();
    q.offer(source);
    visited[source] = true;

    while(!q.isEmpty())
    {
        int node = q.poll();

        if(node == destination)
        {
            return true;
        }

        for(int neighbor : graph.get(node))
        {
            if(visited[neighbor] == false)
            {
                visited[neighbor] = true;
                q.offer(neighbor);
            }
        }
    }



     return false;
    }
}