class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
     HashMap<String , List<String>>  map = new HashMap<>();
     for(String s : strs){
      char key [] = s.toCharArray();
      Arrays.sort(key);
      
      String ki  = new String(key);

      map.putIfAbsent(ki, new ArrayList<>() );

      map.get(ki).add(s);

     }   

     return new ArrayList<>(map.values());

    }
}