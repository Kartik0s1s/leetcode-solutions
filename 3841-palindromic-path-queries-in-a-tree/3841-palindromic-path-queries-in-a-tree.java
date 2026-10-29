 
import java.util.*;

class Solution {

    private int[][] up;
    private int[] depth;
    private int[] tin, tout;
    private int[] basePrefix;
    private Fenwick bit;
    private char[] labels;
    private int LOG;

    private int charMask(char c) {
        return 1 << (c - 'a');
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        // Build adjacency list
        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        labels = s.toCharArray();

        // Binary lifting table size
        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        basePrefix = new int[n];

        // Iterative DFS
        int[] parent = new int[n];
        int[] stack = new int[n];
        int[] iter = new int[n];

        int top = 0;
        int timer = 0;

        stack[0] = 0;
        parent[0] = 0;

        tin[0] = timer++;
        basePrefix[0] = charMask(labels[0]);

        while (top >= 0) {
            int u = stack[top];

            if (iter[top] < adj[u].size()) {
                int v = adj[u].get(iter[top]++);

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                tin[v] = timer++;

                basePrefix[v] =
                        basePrefix[u] ^ charMask(labels[v]);

                up[0][v] = u;

                for (int k = 1; k < LOG; k++) {
                    up[k][v] =
                            up[k - 1][up[k - 1][v]];
                }

                top++;
                stack[top] = v;
                iter[top] = 0;

            } else {
                tout[u] = timer - 1;
                top--;
            }
        }

        // Fenwick Tree
        bit = new Fenwick(n);

        List<Boolean> answer = new ArrayList<>();

        // Process queries
        for (String query : queries) {
            String[] parts = query.split("\\s+");

            if (parts[0].equals("update")) {

                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int delta =
                        charMask(labels[u]) ^ charMask(c);

                if (delta != 0) {
                    bit.rangeXor(tin[u], tout[u], delta);
                    labels[u] = c;
                }

            } else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int prefixU =
                        basePrefix[u] ^ bit.pointQuery(tin[u]);

                int prefixV =
                        basePrefix[v] ^ bit.pointQuery(tin[v]);

                int pathMask =
                        prefixU ^ prefixV
                        ^ charMask(labels[ancestor]);

                boolean isPalindrome =
                        (pathMask & (pathMask - 1)) == 0;

                answer.add(isPalindrome);
            }
        }

        return answer;
    }

    // Lowest Common Ancestor using binary lifting
    private int lca(int a, int b) {

        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];

        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                a = up[k][a];
            }
        }

        if (a == b) {
            return a;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][a] != up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }

        return up[0][a];
    }

    // Range XOR updates and point queries
    static class Fenwick {

        private final int[] tree;

        Fenwick(int n) {
            tree = new int[n + 2];
        }

        private void update(int index, int value) {
            for (int i = index; i < tree.length;
                 i += i & -i) {

                tree[i] ^= value;
            }
        }

        void rangeXor(int left, int right, int value) {
            update(left + 1, value);
            update(right + 2, value);
        }

        int pointQuery(int position) {
            int result = 0;

            for (int i = position + 1; i > 0;
                 i -= i & -i) {

                result ^= tree[i];
            }

            return result;
        }
    }
}
