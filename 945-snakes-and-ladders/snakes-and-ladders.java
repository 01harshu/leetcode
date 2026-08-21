class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(1);
        visited[1] = true;
        int moves = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n * n) return moves;
                
                for (int dice = 1; dice <= 6; dice++) {
                    int next = curr + dice;
                    if (next > n * n) break;
                    
                    int[] pos = getCoordinates(next, n);
                    int r = pos[0], c = pos[1];
                    
                    int dest = board[r][c] != -1 ? board[r][c] : next;
                    
                    if (!visited[dest]) {
                        visited[dest] = true;
                        queue.offer(dest);
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
    
    private int[] getCoordinates(int square, int n) {
        int r = n - 1 - (square - 1) / n;
        int c = (square - 1) % n;
        if ((n - 1 - r) % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{r, c};
    }
}