class Solution {
    public int maxProfit(int[] prices) {
       int buy = Integer.MAX_VALUE;
       int max = Integer.MIN_VALUE; 

       for(int i=0; i<prices.length; i++){
          if( prices[i] < buy){
            buy = prices[i];
          }else {
            max = Math.max(max , prices[i] - buy);
          }
       }
       if(max < 0){
        return 0;
       }
    return max;
    }
}