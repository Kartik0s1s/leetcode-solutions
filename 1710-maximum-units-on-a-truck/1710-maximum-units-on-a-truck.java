class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // sort 
      Arrays.sort(boxTypes, (a,b)-> b[1]-a[1]);

      int maxUnits = 0; 
      for(int type [] : boxTypes){
        int boxes = Math.min(type[0], truckSize);
        maxUnits += boxes * type[1];
        truckSize -= boxes;
      }
      return maxUnits;
    }
}