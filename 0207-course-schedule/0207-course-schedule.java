class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        List<List<Integer>> adjList = new ArrayList<>();

        for(int i=0; i<numCourses; i++){
            adjList.add(new ArrayList<>());
        }

        int indegree [] = new int [numCourses];
        
        for(int [] edge : prerequisites){
            int u = edge[0];
            int v = edge[1];

            adjList.get(v).add(u);

            indegree[u]++;
        }
        
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<numCourses; i++){
            
            if(indegree[i]==0){
                 q.offer(i);
            }

        }
        int count = 0; 

        while(!q.isEmpty()){
            int node = q.poll();
            count++;

            for(int course : adjList.get(node)){
                indegree[course]--;
                if(indegree[course]==0){
                    q.offer(course);
                }

            }

        }
        if(numCourses == count)
        return true;

        return false;
    }
}