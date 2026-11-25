class Solution {
    public int minOperations(int[] nums) {
        

        HashMap <Integer , Integer> map = new HashMap<>();

        for(int task : nums){
           map.put(task , map.getOrDefault(task , 0) + 1);
        }

        int rounds = 0; 
        for(int freq : map.values()){
            if(freq == 1){
                return -1;
            }

            rounds += (freq +2) /3 ; 
        }
        return rounds; 
    }
}