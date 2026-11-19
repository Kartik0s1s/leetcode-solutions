class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int oldColor = image[sr][sc];

        if(oldColor == color){
            return image;
        }

        Queue<int []> q = new LinkedList<>();

        q.offer(new int [] {sr, sc});
        image[sr][sc] = color;

        int [] dr = {-1, 1 , 0 ,0};
        int [] dc = {0, 0 , -1, 1};

        while(!q.isEmpty()){
            int [] curr = q.poll();

            int row = curr[0];
            int col = curr[1];

            for(int i=0; i<4; i++){
                int newRow = row + dr[i];
                int newCol = col + dc[i];

                if(newRow >= 0 && newRow < image.length 
                   &&  newCol >=0 && newCol < image[0].length 
                   &&  image[newRow][newCol] == oldColor
                         ){
                             image[newRow][newCol] = color;
                             q.offer(new int []{newRow, newCol});
                         }
            }
        }
    return image;
    }
}