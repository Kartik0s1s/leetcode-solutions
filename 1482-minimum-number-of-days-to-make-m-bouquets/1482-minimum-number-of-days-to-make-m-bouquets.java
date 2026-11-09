class Solution {
    public int minDays(int[] bloomDay, int m, int k) {

      if(bloomDay.length < (long) m*k){
        return -1;
      }  
      
      int max = Integer.MIN_VALUE;
      int min = Integer.MAX_VALUE;
      
      for(int val : bloomDay){
        min = Math.min(min , val);
        max = Math.max(max , val);
      }


      int low = min ; 
      int high = max;
      int ans = -1;

      while(low <= high ){
        int mid = low +(high - low) / 2;

        boolean result = canMake(mid , bloomDay, m,k);
        
        if(result){
            ans = mid;
            high = mid-1;
        }else {
            low = mid+1;
        }

      }
    return ans;
    }

    private boolean canMake(int minDay , int [] bloomDay, int m , int k){
        int bukey = 0; 
        int consecutive = 0; 

        for(int day : bloomDay){
            if(day <= minDay){
                consecutive++;
                if(consecutive == k ){
                    bukey++;
                    consecutive = 0;
                }
            }else {
                consecutive = 0;
            }
        }
        return bukey >= m ;
    }

}