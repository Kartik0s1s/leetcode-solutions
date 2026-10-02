 class Solution {
    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) 
    {
     backtracking("", 0,0, n);
     return ans ;   
    }

    public void backtracking(String current, int open , int close  ,int n)
    {
        if(current.length() == 2*n)
        {
          ans.add(current);
          return;
        }

        if(open < n )
        {
            backtracking(current + "(" , open +1 , close ,n);
        }
         if(close < open )
         {
            backtracking(current + ")" , open  , close+1 ,n);
         }
    }
}