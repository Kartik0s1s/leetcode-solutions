class Solution {
    public int shipWithinDays(int[] weights, int days) {

       int max = 0 ;
       int sum = 0 ;

       for(int i=0; i<weights.length; i++){
        sum += weights[i];
        max = Math.max(max,weights[i]);
       }

       int low = max;
       int high = sum; 
       int ans = -1;

       while(low <=high){
        int mid = low + (high-low)/2;
         
        boolean result = canShip(mid, weights,days );

        if(result){
            ans = mid;
            high = mid-1;
        }else {
            low = mid + 1; 
        }

       } 
    return ans;
    }


    private boolean canShip(int C , int weights [], int days){
      
    int i=0; 

    for(int day=1; day<= days; day++){
        int stock = 0;
        while(i<weights.length && stock+weights[i] <= C){
            stock+=weights[i];
            i++;
        }
    }
    
     return  i == weights.length;
    }

}