class Solution {
    public int minEatingSpeed(int[] piles, int h) {
       int max = 0; 
      
       for(int i=0; i<piles.length; i++){
        max = Math.max(max , piles[i]);
         
       } 

       int low = 1;
       int high = max; 
       int ans = high;
       while(low <= high){
        int mid = low + (high - low)/2;

        boolean result = canEat(mid , piles , h);

        if(result){
           ans = mid;
           high = mid-1;
        } else {
           low = mid+1;
        }
       }
       return ans ;
    }

    private boolean canEat(int C, int [] piles, int h){
        long hour = 0;
        for(int i = 0; i<piles.length; i++ ){
            long currHour = (piles[i] + C -1)/C;
            hour += currHour;
        }
        return hour <= h;
    }
}