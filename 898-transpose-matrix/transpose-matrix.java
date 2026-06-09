class Solution {
    public int[][] transpose(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        return findTranspose(matrix, r, c);
    }    
    public int[][] findTranspose(int[][] matrix, int r, int c){
        int[][] ans = new int[c][r];
        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                ans[i][j] = matrix[j][i];
            }
        }
        return ans;
        
    }
}