import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression, 0, expression.length() - 1);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s, int left, int right) {

        Set<String> result = new HashSet<>();

        Set<String> current = new HashSet<>();
        current.add("");

        int i = left;

        while (i <= right) {

            char ch = s.charAt(i);

            // Case 1: normal letter
            if (ch >= 'a' && ch <= 'z') {

                Set<String> next = new HashSet<>();

                for (String a : current) {
                    next.add(a + ch);
                }

                current = next;
                i++;
            }

            // Case 2: opening brace
            else if (ch == '{') {

                int count = 1;
                int j = i + 1;

                while (count > 0) {
                    if (s.charAt(j) == '{') {
                        count++;
                    } else if (s.charAt(j) == '}') {
                        count--;
                    }
                    j++;
                }

                // Parse inside {...}
                Set<String> inside = parse(s, i + 1, j - 2);

                // Concatenate current × inside
                Set<String> next = new HashSet<>();

                for (String a : current) {
                    for (String b : inside) {
                        next.add(a + b);
                    }
                }

                current = next;

                i = j;
            }

            // Case 3: comma
            else if (ch == ',') {

                result.addAll(current);

                current.clear();
                current.add("");

                i++;
            }

            else {
                i++;
            }
        }

        result.addAll(current);

        return result;
    }
}