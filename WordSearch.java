// This solutions uses DFS with backtracking. We iterate through all the elements and if at all an element is found we dfs around with backtracking and marking the character to 1 so that it wont be revisited. If it reaches at the end it means the string is found. 
class Solution {
    int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1},};
    public boolean exist(char[][] board, String word) {
        boolean check = false;
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                check = findWord(board, word, i, j, 0);
                if(check) return true;
            }
        }
        return false;
    }

    private boolean findWord(char[][] board, String word, int i, int j, int k) {
        if(k==word.length()) {
            return true;
        }
        if(i<0 || j<0 || i==board.length || j==board[0].length) {
            return false;
        }

        if(board[i][j]=='1' || board[i][j]!=word.charAt(k)) {
            return false;
        }

        boolean check = false;
        for(int[] dir:dirs) {
            int row = i+dir[0];
            int col = j+dir[1];
            
            char c = board[i][j];
            board[i][j] = '1';
            check = findWord(board, word, row, col, k+1);
            board[i][j] = c;
            if(check) break;
        }
        return check;
    }
}
