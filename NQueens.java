// This solution uses a dfs approach with backtracking. We recurse through rows and at each row we try to place the queen at every column.
// Once we place a queen, we will add the restricted column, diagonal and anti diagonal in the hashset so that in next rows we wont end up placing the queen at wrong spot. 
// Before placing each queen we will check the conditions from hash set and place only if its valid
class Solution {
    List<List<String>> response = new ArrayList();
    boolean[][] arr;
    Set<Integer> cols = new HashSet();
    Set<Integer> diagonals = new HashSet();
    Set<Integer> antiDiagonals = new HashSet();

    public List<List<String>> solveNQueens(int n) {
        arr=new boolean[n][n];
        for(int i=0;i<n;i++) {
            Arrays.fill(arr[i], false);
        }
        solve(n, 0);
        return response;
    }

    private void solve(int n, int i) {
        if(i==n) {
            List<String> temp = new ArrayList();
            for(int a=0;a<n;a++) {
                StringBuilder tempStr = new StringBuilder("");
                for(int b=0;b<n;b++) {
                    if(arr[a][b]==true) {
                        tempStr.append("Q");
                    } else {
                        tempStr.append(".");
                    }
                } 
                temp.add(tempStr.toString());
            }
            response.add(temp);
            return;
        }

        for(int j=0;j<n;j++) {
            if(isValid(i, j, n)) {
                arr[i][j] = true;

                cols.add(j);
                diagonals.add(j-i);
                antiDiagonals.add(i+j);

                solve(n, i+1);

                antiDiagonals.remove(i+j);
                diagonals.remove(j-i);
                cols.remove(j);

                arr[i][j] = false;
            }
        }
    }

    private boolean isValid(int i, int j, int n) {
        if(cols.contains(j)) {
            return false;
        }
        if(diagonals.contains(j-i)) return false;
        if(antiDiagonals.contains(i+j)) return false;
        return true;
    }
}

