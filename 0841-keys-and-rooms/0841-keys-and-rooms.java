class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

      boolean [] visited = new boolean [rooms.size()];
      Queue<Integer> q = new LinkedList<>();

      q.offer(0);
      int count =1 ; 
      visited[0]=true;

      while(!q.isEmpty()){
        int node = q.poll();

        for(int neighbor : rooms.get(node)){
            if(!visited[neighbor]){
               count++; 
               visited[neighbor] = true;
                q.offer(neighbor);
            }
        }
      }
    return count == rooms.size(); 
    }
}