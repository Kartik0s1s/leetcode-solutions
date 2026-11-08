class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
      int low = 0 ; 
      int high = letters.length-1; 
      int ans = -1;
      while(low <= high){

        int mid = low + (high-low)/2;

        if(letters[mid] > target){
            ans = mid;
            high =mid-1;
        }else{
            low = mid+1;
        }
      }  
      if(ans == -1){
        return letters[0];
      }
      return letters[ans];
    }
}