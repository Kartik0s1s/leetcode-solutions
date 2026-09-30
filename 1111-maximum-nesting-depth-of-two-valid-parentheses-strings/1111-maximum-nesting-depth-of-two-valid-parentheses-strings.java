class Solution {
    public int[] maxDepthAfterSplit(String seq) {

        int[] answer = new int[seq.length()];
        int depth = 0;

        for (int i = 0; i < seq.length(); i++) {

            if (seq.charAt(i) == '(') {
                depth++;

                // Assign alternate nesting levels to A and B
                answer[i] = depth % 2;
            } 
            else {
                // Closing bracket belongs to the same group
                // as its corresponding opening bracket
                answer[i] = depth % 2;

                depth--;
            }
        }

        return answer;
    }
}