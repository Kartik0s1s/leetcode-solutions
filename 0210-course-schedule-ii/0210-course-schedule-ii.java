class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>(); 

        for(int i=0; i<numCourses; i++){
            adjList.add(new ArrayList<>());
        }
        
        int indegree[]= new int [numCourses];
        for(int edge [] : prerequisites ){
            int course = edge[0];
            int pre = edge[1];

            adjList.get(pre).add(course);
            indegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<numCourses; i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }

        int [] ans = new int [numCourses];
        int index = 0;

        while(!q.isEmpty()){
            int node = q.poll();

            ans[index++]= node;

            for(int neighbor : adjList.get(node)){
                indegree[neighbor]--;
                if(indegree[neighbor] == 0){
                    q.offer(neighbor);
                }
            } 
        }
    if(index == numCourses){
        return ans;
    }

    return new int [] {};
    }
}