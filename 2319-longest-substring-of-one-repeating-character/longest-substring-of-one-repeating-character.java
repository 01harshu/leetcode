class Solution {
    class Node {
        int prefLen;
        int suffLen;
        int maxLen;
        char prefChar;
        char suffChar;
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        chars = s.toCharArray();
        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            chars[idx] = c;
            update(1, 0, n - 1, idx, c);
            ans[i] = tree[1].maxLen;
        }
        return ans;
    }

    private void merge(Node node, Node left, Node right, int lLen, int rLen) {
        node.prefChar = left.prefChar;
        node.suffChar = right.suffChar;
        node.maxLen = Math.max(left.maxLen, right.maxLen);

        if (left.suffChar == right.prefChar) {
            node.maxLen = Math.max(node.maxLen, left.suffLen + right.prefLen);
        }

        node.prefLen = left.prefLen;
        if (left.prefLen == lLen && left.prefChar == right.prefChar) {
            node.prefLen = left.prefLen + right.prefLen;
        }

        node.suffLen = right.suffLen;
        if (right.suffLen == rLen && right.suffChar == left.suffChar) {
            node.suffLen = right.suffLen + left.suffLen;
        }
    }

    private void build(int treeIdx, int l, int r) {
        tree[treeIdx] = new Node();
        if (l == r) {
            tree[treeIdx].prefLen = 1;
            tree[treeIdx].suffLen = 1;
            tree[treeIdx].maxLen = 1;
            tree[treeIdx].prefChar = chars[l];
            tree[treeIdx].suffChar = chars[l];
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * treeIdx, l, mid);
        build(2 * treeIdx + 1, mid + 1, r);
        merge(tree[treeIdx], tree[2 * treeIdx], tree[2 * treeIdx + 1], mid - l + 1, r - mid);
    }

    private void update(int treeIdx, int l, int r, int idx, char val) {
        if (l == r) {
            tree[treeIdx].prefChar = val;
            tree[treeIdx].suffChar = val;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * treeIdx, l, mid, idx, val);
        } else {
            update(2 * treeIdx + 1, mid + 1, r, idx, val);
        }
        merge(tree[treeIdx], tree[2 * treeIdx], tree[2 * treeIdx + 1], mid - l + 1, r - mid);
    }
}