class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        int max = Integer.MIN_VALUE; 
        int min = Integer.MAX_VALUE;

        for(int val : time){
            max = Math.max(max , val);
            min = Math.min(min , val);
        }
        long low = min; 
        long high = (long) min * totalTrips ;
        long ans = -1;

        while(low <= high){
          long mid = low + (high - low)/2;
          
          boolean result = canTravel( mid , time , totalTrips);

          if(result){
            ans = mid;
            high = mid-1;
          }else {
            low = mid+1;
          }

        }
        return ans;
    } 


    public boolean canTravel(long minTime, int [] time , int trips){
        long trip = 0;

        for(int i=0; i<time.length; i++){
            long currTrip = minTime / time[i];
            trip += currTrip;
        }
    return trip >= trips;
    }
}