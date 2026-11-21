import java.util.*;
class Solution 
{
    public boolean possibleBipartition(int n, int[][] dislikes) 
    {
      ArrayList<ArrayList<Integer>>  graph  =  new ArrayList<>();
       
      for(int i=0; i<=n; i++)
      {
        graph.add(new ArrayList<>());
      }
      
      
      for( int [] edge : dislikes)
      {
        int u = edge[0];
        int v = edge[1];

        graph.get(u).add(v);
        graph.get(v).add(u); 
      }
      
       int color [] = new int [n+1];
       
       for(int i=1; i<=n; i++)
       {
        if(color[i] != 0)
        {
          continue;
        } 
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        color[i] = 1;

        while(!q.isEmpty())
        {
            int node = q.poll();
            for(int neighbor : graph.get(node))
            {
                if(color[neighbor] == 0)
                {
                    color[neighbor] =  -color[node];
                    q.offer(neighbor);
                }else if(color[neighbor] == color[node])
                {
                    return false;
                }
            }
        }
       }
    return true;
    }
}