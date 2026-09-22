class Solution {
    private static class Node {
        int totalProd;
        int[] count;

        Node(int k) {
            count = new int[k];
            totalProd = 1 % k;
        }
    }

    private int n;
    private int K;
    private Node[] tree;

    private Node merge(Node left, Node right) {
        Node res = new Node(K);
        res.totalProd = (left.totalProd * right.totalProd) % K;

        for (int i = 0; i < K; i++) {
            res.count[i] += left.count[i];
        }

        for (int j = 0; j < K; j++) {
            if (right.count[j] > 0) {
                int rem = (left.totalProd * j) % K;
                res.count[rem] += right.count[j];
            }
        }

        return res;
    }

    private void build(int node, int start, int end, int[] nums) {
        if (start == end) {
            int val = nums[start] % K;
            tree[node] = new Node(K);
            tree[node].totalProd = val;
            tree[node].count[val] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid, nums);
        build(2 * node + 1, mid + 1, end, nums);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int rem = val % K;
            tree[node] = new Node(K);
            tree[node].totalProd = rem;
            tree[node].count[rem] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        if (start <= idx && idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node queryTree(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return null;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        Node leftNode = queryTree(2 * node, start, mid, l, r);
        Node rightNode = queryTree(2 * node + 1, mid + 1, end, l, r);

        if (leftNode == null) return rightNode;
        if (rightNode == null) return leftNode;

        return merge(leftNode, rightNode);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.K = k;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node resNode = queryTree(1, 0, n - 1, start, n - 1);
            result[i] = resNode.count[x];
        }

        return result;
    }
}