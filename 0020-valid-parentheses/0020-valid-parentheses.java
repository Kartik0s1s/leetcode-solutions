class Solution {
    public boolean isValid(String s) 
    {
        Stack<Character> stack = new Stack<>();

        char ch [] = s.toCharArray();

        for(char c : ch){
            if(c == '[' || c == '(' || c == '{'){
                stack.push(c);
            }else if(stack.isEmpty()){
                return false ;
            }else {
                char top = stack.pop();

                if(top == '(' && c != ')' || top == '[' && c != ']' ||  top == '{' && c != '}' )
                 return false ; 
            }
        }

        return stack.isEmpty();
    }
}