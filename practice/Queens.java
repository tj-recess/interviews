import java.util.*;

public class Queens extends Questions {
    
    public static void main(String[] args) {
        Queens q = new Queens();
        ArrayList<String[]> solutions = q.solveNQueens(Integer.parseInt(args[0]));
        for (String[] sol : solutions) {
            for (String row : sol) {
                print(row);
            }
            print("");
        }
    }

    public ArrayList<String[]> solveNQueens(int n) {
        int[] board = new int[n];
        ArrayList<String[]> result = new ArrayList<String[]>();
        solve(board, 0, result);
        return result;
    }
    
    private void solve(int[] board, int start, ArrayList<String[]> result) {
        if (start == board.length) {
            result.add(printBoard(board));
        } else {
            for (int i = 0, len = board.length; i < len; i++) {
                board[start] = i;
                debug("board = " + Arrays.toString(board));
                if (isSafe(board, start)) {
                    solve(board, start+1, result);
                }
            }
        }
    }
    
    private boolean isSafe(int[] board, int i) {
        for (int j = 0; j < i; j++) {
            if ((board[i] == board[j]) 
                || (board[i] - board[j] == i - j)
                || (board[i] - board[j] == j - i)) {
                    return false;
            }
        }
        return true;
    }
    
    private String[] printBoard(int[] board) {
        String[] output = new String[board.length];
        for (int i = 0, len = board.length; i < len; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                if (board[i] == j) {
                    sb.append("Q");
                } else { 
                    sb.append(".");
                }
            }
            output[i] = sb.toString();
        }
        return output;
    }
}
