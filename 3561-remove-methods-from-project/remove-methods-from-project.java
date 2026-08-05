
class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            adj.get(inv[0]).add(inv[1]);
        }

        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);
        isSuspicious[k] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                if (!isSuspicious[v]) {
                    isSuspicious[v] = true;
                    queue.add(v);
                }
            }
        }

        for (int[] inv : invocations) {
            if (!isSuspicious[inv[0]] && isSuspicious[inv[1]]) {
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    all.add(i);
                }
                return all;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                result.add(i);
            }
        }
        return result;
    }
}