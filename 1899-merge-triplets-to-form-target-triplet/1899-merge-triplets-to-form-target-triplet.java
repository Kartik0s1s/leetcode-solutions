 
class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
      
      int prev [] = new int []{0,0,0};
      for(int triplet [] : triplets ){


         if(triplet[0] > target[0] ||
            triplet[1] > target[1] ||
            triplet[2] > target[2]  ){
                continue;
         }

         prev[0] = Math.max(prev[0], triplet[0]);
          prev[1] = Math.max(prev[1], triplet[1]);
           prev[2] = Math.max(prev[2], triplet[2]);

            
      }  
        return  prev[0] == target[0] && prev[1] == target[1] && prev[2] == target[2] ;
    }
}